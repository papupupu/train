package com.papupupu.member.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@ComponentScan("com.papupupu")
public class MemberApplication {

    private  static final Logger LOG = LoggerFactory.getLogger(MemberApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MemberApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        LOG.info("启动成功‼");
            LOG.info("地址: \thttp://localhost:{}{}", environment.getProperty("server.port"), environment.getProperty("server.servlet.context-path"));
//        SpringApplication.run(MemberApplication.class, args);
    }

}
