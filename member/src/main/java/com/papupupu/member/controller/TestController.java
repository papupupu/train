package com.papupupu.member.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("hellow")
    public String hellow(){
        return "hellow";
    }

    @GetMapping("test")
    public String test(){return "test";}
}