package dev.hoto.iotmanage.controller;

import dev.hoto.iotmanage.IoTDevice;
import dev.hoto.iotmanage.service.deviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeviceController {
    @Autowired
    deviceServiceImpl deviceService;

    @GetMapping("/register")
    public String registerDevice() {
        return "registerDevice";
    }

    @GetMapping("/register/submit")
    public String saveRegistered(HttpServletRequest request) {
        String moduleType = request.getParameter("module_type");
        String moduleName = request.getParameter("module_name");
        String moduleOwner = request.getParameter("module_owner");

        System.out.println(moduleType + ", " + moduleName + ", " + moduleOwner);


        return "showDevice";
    }

    @GetMapping("/show")
    public String showDevices() {

        System.out.println("showDevices");
        return "showDevice";
    }

    @GetMapping("/edit")
    public String editDevices() {
        return "editDevice";
    }


}
