package com.papupupu.train.member.controller;

import cn.hutool.core.util.ObjectUtil;
import com.papupupu.train.common.context.LoginMemberContext;
import com.papupupu.train.common.resp.CommonResp;
import com.papupupu.train.common.resp.PageResp;
import com.papupupu.train.member.req.PassengerQueryReq;
import com.papupupu.train.member.req.PassengerSaveReq;
import com.papupupu.train.member.resp.PassengerQueryResp;
import com.papupupu.train.member.service.PassageService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Resource
    private PassageService passageService;

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody PassengerSaveReq passengerSaveReq){
        passageService.save(passengerSaveReq);
        return CommonResp.success();
    }

    @GetMapping("/query-list")
    public CommonResp<PageResp<PassengerQueryResp>> queryList(@Valid PassengerQueryReq passengerQueryReq){
        if(ObjectUtil.isNotNull(passengerQueryReq)){
            passengerQueryReq.setMemberId(LoginMemberContext.getId());
        }
        PageResp<PassengerQueryResp> passengerQueryRespPageResp = passageService.queryList(passengerQueryReq);
        return CommonResp.success(passengerQueryRespPageResp);
    }


    @DeleteMapping("/delete/{id}")
    public CommonResp<Object> delete(@PathVariable Long id){
        passageService.delete(id);
        return CommonResp.success();
    }
}
