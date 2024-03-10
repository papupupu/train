package com.papupupu.train.member.controller;

import com.papupupu.train.common.resp.CommonResp;
import com.papupupu.train.member.req.MemberLoginReq;
import com.papupupu.train.member.req.MemberRegisterReq;
import com.papupupu.train.member.req.MemberSendCodeReq;
import com.papupupu.train.member.resp.MemberLoginResp;
import com.papupupu.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count(){
        int count = memberService.count();
        return CommonResp.success(count);
    }

    @PostMapping("/register")
    public CommonResp<Long> register(@Valid @RequestBody MemberRegisterReq memberRegisterReq){
        long id = memberService.register(memberRegisterReq);
        return CommonResp.success(id);
    }


    @PostMapping("/send-code")
    public CommonResp sendCode(@Valid @RequestBody MemberSendCodeReq memberSendCodeReq){
        memberService.sendCode(memberSendCodeReq);
        return CommonResp.success();
    }


    @PostMapping("/login")
    public CommonResp<MemberLoginResp> login(@Valid @RequestBody MemberLoginReq memberLoginReq){
        MemberLoginResp memberLoginResp = memberService.login(memberLoginReq);
        return CommonResp.success(memberLoginResp);
    }
}
