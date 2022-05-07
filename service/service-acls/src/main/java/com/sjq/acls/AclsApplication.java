package com.sjq.acls;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Kemp
 * @create 2022/4/18 13:08
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"com.sjq.servicebase.config", "com.sjq.security","com.sjq.acls"})
@MapperScan(basePackages = {"com.sjq.acls.mapper"})
public class AclsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AclsApplication.class, args);
    }
}
