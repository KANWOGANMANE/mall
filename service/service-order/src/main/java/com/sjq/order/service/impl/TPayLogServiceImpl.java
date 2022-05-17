package com.sjq.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.sjq.commonutils.result.Result;
import com.sjq.order.entity.AliPayProperties;
import com.sjq.order.entity.TPayLog;
import com.sjq.order.mapper.TPayLogMapper;
import com.sjq.order.service.ITPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements ITPayLogService {

    @Autowired
    private AliPayProperties aliPayProperties;

    @Override
    public Map<String, String> queryPayStatus(String orderNo) throws AlipayApiException {
        AlipayClient alipayClient = new DefaultAlipayClient(
                aliPayProperties.getGatewayUrl(),
                aliPayProperties.getAppId(),
                aliPayProperties.getMerchantPrivateKey(),
                "json",
                aliPayProperties.getCharset(),
                aliPayProperties.getAlipayPublicKey(),
                aliPayProperties.getSignType()
        );
        AlipayTradeQueryRequest tradeQueryRequest = new AlipayTradeQueryRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no",orderNo);
        tradeQueryRequest.setBizContent(bizContent.toString());
        AlipayTradeQueryResponse execute = alipayClient.execute(tradeQueryRequest);
        String body = execute.getBody();
        Map<String, String> re = new HashMap<>();
        re.put("res",body);
        return re;
    }

    @Override
    public void updateOrdersStatus(Map<String, String> map) {

    }
}
