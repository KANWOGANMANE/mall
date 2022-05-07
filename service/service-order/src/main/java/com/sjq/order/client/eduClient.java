package com.sjq.order.client;

import com.sjq.commonutils.vo.CourseWebVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author Kemp
 * @create 2022/4/16 14:34
 */
@Component
@FeignClient("service-edu")
public interface eduClient {

    @PostMapping("/eduservice/course/getCourseInfoOrder/{id}")
    public CourseWebVo getCourseInfoOrder(@PathVariable("id") String id);

}
