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

    //Home 컨트롤러
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 디바이스 추가 페이지로 이동
    @GetMapping("/register")
    public String registerDevice() {
        return "registerDevice";
    }

    //기기 조회 페이지로 이동
    @GetMapping("/show")
    public String showDevices(Model model) {
        List<IoTDevice> list = deviceService.getDevices(); //DB에서 기기 리스트 받아오기
        DataProcess.changeModuleTypeToKorean(list); //DB에 영어로 저장되어 있는 모듈 종류를 한글로 변경
        model.addAttribute("deviceList", list); //table로 보여주기 위한 Attriute 저장

        return "showDevice"; //기기 조회 페이지로 가기
    }

    //수정 및 삭제 페이지로 이동
    @PostMapping("/edit")
    public String editDevices(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("module_id"));

        IoTDevice selectedDevice = deviceService.GetDeviceById(id);

        //type을 변경하지 않을 경우 DB에 한글로 값이 들어가는 문제를 해결하기 위함
        model.addAttribute("deviceEngType", selectedDevice.getType());

        //type값을 한글로 변경
        DataProcess.changeModuleTypeToKorean(selectedDevice);
        model.addAttribute("selectedDevice", selectedDevice); //기본값을 보여주기 위한 Attriute 저장

        return "editDevice"; //수정 페이지로 이동
    }

}

