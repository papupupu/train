package com.papupupu.train.member.controller;

import com.papupupu.train.common.resp.CommonResp;
import com.papupupu.train.member.req.MemberRegisterReq;
import com.papupupu.train.member.req.MemberSendCodeReq;
import com.papupupu.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp count(){
        int count = memberService.count();
        CommonResp<Integer> resp = CommonResp.success(count);
        return resp;
    }

    @PostMapping("/register")
    public CommonResp register(@Valid MemberRegisterReq memberRegisterReq){
        long id = memberService.register(memberRegisterReq);
        CommonResp resp = CommonResp.success(id);
        return resp;
    }

    @PostMapping("/send-code")
    public CommonResp sendCode(MemberSendCodeReq memberSendCodeReq){
        memberService.sendCode(memberSendCodeReq);
        CommonResp resp = CommonResp.success();
        return resp;
    }



}
