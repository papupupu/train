package com.papupupu.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.papupupu.train.common.exception.BussinessException;
import com.papupupu.train.common.exception.BussinessExceptionEnum;
import com.papupupu.train.common.util.SnowUtil;
import com.papupupu.train.member.domain.Member;
import com.papupupu.train.member.domain.MemberExample;
import com.papupupu.train.member.mapper.MemberMapper;
import com.papupupu.train.member.req.MemberLoginReq;
import com.papupupu.train.member.req.MemberRegisterReq;
import com.papupupu.train.member.req.MemberSendCodeReq;
import com.papupupu.train.member.resp.MemberLoginResp;
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
       Member memberDB = getByMobile(mobile);
       if (ObjectUtil.isNotEmpty(memberDB)){
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
        Member memberDB = getByMobile(mobile);

        //手机号不存在，先进行注册
        if (ObjectUtil.isEmpty(memberDB)){
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
        LOG.info("生成短信验证码:{}", "8888");


        //保存短信验证表：，短信的手机号，验证码， 业务类型，过期时间，是否已经使用
        LOG.info("保存短信验证表");

        //接入短信通道
        LOG.info("接入短信通道");
    }
    /**
     * 实现用户登录功能
     * @param memberLoginReq
     * @return 新注册的用户id
     */
    public MemberLoginResp login(MemberLoginReq memberLoginReq){
        String mobile = memberLoginReq.getMobile();

        Member memberDB = getByMobile(mobile);

        //手机号不存在，先进行注册
        if (ObjectUtil.isEmpty(memberDB)){
            throw new BussinessException(BussinessExceptionEnum.MEMBER_MOBILE_NOT_EXIT);
        }

        //校验验证码
       if(!"8888".equals(memberLoginReq.getCode())){
           throw new BussinessException(BussinessExceptionEnum.SEND_CODE_ERROR);
       }

        return BeanUtil.copyProperties(memberDB,  MemberLoginResp.class);
    }

    private Member getByMobile(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> members = memberMapper.selectByExample(memberExample);

        if (CollUtil.isEmpty(members)){
           return null;
        } else {
            return members.get(0);
        }

    }

}
