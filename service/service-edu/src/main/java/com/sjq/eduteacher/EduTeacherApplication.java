package com.sjq.eduteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author Kemp
 * @create 2022/1/13 17:06
 */
@SpringBootApplication
@ComponentScan(value = {"com.sjq.servicebase.config", "com.sjq.eduteacher.config"})
public class EduTeacherApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduTeacherApplication.class, args);
    }
}
