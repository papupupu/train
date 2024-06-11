package com.papupupu.train.member.req;

import com.papupupu.train.common.req.PageReq;

public class PassengerQueryReq extends PageReq {
    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer("PassengerQueryReq{");
        buffer.append("memberId=").append(memberId);
        buffer.append('}');
        return buffer.toString();
    }
}