package dev.hoto.iotmanage;

import java.io.Serializable;

public class IoTDevice implements Serializable {
    private int id;
    private String type;
    private String nickname;
    private String owner;
    private int power;
    private int status;

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
