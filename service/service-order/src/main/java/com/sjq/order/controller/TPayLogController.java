package com.sjq.order.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.result.Result;
import com.sjq.order.entity.TOrder;
import com.sjq.order.entity.TPayLog;
import com.sjq.order.service.ITOrderService;
import com.sjq.order.service.ITPayLogService;
import com.sjq.order.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/eduorder/paylog")
//@CrossOrigin
public class TPayLogController {

    @Autowired
    private ITPayLogService payLogService;

    @Autowired
    private ITOrderService orderService;


    @GetMapping("buycourse/{orderNo}")
    public Result buycourse(@PathVariable String orderNo){
        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("order_no",orderNo);
        TOrder torder = orderService.getOne(qw);
        TPayLog pay = new TPayLog();
        pay.setOrderNo(orderNo);
        pay.setGmtCreate(LocalDateTime.now());
        pay.setGmtModified(LocalDateTime.now());
        pay.setPayTime(LocalDateTime.now());
        if (torder != null) {
            torder.setStatus(1);
            torder.setGmtModified(LocalDateTime.now());
            pay.setTotalFee(torder.getTotalFee());
        }
        boolean update = orderService.update(torder,qw);
        boolean save = false;
        if(update){
            pay.setTransactionId(OrderNoUtil.getOrderNo());
            pay.setTradeState("SUCCESS");
            pay.setPayType(1);
            save = payLogService.save(pay);
        }
        return save?Result.ok():Result.fail();
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo){
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        if(map == null) {
            return Result.fail("支付错误");
        }
        if(map.get("trade_state").equals("SUCCESS")) {//支付成功
            //添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return Result.ok("支付成功");
        }
        return Result.ok("支付中");
    }

}
