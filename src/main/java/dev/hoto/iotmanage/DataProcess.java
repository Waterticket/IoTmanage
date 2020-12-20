package dev.hoto.iotmanage;

import java.util.List;

//컨트롤러에서 필요한 데이터 처리 기능
public class DataProcess {
    //IoTDevice 리스트의 type을 한글로 변경
    public static void changeModuleTypeToKorean(List<IoTDevice> target) {
        String[] korTypes = {"전등", "창문", "블라인드", "가스"};
        int index = -1;

        for (IoTDevice device :
                target) {
            changeModuleTypeToKorean(device);
        }
    }

    //IoTDevice 객체의 type을 한글로 변경
    public static void changeModuleTypeToKorean(IoTDevice device) {
        String[] korTypes = {"전등", "창문", "블라인드", "가스"}; //대응하는 한글리스트
        int index = -1;

        //대응하는 korTypes 값의 인덱스 지정
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
                //없으면 DB 오류로 프로그램 종료
                System.out.println("잘못된 모듈 종류입니다.");
                System.exit(0);
        }

        device.setType(korTypes[index]);
    }

}