package com.sjq.statistics.service;

import com.sjq.statistics.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
public interface IStatisticsDailyService extends IService<StatisticsDaily> {

    Integer registerCount(String day);

    Map<String, Object> getShowData(String type, String begin, String end);
}
