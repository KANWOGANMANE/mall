package com.sjq.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Kemp
 * @create 2022/1/13 17:06
 * 讲师、课程、课程分类、评论、上传
 */
@SpringBootApplication
@ComponentScan(value = {"com.sjq.servicebase.config", "com.sjq.edu"})
@EnableDiscoveryClient
@EnableFeignClients
public class EduTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduTeacherApplication.class, args);
    }
}
