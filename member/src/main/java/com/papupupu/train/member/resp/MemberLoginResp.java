package com.papupupu.train.member.resp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResp {
    private String id;
    private String mobile;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MemberLoginResp{");
        sb.append("id='").append(id).append('\'');
        sb.append(", mobile='").append(mobile).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
