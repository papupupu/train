package com.papupupu.train.common.resp;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class CommonResp<T> {
    private String message;
    private boolean success = true;
    private T content;
    private Map<String, Object> map = new HashMap<>();

    private CommonResp(){}

    public static CommonResp success(){
        return new CommonResp();
    }

    public  static<T> CommonResp<T> success(T content){
        CommonResp<T> resp = new CommonResp<>();
        resp.setContent(content);
        return resp;
    }

    public static<T>  CommonResp<T> success(String message, T content) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setMessage(message);
        resp.setContent(content);
        return  resp;
    }

    public static CommonResp error(){
        return new CommonResp().setSuccess(false);
    }

    public static<T> CommonResp<T> error(T content){
        CommonResp<T> resp = new CommonResp<>();
        resp.setSuccess(false);
        resp.setContent(content);
        return resp;
    }

    public static<T>  CommonResp<T> error(String message, T content) {
        CommonResp<T> resp = new CommonResp<>();
        resp.setSuccess(false);
        resp.setMessage(message);
        resp.setContent(content);
        return  resp;
    }

}
