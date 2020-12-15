package dev.hoto.iotmanage.service;

import dev.hoto.iotmanage.IoTDevice;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service("deviceService")
public class deviceServiceImpl implements deviceService{
    protected dev.hoto.iotmanage.deviceDAO deviceDAO = dev.hoto.iotmanage.deviceDAO.sharedInstance();

    public List<IoTDevice> getDevices()
    {
        ResultSet rs = null;
        List<IoTDevice> list = null;

        rs = deviceDAO.getDevices();
        if(rs == null) {
            // 값을 못불러옴
        }

        //데이터를 저장할 리스트 생성
        //구문 실행에 성공했을 때 객체를 생성하게 해서
        //리턴하는 값이 null이면 데이터베이스 작업에 실패한 것이고
        //size()가 0이면 데이터가 없다는 것을 구분하기 위한 것입니다.
        list = new ArrayList<IoTDevice>();

        //데이터를 읽어서 list에 저장
        try {
            while (rs.next()) {
                //DTO 클래스의 객체 생성. (모든 데이터가 DTO클래스에 들어있으므로)
                IoTDevice device = new IoTDevice();

                //DTO클래스의 변수에 값을 세팅하기 위해 Set메서드를 이용하고.
                //Select의 결과를 컬럼 단위로 읽어오기 위해서 'get변수타입(컬럼명)' 메서드를 이용
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
}
