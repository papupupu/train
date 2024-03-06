package com.papupupu.train.common.controller;


import com.papupupu.train.common.exception.BussinessException;
import com.papupupu.train.common.resp.CommonResp;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log
public class ControllerExceptionHandler {


    private static final Logger LOG = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    /**
     * 所有异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResp exceptionHandler(Exception e){
        LOG.error("系统异常:{}", e);
        CommonResp resp = CommonResp.error();
        resp.setMessage("系统出现异常，请联系管理员");
        return resp;
    }


    /**
     * 所有业务异常统一处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = BussinessException.class)
    @ResponseBody
    public CommonResp exceptionHandler(BussinessException e){
        LOG.error("业务异常:{}", e.getE().getDesc());
        CommonResp resp = CommonResp.error();
        resp.setMessage(e.getE().getDesc());
        return resp;
    }

}
