package com.sjq.edu.client;

import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author Kemp
 * @create 2022/4/15 19:41
 */
@Component
@FeignClient(name = "service-ucenter",fallback = ucenterClientImpl.class)
public interface ucenterClient {

    @PostMapping("/ucenter/member/getInfoUc/{id}")
    public UcenterMemberVo getInfoUcccc(@PathVariable("id") String id);

}
