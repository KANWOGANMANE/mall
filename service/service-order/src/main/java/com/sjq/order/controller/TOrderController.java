package com.sjq.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.utils.JwtUtils;
import com.sjq.order.entity.TOrder;
import com.sjq.order.service.ITOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class TOrderController {

    @Autowired
    private ITOrderService orderService;

    @PostMapping("createOrder/{courseId}")
    public Result createOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberid = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.createOrder(courseId,memberid);
        return Result.ok(orderNo);
    }

    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable String courseId, @PathVariable String memberId){
        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("course_id",courseId);
        qw.eq("member_id",memberId);
        qw.eq("status",1);
        int count = orderService.count(qw);
        return count>0?true:false;
    }

    @GetMapping("getOrderInfoByOrderId/{orderId}")
    public Result getOrderInfoByOrderId(@PathVariable String orderId){
        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("order_no",orderId);
        TOrder one = orderService.getOne(qw);
        if(one == null) return Result.fail();
        return Result.ok(one);
    }



}
