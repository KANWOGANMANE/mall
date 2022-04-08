package com.sjq.fdfs.store.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author Kemp
 * @create 2022/3/29 16:56
 */
@Component
public class ServeDefaultData implements InitializingBean {

    @Value("${fastdfs.serve.portal}")
    private String portal;

    @Value("${fastdfs.serve.ip}")
    private String ip;

    @Value("${fastdfs.serve.port}")
    private String port;

    public static String FIP;

    public static String FPORTAL;

    public static String FPORT;

    @Override
    public void afterPropertiesSet() throws Exception {
        FPORTAL = portal;
        FIP = ip;
        FPORT = port;
    }
}
