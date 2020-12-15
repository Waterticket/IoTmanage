package dev.hoto.iotmanage.service;

import dev.hoto.iotmanage.IoTDevice;

import java.util.List;

public interface deviceService {
    public List<IoTDevice> getDevices();
    public boolean InsertDevice(IoTDevice device);
}
