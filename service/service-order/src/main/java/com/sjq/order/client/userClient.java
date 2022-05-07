package com.sjq.order.client;

import com.sjq.commonutils.vo.UcenterMemberVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author Kemp
 * @create 2022/4/16 14:35
 */
@Component
@FeignClient("service-ucenter")
public interface userClient {

    @PostMapping("/ucenter/member/getInfoUc/{id}")
    public UcenterMemberVo getInfoUcccc(@PathVariable("id") String id);
}
