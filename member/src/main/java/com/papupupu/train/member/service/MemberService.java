package com.papupupu.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.papupupu.train.common.exception.BussinessException;
import com.papupupu.train.common.exception.BussinessExceptionEnum;
import com.papupupu.train.common.util.SnowUtil;
import com.papupupu.train.member.domain.Member;
import com.papupupu.train.member.domain.MemberExample;
import com.papupupu.train.member.mapper.MemberMapper;
import com.papupupu.train.member.req.MemberRegisterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
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
}
