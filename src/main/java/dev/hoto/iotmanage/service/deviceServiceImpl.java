package dev.hoto.iotmanage.service;

import dev.hoto.iotmanage.IoTDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("deviceService")
public class deviceServiceImpl implements deviceService{
    @Autowired
    dev.hoto.iotmanage.deviceDAO deviceDAO; // DAO를 DI 형식으로 사용하기 위해 선언

    @Override
    public List<IoTDevice> getDevices()
    {
        ResultSet rs = null; // 변수 초기 선언
        List<IoTDevice> list = null;

        rs = deviceDAO.getDevices();
        if(rs == null) {
            // 값을 못불러옴
        }
        
        // 리스트를 통해 데이터를 한번에 전송하기 위함
        list = new ArrayList<IoTDevice>();

        // 데이터를 읽어서 list에 저장
        try {
            while (rs.next()) { // 값이 있는 동안 반복
                // DTO 클래스 객체 생성
                IoTDevice device = new IoTDevice();

                // DTO 클래스 변수에 값을 세팅하기 위해 Set 메서드를 이용하고.
                // DB에 Select 결과를 컬럼 단위로 읽어오기 위해서 'get변수타입("컬럼명")' 함수를 사용
                device.setId(rs.getInt("id"));
                device.setType(rs.getString("type"));
                device.setNickname(rs.getString("nickname"));
                device.setOwner(rs.getString("owner"));
                device.setPower(rs.getInt("power"));

                list.add(device);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list; // 디바이스 정보가 담긴 리스트 리턴
    }

    @Override
    public boolean InsertDevice(IoTDevice device)
    {
        return deviceDAO.InsertDevice(device);
    } // 디바이스 추가

    @Override
    public boolean DeleteDeviceById(int id) { return deviceDAO.DeleteDeviceById(id); } // id가 같은 디바이스 제거

    @Override
    public IoTDevice GetDeviceById(int id) { // id가 같은 디바이스의 정보를 가져옴
        ResultSet rs = deviceDAO.getDeviceById(id); // 해당 디바이스의 데이터를 ResultSet으로 가져옴

        // DTO 클래스 객체 생성
        IoTDevice device = new IoTDevice();

        try {
            while (rs.next()) { // 값이 있는 동안 반복 (id는 primary_key이기에 중복되지 않고, 즉 한번만 실행되게 된다)
                // device 데이터를 넣는다
                device.setId(rs.getInt("id"));
                device.setType(rs.getString("type"));
                device.setNickname(rs.getString("nickname"));
                device.setOwner(rs.getString("owner"));
                device.setPower(rs.getInt("power"));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return device;
    }

    @Override
    public List<IoTDevice> searchDevicesByName(String name) // 검색어가 포함된 이름이 있는 디바이스 리스트를 가져옴
    {
        ResultSet rs = null;
        List<IoTDevice> list = null;

        rs = deviceDAO.searchDevicesByName(name); // 검색어를 포함한 이름을 가진 디바이스 목록을 ResultSet으로 가져옴
        if(rs == null) {
            // 값을 못불러옴
        }

        // 리스트를 통해 데이터를 한번에 전송하기 위해 리스트 생성
        list = new ArrayList<IoTDevice>();

        // 데이터를 읽어서 list에 저장
        try {
            while (rs.next()) { // 값이 있는 동안 반복
                // DTO 클래스 객체 생성
                IoTDevice device = new IoTDevice();

                // DTO 클래스 변수에 값을 세팅하기 위해 Set 메서드를 이용하고.
                // DB에 Select 결과를 컬럼 단위로 읽어오기 위해서 'get변수타입("컬럼명")' 함수를 사용
                device.setId(rs.getInt("id"));
                device.setType(rs.getString("type"));
                device.setNickname(rs.getString("nickname"));
                device.setOwner(rs.getString("owner"));
                device.setPower(rs.getInt("power"));

                list.add(device);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean ModifyDevice(IoTDevice device) { return deviceDAO.ModifyDevice(device); } // 디바이스 정보 수정
}
