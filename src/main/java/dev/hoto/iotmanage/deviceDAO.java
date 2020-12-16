package dev.hoto.iotmanage;

import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class deviceDAO {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;

    // 데이터베이스 연결을 수행해주는 메서드.
    private boolean connect() {
        boolean result = false;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MariaDB: org.mariadb.jdbc.Driver

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/dimigo" +
                    "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Asia/Seoul","root","q1w2e3r4");

            result = true;
        } catch (ClassNotFoundException e) {
            System.out.println("클래스 로드 실패 : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("연결 실패 : " + e.getMessage());
        }

        return result;
    }

    // 데이터베이스 연결을 해제하는 메서드
    private void close() {
        try {
            if (rs != null)
                rs.close();
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        } catch (Exception e) {
            System.out.println("해제 실패 : " + e.getMessage());
        }
    }

    // 디바이스 리스트 가져오기
    public ResultSet getDevices() {
        String sql = "SELECT * FROM iot_devices";

        if (this.connect()) {
            try {
                stmt = conn.createStatement();
                if(stmt != null){ // DB에 접속했을 경우
                    rs = stmt.executeQuery(sql);
                    // 데이터를 받아와 ResultSet에 저장
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // 연결에 실패했을 때 작업
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return rs; // ResultSet 리턴
    }

    //iot_devices 테이블에 데이터를 삽입하는 메서드
    public boolean InsertDevice(IoTDevice device){
        boolean result = false;

        if(this.connect()){
            try {
                String sql = "INSERT INTO iot_devices(type,nickname,owner,power) VALUES (?,?,?,?);";
                PreparedStatement pstmt = conn.prepareStatement(sql); // 데이터를 넣기 위해 prepared statement 삽입

                pstmt.setString(1, device.getType()); // 각 ?에 값을 집어 넣는다
                pstmt.setString(2, device.getNickname());
                pstmt.setString(3, device.getOwner());
                pstmt.setInt(4, device.getPower());

                int r = pstmt.executeUpdate();

                if(r>0){ // 쿼리를 성공했을경우 양수 리턴
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }else {
            System.out.println("데이터베이스 연결 실패");
            System.exit(0);
        }

        return result;
    }

    // id를 사용해 디바이스 제거
    public boolean DeleteDeviceById(int moduleId)
    {
        boolean result = false;

        if(this.connect()){ // DB에 연결되었을 경우
            try {
                String sql = "DELETE FROM iot_devices WHERE id = ?;"; // id가 같은 데이터 제거
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, moduleId);

                int r = pstmt.executeUpdate();

                if(r>0){
                    result = true;
                }

                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }else {
            System.out.println("데이터베이스 연결 실패");
            System.exit(0);
        }

        return result;
    }

    // 디바이스 리스트 가져오기
    public ResultSet getDeviceById(int moduleId) {
        String sql = "SELECT * FROM iot_devices WHERE id = ?";

        if (this.connect()) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, moduleId);

                if(pstmt != null){ // DB에 접속했을 경우
                    rs = pstmt.executeQuery();
                    // 데이터를 받아와 ResultSet에 저장
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // 연결에 실패했을 때 작업
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return rs; // ResultSet 리턴
    }

    // 이름으로 디바이스 검색
    public ResultSet searchDevicesByName(String name)
    {
        String sql = "SELECT * FROM iot_devices WHERE nickname LIKE ?";

        if (this.connect()) {
            try {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, "%"+name+"%");

                if(pstmt != null){ // DB에 접속했을 경우
                    rs = pstmt.executeQuery();
                    // 데이터를 받아와 ResultSet에 저장
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            // 연결에 실패했을 때 작업
            System.out.println("데이터베이스 연결에 실패했습니다.");
            System.exit(0);
        }

        return rs; // ResultSet 리턴
    }

    // 디바이스 데이터 수정
    public boolean ModifyDevice(IoTDevice device)
    {
        boolean result = false;

        if(this.connect()){
            try {
                String sql = "UPDATE iot_devices SET type = ?, nickname = ?, owner = ?, power = ?, WHERE id = ?;";
                PreparedStatement pstmt = conn.prepareStatement(sql); // 데이터를 넣기 위해 prepared statement 삽입

                pstmt.setString(1, device.getType()); // 각 ?에 값을 집어 넣는다
                pstmt.setString(2, device.getNickname());
                pstmt.setString(3, device.getOwner());
                pstmt.setInt(4, device.getPower());
                pstmt.setInt(5, device.getId());

                int r = pstmt.executeUpdate();

                if(r>0){ // 쿼리를 성공했을경우 양수 리턴
                    result = true;
                }
                //데이터베이스 생성 객체 해제
                pstmt.close();
                this.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        }else {
            System.out.println("데이터베이스 연결 실패");
            System.exit(0);
        }

        return result;
    }
}