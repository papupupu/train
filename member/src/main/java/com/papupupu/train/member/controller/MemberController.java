package com.papupupu.train.member.controller;

import com.papupupu.train.member.mapper.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService service;

    @GetMapping("/count")
    public Integer count(){
        return service.count();
    }
}
