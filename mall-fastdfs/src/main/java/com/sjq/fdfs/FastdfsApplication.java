package com.sjq.fdfs;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 * @Author Kemp
 * @create 2022/1/6 15:05
 */
@SpringBootApplication
@Import(FdfsClientConfig.class)
@EnableDiscoveryClient
public class FastdfsApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastdfsApplication.class,args);
    }
}
