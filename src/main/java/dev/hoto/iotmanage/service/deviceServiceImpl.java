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
    dev.hoto.iotmanage.deviceDAO deviceDAO;

    // 이미 정의해둔 DAO를 싱글톤 형식으로 가져오기 위해 선언
    //protected dev.hoto.iotmanage.deviceDAO deviceDAO = dev.hoto.iotmanage.deviceDAO.sharedInstance();

    public List<IoTDevice> getDevices()
    {
        ResultSet rs = null;
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
                device.setStatus(rs.getInt("status"));

                list.add(device);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    public boolean InsertDevice(IoTDevice device)
    {
        return deviceDAO.InsertDevice(device);
    }

    public boolean DeleteDeviceById(int id) { return deviceDAO.DeleteDeviceById(id); }

    public IoTDevice GetDeviceById(int id) {
        ResultSet rs = deviceDAO.getDeviceById(id);

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
                device.setStatus(rs.getInt("status"));
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return device;
    }

    public List<IoTDevice> searchDevicesByName(String name)
    {
        ResultSet rs = null;
        List<IoTDevice> list = null;

        rs = deviceDAO.searchDevicesByName(name);
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
                device.setStatus(rs.getInt("status"));

                list.add(device);
            }
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
}
