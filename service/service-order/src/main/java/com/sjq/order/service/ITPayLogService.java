package com.sjq.order.service;

import com.alipay.api.AlipayApiException;
import com.sjq.order.entity.TPayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
public interface ITPayLogService extends IService<TPayLog> {

    Map<String, String> queryPayStatus(String orderNo) throws AlipayApiException;

    void updateOrdersStatus(Map<String, String> map);
}
