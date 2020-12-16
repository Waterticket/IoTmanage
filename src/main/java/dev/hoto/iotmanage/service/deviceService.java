package dev.hoto.iotmanage.service;

import dev.hoto.iotmanage.IoTDevice;

import java.util.List;

// 서비스 인터페이스
public interface deviceService {
    List<IoTDevice> getDevices();
    boolean InsertDevice(IoTDevice device);
    boolean DeleteDeviceById(int id);
    IoTDevice GetDeviceById(int id);
    List<IoTDevice> searchDevicesByName(String name);
}
