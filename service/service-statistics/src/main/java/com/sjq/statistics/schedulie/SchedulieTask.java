package com.sjq.statistics.schedulie;

import com.sjq.statistics.service.IStatisticsDailyService;
import com.sjq.statistics.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Author Kemp
 * @create 2022/4/17 20:46
 */
@Component
public class SchedulieTask {

    @Autowired
    private IStatisticsDailyService statisticsDailyService;

    //在每天凌晨1点，把前一天数据进行数据查询添加
    @Scheduled(cron = "0 0 1 * * ?")
    public void task(){
        statisticsDailyService.registerCount(DateUtil.formatDate(DateUtil.addDays(new Date(), -1)));
    }




}
