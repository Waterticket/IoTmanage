package dev.hoto.iotmanage.controller;

import dev.hoto.iotmanage.DataProcess;
import dev.hoto.iotmanage.IoTDevice;
import dev.hoto.iotmanage.service.deviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DeviceController {
    @Autowired
    deviceServiceImpl deviceService; // DI 형식으로 사용하기 위해 선언

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/register")
    public String registerDevice() {
        // 디바이스 추가 페이지
        return "registerDevice";
    }

    @PostMapping("/register/submit")
    public String saveRegistered(HttpServletRequest request, Model model) {
        String moduleType = request.getParameter("module_type");  // post로 받아온 데이터 가져오기
        String moduleName = request.getParameter("module_name");
        String moduleOwner = request.getParameter("module_owner");
        IoTDevice device = new IoTDevice(moduleType, moduleName, moduleOwner, 1); // DI 처리

        deviceService.InsertDevice(device); // 디바이스 정보 저장

        model.addAttribute("bIsRegistered", true);

        return "index"; // 메인 페이지로 가기
    }


    @GetMapping("/show")
    public String showDevices(Model model) {
        List<IoTDevice> list = deviceService.getDevices();
        DataProcess.changeModuleTypeToKorean(list);
        model.addAttribute("deviceList", list);

        return "showDevice";
    }

    @PostMapping("/show/search")
    public String search(HttpServletRequest request, Model model) {
        String kindOfSearch = request.getParameter("how_to_search");
        String value = request.getParameter("value_to_search");

        try {
            if (kindOfSearch.equals("searchNickname")) {
                List<IoTDevice> list = deviceService.searchDevicesByName(value);

                model.addAttribute("deviceList", list);
            } else if (kindOfSearch.equals("searchId")) {
                int id = Integer.parseInt(value);

                ArrayList list = new ArrayList<IoTDevice>();
                IoTDevice device = deviceService.GetDeviceById(id);
                if (device.getId() != 0) {
                    list.add(device);
                    model.addAttribute("deviceList", list);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return "showDevice";
    }


    @PostMapping("/edit")
    public String editDevices(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("module_id"));

        IoTDevice selectedDevice = deviceService.GetDeviceById(id);
        model.addAttribute("deviceEngType", selectedDevice.getType());

        DataProcess.changeModuleTypeToKorean(selectedDevice);
        model.addAttribute("selectedDevice", selectedDevice);

        return "editDevice";
    }

    @PostMapping("/edit/submit")
    public String saveEdited(HttpServletRequest request, Model model) {
        IoTDevice modifiedDevice = null;
        try {
            int id = Integer.parseInt(request.getParameter("module_id"));
            String type = request.getParameter("module_type");
            String nickname = request.getParameter("module_name");
            String owner = request.getParameter("module_owner");
            int power = Integer.parseInt(request.getParameter("module_power"));

            modifiedDevice = new IoTDevice(type, nickname, owner, power);
            modifiedDevice.setId(id);

            deviceService.ModifyDevice(modifiedDevice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        model.addAttribute("bIsEdited", true);

        return "index";
    }


    @PostMapping("/delete")
    public String deleteDevice(HttpServletRequest request, Model model) {
        int moduleId = Integer.parseInt(request.getParameter("module_id"));  // post로 받아온 데이터 가져오기

        deviceService.DeleteDeviceById(moduleId); // 해당 디바이스 제거

        model.addAttribute("bIsRemoved", true);

        return "index"; //index 페이지로 이동
    }
}

