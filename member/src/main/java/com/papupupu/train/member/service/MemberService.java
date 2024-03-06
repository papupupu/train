package com.papupupu.train.member.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.papupupu.train.common.exception.BussinessException;
import com.papupupu.train.common.exception.BussinessExceptionEnum;
import com.papupupu.train.common.util.SnowUtil;
import com.papupupu.train.member.domain.Member;
import com.papupupu.train.member.domain.MemberExample;
import com.papupupu.train.member.mapper.MemberMapper;
import com.papupupu.train.member.req.MemberRegisterReq;
import com.papupupu.train.member.req.MemberSendCodeReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    Logger LOG = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberMapper memberMapper;

    public int count(){
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    /**
     * 实现用户注册功能
     * @param memberRegisterReq
     * @return 新注册的用户id
     */
   public long register(MemberRegisterReq memberRegisterReq){
       String mobile = memberRegisterReq.getMobile();
       MemberExample memberExample = new MemberExample();
       memberExample.createCriteria().andMobileEqualTo(mobile);
       List<Member> members = memberMapper.selectByExample(memberExample);
       if (CollUtil.isNotEmpty(members)){
           throw new BussinessException(BussinessExceptionEnum.MEMBER_MOBILE_EXIT);
       }
       Member member = new Member();
       member.setId(SnowUtil.getSnowflakeNextId());
       member.setMobile(mobile);
       memberMapper.insert(member);
       return member.getId();
   }

    /**
     * 实现用户发送验证码功能
     * @param memberSendCodeReq
     * @return 新注册的用户id
     */
    public void sendCode(MemberSendCodeReq memberSendCodeReq){
        String mobile = memberSendCodeReq.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);

        //手机号不存在，先进行注册
        if (CollUtil.isEmpty(members)){
            LOG.info("手机号不存在，先进行注册");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号存在");
        }

        //生成验证码
        String code = RandomUtil.randomString(4);
        LOG.info("生成短信验证码:{}", code);


        //保存短信验证表：，短信的手机号，验证码， 业务类型，过期时间，是否已经使用
        LOG.info("保存短信验证表");

        //接入短信通道
        LOG.info("接入短信通道");
    }

}
