package com.sjq.edu.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author Kemp
 * @create 2022/4/16 17:58
 */
@Component
@FeignClient(name = "service-order",fallback = orderClientImpl.class)
public interface orderClient {

    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public Boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable("memberId") String memberId);

}
