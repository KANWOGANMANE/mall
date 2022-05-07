package com.sjq.statistics.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.sjq.commonutils.result.Result;
import com.sjq.statistics.client.ucenterClient;
import com.sjq.statistics.service.IStatisticsDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
@RestController
@RequestMapping("/edustatistics/sta")
//@CrossOrigin
public class StatisticsDailyController {

    @Autowired
    private IStatisticsDailyService statisticsDailyService;

    //统计某一天注册人数
    @PostMapping("registerCount/{day}")
    public Result registerCount(@PathVariable String day){
        Integer res = statisticsDailyService.registerCount(day);
        return Result.ok(res);
    }

    //图表显示，返回两部分数据，日期json数组，数量json数组
    @GetMapping("showData/{type}/{begin}/{end}")
    public Result showData(@PathVariable String type, @PathVariable String begin,
                      @PathVariable String end) {
        Map<String,Object> map = statisticsDailyService.getShowData(type,begin,end);
        if(map == null) return Result.fail();
        return Result.ok(map);
    }





}
