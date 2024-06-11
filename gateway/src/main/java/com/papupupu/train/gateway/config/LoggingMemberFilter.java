package com.papupupu.train.gateway.config;

import com.papupupu.train.gateway.util.JwtUtil;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingMemberFilter implements GlobalFilter, Ordered {
    private static final Logger LOG = LoggerFactory.getLogger(LoggingMemberFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //设置对应的不需要校验
        String path = exchange.getRequest().getURI().getPath();
        if(path.contains("/admin")
                || path.contains("/member/member/login")
                || path.contains("/member/member/register")
                || path.contains("/member/member/send-code")
//                || path.contains("/member/passenger")
        ) {
            LOG.info("不需要校验token");
            return chain.filter(exchange);
        }else {
           LOG.info("需要进行token校验");
        }

        String token = exchange.getRequest().getHeaders().getFirst("token");
        LOG.info("会员登录验证开始，token：{}", token);
        if(StringUtil.isNullOrEmpty(token)){
            LOG.info("token为空, 请求被拦截");
            //设置response的返回码是未验证
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        if(JwtUtil.validate(token)){
            LOG.info("token有效");
            return chain.filter(exchange);
        } else {
            LOG.info("token无效, 请求被拦截");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

    }


    @Override
    public int getOrder() {
        return 0;
    }
}
