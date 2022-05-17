package com.sjq.order.mapper;

import com.sjq.order.entity.EduCourse;
import com.sjq.order.entity.TOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 订单 Mapper 接口
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
public interface TOrderMapper extends BaseMapper<TOrder> {

    List<EduCourse> selecthasbuylist(String memberid);
}
