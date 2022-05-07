package com.sjq.statistics.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * @Author Kemp
 * @create 2022/4/15 19:41
 */
@Component
@FeignClient(name = "service-ucenter",fallback = ucenterClientImpl.class)
public interface ucenterClient {

    @GetMapping("/ucenter/member/countRegister/{day}")
    public Integer countRegister(@PathVariable("day") String day);

}
