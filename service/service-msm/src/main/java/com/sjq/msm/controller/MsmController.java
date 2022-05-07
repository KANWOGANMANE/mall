package com.sjq.msm.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.api.R;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.utils.RandomUtil;
import com.sjq.msm.config.RedisTemplate;
import com.sjq.msm.config.RedisUtil;
import com.sjq.msm.service.MsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Kemp
 * @create 2022/4/12 19:47
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisUtil redisUtil;


    @GetMapping("send/{phone}")
    public Result sendMsm(@PathVariable String phone){
        String code = (String)redisUtil.get(phone);
        if(StringUtils.isNotBlank(code)) {
            return Result.ok(code);
        }
        code = RandomUtil.getSixBitRandom();
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("code",code);
        boolean a =  msmService.send(params,phone);
        if(!a){
            return Result.fail("发送短信失败");
        }
        redisUtil.set(phone,code,300);
        return Result.ok();
    }
}
