package com.papupupu.train.member.resp;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberLoginResp {
    private Long id;
    private String mobile;
    private String token;

    @Override
    public String toString() {
        final StringBuffer buffer = new StringBuffer("MemberLoginResp{");
        buffer.append("id='").append(id).append('\'');
        buffer.append(", mobile='").append(mobile).append('\'');
        buffer.append(", token='").append(token).append('\'');
        buffer.append('}');
        return buffer.toString();
    }
}
