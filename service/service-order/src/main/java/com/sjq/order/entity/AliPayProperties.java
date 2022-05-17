package com.sjq.order.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author Kemp
 * @create 2022/5/7 23:46
 */
@Component
@ConfigurationProperties(prefix = "alipay")
@Data
public class AliPayProperties {
    //appid
    private String appId;
    //商家私钥
    private String merchantPrivateKey;
    //支付宝公钥
    private String alipayPublicKey;
    //# 异步通知的回调地址
    private String notifyUrl;
    //# 同步回调的地址
    private String returnUrl;
    private String signType;
    private String charset;
    private String gatewayUrl;
}
