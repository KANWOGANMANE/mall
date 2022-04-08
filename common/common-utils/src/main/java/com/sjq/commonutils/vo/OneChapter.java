package com.sjq.commonutils.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kemp
 * @create 2022/4/6 12:17
 * 章节
 */
@Data
public class OneChapter {
    private String id;
    private String title;
    List<TwoChapter> children = new ArrayList<>();
}
