package com.sjq.edu.client;

import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.DleVideoVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Kemp
 * @create 2022/4/10 18:46
 */
/*
该类的方法在触发熔断机制后执行
熔断器hytris加在消费者上
 */
@Component
public class fdfsClientImpl implements fdfsClient{
    @Override
    public Result deleteFile(DleVideoVo grouppath) {
        return Result.fail("删除视频失败");
    }

    @Override
    public Result deleteBath(List<String> pathList) {
        return Result.fail("删除视频失败");
    }
}
