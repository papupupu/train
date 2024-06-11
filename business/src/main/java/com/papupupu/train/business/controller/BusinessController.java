package com.papupupu.train.business.controller;


import com.papupupu.train.common.resp.CommonResp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @GetMapping("/test")
    public CommonResp<String> test(){
        return CommonResp.success("hellow");
    }
}
