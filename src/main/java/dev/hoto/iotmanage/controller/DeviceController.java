package dev.hoto.iotmanage.controller;

import dev.hoto.iotmanage.IoTDevice;
import dev.hoto.iotmanage.service.deviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class DeviceController {
    @Autowired
    deviceServiceImpl deviceService; // DI 형식으로 사용하기 위해 선언

    @GetMapping("/register")
    public String registerDevice() {
        // 디바이스 추가 페이지
        return "registerDevice";
    }

    @PostMapping("/register/submit")
    public String saveRegistered(HttpServletRequest request) {
        String moduleType = request.getParameter("module_type");  // post로 받아온 데이터 가져오기
        String moduleName = request.getParameter("module_name");
        String moduleOwner = request.getParameter("module_owner");
        IoTDevice device = new IoTDevice(moduleType, moduleName, moduleOwner, 0, 0); // DI 처리

        deviceService.InsertDevice(device); // 디바이스 정보 저장
        return "redirect:/"; // 메인 페이지로 가기
    }


    @GetMapping("/show")
    public String showDevices(Model model) {
        List<IoTDevice> list = deviceService.getDevices();
        model.addAttribute("deviceList", list);

        return "showDevice";
    }

    @PostMapping("/edit")
    public String editDevices(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));

        IoTDevice selectedDevice = deviceService.GetDeviceById(id);

        model.addAttribute("selectedDevice", selectedDevice);
        return "editDevice";
    }

    @PostMapping("/edit/submit")
    public String saveEdited(HttpServletRequest request) {
        String type = request.getParameter("module_type");
        String nickname = request.getParameter("module_name");
        String owner = request.getParameter("module_owner");
        int power = Integer.parseInt(request.getParameter("module_power"));
        int status = Integer.parseInt(request.getParameter("module_status"));

        IoTDevice modifiedDevice = new IoTDevice(type, nickname, owner, power, status);

        deviceService.ModifyDevice(modifiedDevice);

        return "redirect:/";
    }


    @PostMapping("/delete")
    public String deleteDevice(HttpServletRequest request) {
        int moduleId = Integer.parseInt(request.getParameter("module_id"));  // post로 받아온 데이터 가져오기

        deviceService.DeleteDeviceById(moduleId); // 해당 디바이스 제거
        return "redirect:/show"; // show 페이지로 가기
    }
}
