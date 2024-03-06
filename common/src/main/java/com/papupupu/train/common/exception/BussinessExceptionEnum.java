package com.papupupu.train.common.exception;



public enum BussinessExceptionEnum {
    MEMBER_MOBILE_EXIT("手机号已注册");


    BussinessExceptionEnum(String desc){
        this.desc = desc;
    };

    private String desc;
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BussinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }
}
