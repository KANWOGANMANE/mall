package com.sjq.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author Kemp
 * @create 2022/5/8 13:05
 */
@Controller
public class AliPayCallbackController {

    @PostMapping("eduorder/aliPay/notify")
    public String notifyCallback(@RequestParam Map<String,String> params){
        String out_trade_no = params.get("out_trade_no");
        return "redirect:"+"/course/"+out_trade_no;
    }


}
