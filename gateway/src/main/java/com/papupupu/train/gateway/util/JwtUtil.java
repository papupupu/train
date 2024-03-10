package com.papupupu.train.gateway.util;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class JwtUtil {
    private static Logger LOG = LoggerFactory.getLogger(JwtUtil.class);
    private static final String key = "papupupu_train12306";

    public static String createToken(Long id, String mobile){
        LOG.info("开始生成token， id:{}, mobile:{}", id, mobile);
        //此处的作用不详
//        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        HashMap<String, Object> payLoad = new HashMap<>();
        LocalDateTime now = LocalDateTime.now();
        //设置token为24小时过期
        LocalDateTime expire = now.plus(24, ChronoUnit.HOURS);

        //token注册时间
        payLoad.put(JWTPayload.ISSUED_AT, now);
        //token生效时间
        payLoad.put(JWTPayload.NOT_BEFORE, now);
        //token过期时间
        payLoad.put(JWTPayload.EXPIRES_AT, expire);
        payLoad.put("id", id);
        payLoad.put("mobile", mobile);


        String token = JWTUtil.createToken(payLoad, key.getBytes());
        LOG.info("生成JWT token:{}", token);
        return token;
    }

    public static boolean validate(String token){
        LOG.info("开始检验token:{}", token);
//        boolean verify = JWTUtil.verify(token, key.getBytes());
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());

        //此处的参数leeway指的是偏差，表示可以容忍的偏差时间,单位是秒
        boolean verify = jwt.validate(0);
        LOG.info("检验token:{}， 结果为:{}", token, verify);
        return verify;
    }

    public static JSONObject getJSONObject(String token){
        LOG.info("开始获取token的原始内容");
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        boolean validate = jwt.validate(0);
        LOG.info("token是否过期:{}", !validate);
        if(!validate){
            return null;
        }
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        payloads.remove(JWTPayload.EXPIRES_AT);

        LOG.info("根据token获取原始内容：{}", payloads);
        return payloads;
    }


    public  static void main(String[] args) {
        String token = createToken(34L, "18670142513");
        System.out.println(token);
        boolean validate = validate(token);
        System.out.println(validate);
        JSONObject jsonObject = getJSONObject(token);
        System.out.println(jsonObject);
    }
}
