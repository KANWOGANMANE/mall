package com.sjq.order.service.impl;

import com.sjq.order.entity.TPayLog;
import com.sjq.order.mapper.TPayLogMapper;
import com.sjq.order.service.ITPayLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-16
 */
@Service
public class TPayLogServiceImpl extends ServiceImpl<TPayLogMapper, TPayLog> implements ITPayLogService {

    @Override
    public Map<String, String> queryPayStatus(String orderNo) {
        return null;
    }

    @Override
    public void updateOrdersStatus(Map<String, String> map) {

    }
}
