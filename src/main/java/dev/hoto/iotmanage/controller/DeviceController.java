package dev.hoto.iotmanage.controller;

import dev.hoto.iotmanage.service.deviceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeviceController {
    @Autowired
    deviceServiceImpl deviceService;

    @GetMapping("/register")
    public String registerDevice() { return  "registerDevice"; }

    @GetMapping("/show")
    public String showDevices() {
        return "showDevice";
    }

    @GetMapping("/edit")
    public String editDevices() {
        return "editDevice";
    }
}
