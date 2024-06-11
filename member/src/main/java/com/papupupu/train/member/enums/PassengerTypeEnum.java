package com.papupupu.train.member.enums;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public enum PassengerTypeEnum {
    ADULT("1", "成人"),
    CHILD("2", "儿童"),
    STUDENT("3", "学生");

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    PassengerTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<Map<String, String>> getEnumList(){
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for(PassengerTypeEnum anEnum : EnumSet.allOf(PassengerTypeEnum.class)){
            Map<String, String> map = new HashMap<>();
            map.put("code" , anEnum.getCode());
            map.put("desc" , anEnum.getDesc());
            list.add(map);
        }
        return list;
    }

}
