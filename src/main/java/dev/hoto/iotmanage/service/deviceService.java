package dev.hoto.iotmanage.service;

import dev.hoto.iotmanage.IoTDevice;

import java.util.List;

public interface deviceService {
    List<IoTDevice> getDevices();
    boolean InsertDevice(IoTDevice device);
    boolean DeleteDeviceById(int id);
}
