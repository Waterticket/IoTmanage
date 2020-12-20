package dev.hoto.iotmanage.controller;

import dev.hoto.iotmanage.DataProcess;
import dev.hoto.iotmanage.IoTDevice;
import dev.hoto.iotmanage.service.deviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SubmitController {

    @Autowired
    deviceServiceImpl deviceService; // DI 형식으로 사용하기 위해 선언

    //디바이스 추가 페이지에서 submit
    @PostMapping("/register/submit")
    public String saveRegistered(HttpServletRequest request, Model model) {
        // Post로 받아온 데이터 가져오기
        String moduleType = request.getParameter("module_type");
        String moduleName = request.getParameter("module_name");
        String moduleOwner = request.getParameter("module_owner");
        IoTDevice device = new IoTDevice(moduleType, moduleName, moduleOwner, 1); // DI 처리

        deviceService.InsertDevice(device); // 디바이스 정보 저장

        //alert 메세지를 위한 Attribute 저장(url, 메세지)
        model.addAttribute("url", "/");
        model.addAttribute("msg", "성공적으로 등록했습니다.");

        return "alert"; //alert 페이지로 이동
    }

    //기기 조회 페이지에서 검색
    @PostMapping("/show/search")
    public String search(HttpServletRequest request, Model model) {
        String kindOfSearch = request.getParameter("how_to_search"); //검색 종류 (id / 기기 이름) 받아오기
        String value = request.getParameter("value_to_search"); //검색할 값 받아오기

        model.addAttribute("how_to_search", kindOfSearch);
        model.addAttribute("value_to_search", value);

        try {

            if (kindOfSearch.equals("searchNickname")) {
                //기기 이름으로 검색하는 경우
                List<IoTDevice> list = deviceService.searchDevicesByName(value); //DB에서 리스트 받아오기
                DataProcess.changeModuleTypeToKorean(list);
                model.addAttribute("deviceList", list); //table로 보여주기 위한 Attriute 저장
            } else if (kindOfSearch.equals("searchId")) {
                //id로 검색하는 경우
                int id = Integer.parseInt(value);
                ArrayList list = new ArrayList<IoTDevice>();
                IoTDevice device = deviceService.GetDeviceById(id); //DB에서 객체 받아오기

                //type을 변경하지 않을 경우 DB에 한글로 값이 들어가는 문제를 해결하기 위함
                model.addAttribute("deviceEngType", device.getType());

                //type값을 한글로 변경
                DataProcess.changeModuleTypeToKorean(device);

                //null 값 처리
                if (device.getId() != 0) {
                    list.add(device); //같은 방식으로 처리하기 위해 리스트로 설정
                    model.addAttribute("deviceList", list); //table로 보여주기 위한 Attriute 저장
                }
            }
        } catch (NumberFormatException e) {
            //parseInt 에러 처리
            e.printStackTrace();
        }

        return "showDevice"; //기기 조회 페이지로 이동
    }

    //수정 submit
    @PostMapping("/edit/submit")
    public String saveEdited(HttpServletRequest request, Model model) {
        IoTDevice modifiedDevice = null;

        //parseInt 에러 처리를 위함
        try {
            //html의 form에서 값 받아오기
            int id = Integer.parseInt(request.getParameter("module_id"));
            String type = request.getParameter("module_type");
            String nickname = request.getParameter("module_name");
            String owner = request.getParameter("module_owner");
            int power = Integer.parseInt(request.getParameter("module_power"));

            modifiedDevice = new IoTDevice(type, nickname, owner, power);
            modifiedDevice.setId(id); //id는 따로 입력 필요 <= 안그러면 수정할 기기를 못찾음

            deviceService.ModifyDevice(modifiedDevice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        //alert 메세지를 위한 Attribute 저장(url, 메세지)
        model.addAttribute("url", "/");
        model.addAttribute("msg", "성공적으로 수정했습니다.");

        return "alert"; //alert 페이지로 이동
    }

    //삭제 button
    @PostMapping("/delete")
    public String deleteDevice(HttpServletRequest request, Model model) {
        int moduleId = Integer.parseInt(request.getParameter("module_id"));  // post로 받아온 데이터 가져오기

        deviceService.DeleteDeviceById(moduleId); // 해당 디바이스 제거

        //alert 메세지를 위한 Attribute 저장(url, 메세지)
        model.addAttribute("url", "/");
        model.addAttribute("msg", "성공적으로 삭제했습니다.");

        return "alert"; //alert 페이지로 이동
    }
}
