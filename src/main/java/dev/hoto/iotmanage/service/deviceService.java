package dev.hoto.iotmanage.service;

import dev.hoto.iotmanage.IoTDevice;

import java.util.List;

// 서비스 인터페이스
public interface deviceService {
    List<IoTDevice> getDevices(); // 디바이스 목록 가져오기
    boolean InsertDevice(IoTDevice device); // 디바이스 추가
    boolean DeleteDeviceById(int id); // id가 같은 디바이스 제거
    IoTDevice GetDeviceById(int id); // id가 같은 디바이스의 정보를 가져옴
    List<IoTDevice> searchDevicesByName(String name); // 검색어가 포함된 이름이 있는 디바이스 리스트를 가져옴
    boolean ModifyDevice(IoTDevice device); // 디바이스 정보 수정
}
