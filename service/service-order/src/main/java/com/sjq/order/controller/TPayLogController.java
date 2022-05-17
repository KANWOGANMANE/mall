package com.sjq.order.controller;


import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.result.Result;
import com.sjq.order.entity.TOrder;
import com.sjq.order.entity.TPayLog;
import com.sjq.order.service.ITOrderService;
import com.sjq.order.service.ITPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sjq.order.entity.AliPayProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
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
public class TPayLogController extends BaseController {

    @Autowired
    private ITPayLogService payLogService;

    @Autowired
    private ITOrderService orderService;

    @Autowired
    private AliPayProperties aliPayProperties;


    @GetMapping("buycourse/{orderNo}")
    public Result buycourse(@PathVariable String orderNo)throws AlipayApiException {
        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("order_no",orderNo);
        TOrder torder = orderService.getOne(qw);
        if(torder != null){
            //1.初始化alipayclient
            AlipayClient alipayClient = new DefaultAlipayClient(
                    aliPayProperties.getGatewayUrl(),
                    aliPayProperties.getAppId(),
                    aliPayProperties.getMerchantPrivateKey(),
                    "json",
                    aliPayProperties.getCharset(),
                    aliPayProperties.getAlipayPublicKey(),
                    aliPayProperties.getSignType()
            );
            //2.设置请求参数
            AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
            alipayRequest.setReturnUrl(aliPayProperties.getReturnUrl());
            alipayRequest.setNotifyUrl(aliPayProperties.getNotifyUrl());
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no",orderNo);
            bizContent.put("total_amount",torder.getTotalFee());
            bizContent.put("subject",torder.getCourseTitle());
            bizContent.put("product_code","FAST_INSTANT_TRADE_PAY");
            alipayRequest.setBizContent(bizContent.toString());
            AlipayTradePagePayResponse payResponse = alipayClient.pageExecute(alipayRequest);
            String form = payResponse.getBody();
//            if(payResponse.isSuccess()){
//                //调用succ
//            }else{
//                //调用fail
//            }
            return Result.ok(form);
        }
        return Result.fail();
    }


    @GetMapping("aliPay/return")
    public void returnCallback(@RequestParam Map<String,String> params) throws IOException, AlipayApiException{
        String out_trade_no = params.get("out_trade_no");
        String total_amount = params.get("total_amount");
        String trade_no = params.get("trade_no");

        QueryWrapper<TOrder> qw = new QueryWrapper<>();
        qw.eq("order_no",out_trade_no);
        qw.eq("status",0);
        TOrder torder = orderService.getOne(qw);
        if(torder != null){
            torder.setPayType(2);
            torder.setStatus(1);
            torder.setGmtModified(LocalDateTime.now());
            orderService.updateById(torder);
        }
        TPayLog pay = new TPayLog();
        pay.setOrderNo(out_trade_no);
        pay.setPayTime(LocalDateTime.now());
        pay.setTotalFee(new BigDecimal(total_amount));
        pay.setTransactionId(trade_no);
        pay.setTradeState("SUCCESS");
        pay.setPayType(2);
        pay.setGmtCreate(LocalDateTime.now());
        pay.setGmtModified(LocalDateTime.now());
        payLogService.save(pay);
        response.sendRedirect("http://localhost:3000/course/"+ torder.getCourseId());
    }

    @PostMapping("aliPay/notify")
    public String notifyCallback(@RequestParam Map<String,String> params){
        String out_trade_no = params.get("out_trade_no");
        return "redirect:"+"/course/";
    }


    //查询订单支付状态
    //参数：订单号，根据订单号查询 支付状态
    @GetMapping("queryPayStatus/{orderNo}")
    public Result queryPayStatus(@PathVariable String orderNo) throws AlipayApiException {
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
