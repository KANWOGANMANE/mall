package com.sjq.order.service;

import com.sjq.order.entity.EduCourse;
import com.sjq.order.entity.TOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    List<EduCourse> gethasBuyCourse(String memberid);
}
