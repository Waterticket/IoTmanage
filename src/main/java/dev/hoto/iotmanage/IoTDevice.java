package dev.hoto.iotmanage;

import java.io.Serializable;

public class IoTDevice implements Serializable {
    private int id; // 데이터 id
    private String type; // 기기 종류
    private String nickname; // 기기 별명
    private String owner; // 기기 주인
    private int power; // 기기 전원 상태 (0 or 1)
    private int status; // 기기 상태

    public IoTDevice() {
        super();
    }

    public IoTDevice(String type, String nickname, String owner, int power, int status) {
        super();
        this.type = type;
        this.nickname = nickname;
        this.owner = owner;
        this.power = power;
        this.status = status;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
