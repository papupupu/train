package com.papupupu.train.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@ComponentScan("com.papupupu")
@MapperScan("com.papupupu.train.*.mapper")
public class BusinessApplication {

    private  static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(BusinessApplication.class);
        ConfigurableEnvironment environment = application.run(args).getEnvironment();
        LOG.info("启动成功‼");
            LOG.info("地址: \thttp://localhost:{}{}", environment.getProperty("server.port"), environment.getProperty("server.servlet.context-path"));
//        SpringApplication.run(MemberApplication.class, args);
    }

}
