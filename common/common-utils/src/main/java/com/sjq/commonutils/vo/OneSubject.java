package com.sjq.commonutils.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kemp
 * @create 2022/4/1 15:39
 * 一级分类,课程分类管理
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
