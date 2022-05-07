package com.sjq.order.service;

import com.sjq.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
public interface ITOrderService extends IService<TOrder> {

    String createOrder(String courseId, String token);
}
