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
    deviceServiceImpl deviceService;

    @GetMapping("/register")
    public String registerDevice() { 
        // 디바이스 추가 페이지
        return  "registerDevice";
    }

    @PostMapping("/register")
    public String registerDevicePost(HttpServletRequest httpServletRequest, Model model) {
        String type = httpServletRequest.getParameter("module_type"); // post로 받아온 데이터 가져오기
        String name = httpServletRequest.getParameter("module_name");
        String owner = httpServletRequest.getParameter("module_owner");
        IoTDevice device = new IoTDevice(type, name, owner, 0, 0); // DI 처리

        deviceService.InsertDevice(device); // 디바이스 정보 저장
        return "redirect:/"; // 메인 페이지로 가기
    }

    @GetMapping("/show")
    public String showDevices() {
        List<IoTDevice> list = deviceService.getDevices();
        
        /* @todo 여기에서 값 넣기 */
        
        return "showDevice";
    }

    @GetMapping("/edit")
    public String editDevices() {
        return "editDevice";
    }
}
