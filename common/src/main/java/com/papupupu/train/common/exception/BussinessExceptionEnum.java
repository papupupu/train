package com.papupupu.train.common.exception;



public enum BussinessExceptionEnum {
    MEMBER_MOBILE_EXIT("手机号已注册"),
    MEMBER_MOBILE_NOT_EXIT("请请求发送验证码"),
    SEND_CODE_ERROR("发送验证码错误");


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
