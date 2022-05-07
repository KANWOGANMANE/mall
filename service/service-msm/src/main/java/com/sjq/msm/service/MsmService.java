package com.sjq.msm.service;

import java.util.Map;

/**
 * @Author Kemp
 * @create 2022/4/12 19:47
 */
public interface MsmService {
    boolean send(Map<String, Object> params, String phone);
}
