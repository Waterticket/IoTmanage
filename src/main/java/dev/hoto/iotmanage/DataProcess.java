package dev.hoto.iotmanage;

import java.util.List;

public class DataProcess {
    public static void changeModuleTypeToKorean(List<IoTDevice> target) {
        String[] korTypes = {"전등", "창문", "블라인드", "가스"};
        int index = -1;

        for (IoTDevice device :
                target) {
            changeModuleTypeToKorean(device);
        }
    }

    public static void changeModuleTypeToKorean(IoTDevice device) {
        String[] korTypes = {"전등", "창문", "블라인드", "가스"};
        int index = -1;

        switch (device.getType()) {
            case "light":
                index = 0;
                break;

            case "window":
                index = 1;
                break;

            case "blind":
                index = 2;
                break;

            case "gas":
                index = 3;
                break;

            default:
                System.out.println("잘못된 모듈 종류입니다.");
                System.exit(0);
        }

        device.setType(korTypes[index]);
    }

}