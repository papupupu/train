package com.papupupu.train.member.service;

import com.papupupu.train.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberMapper mapper;

    public int count(){
        return Math.toIntExact(mapper.countByExample(null));
    }
}
