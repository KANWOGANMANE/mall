package com.sjq.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


/**
 * @Author Kemp
 * @create 2022/4/12 19:45
 * 短信服务
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(value = {"com.sjq.servicebase.config","com.sjq.msm"})
public class MsmApplication {
    public static void main(String[] args) {
        SpringApplication.run(MsmApplication.class, args);
    }
}
