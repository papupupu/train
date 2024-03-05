package com.papupupu.train.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@ComponentScan("com.papupupu")
public class GatewayApplication {

    private  static final Logger LOG = LoggerFactory.getLogger(GatewayApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(GatewayApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        LOG.info("启动成功‼");
            LOG.info("地址: \thttp://localhost:{}", environment.getProperty("server.port"));
//        SpringApplication.run(MemberApplication.class, args);
    }

}
