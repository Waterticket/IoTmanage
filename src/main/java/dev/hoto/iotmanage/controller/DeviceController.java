package dev.hoto.iotmanage.controller;

import dev.hoto.iotmanage.Service.deviceDBService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DeviceController {
    @GetMapping("/register")
    public String registerDevice() {
        return  "registerDevice";
    }

    @GetMapping("/show")
    public String showDevices() {
        return "showDevice";
    }

    @GetMapping("/edit")
    public String editDevices() {
        return "editDevice";
    }
}
