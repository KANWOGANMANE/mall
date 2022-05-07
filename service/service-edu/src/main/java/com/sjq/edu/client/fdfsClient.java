package com.sjq.edu.client;

import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.DleVideoVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author Kemp
 * @create 2022/4/10 17:05
 */
@Component
@FeignClient(name = "service-fdfs",fallback = fdfsClientImpl.class)
public interface fdfsClient {

    @PostMapping("/edufdfs/deleteFile")
    Result deleteFile(@RequestBody DleVideoVo grouppath);

    @PostMapping("/edufdfs/deleteBath")
    Result deleteBath(@RequestParam("pathList") List<String> pathList);
}
