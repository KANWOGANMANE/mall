package com.sjq.commonutils.vo;

import lombok.Data;

/**
 * @Author Kemp
 * @create 2022/4/6 12:17
 * 小节
 */
@Data
public class TwoChapter {
    private String id;
    private  String title;
    private String videoSourceId;//视频id
    private Boolean isFree;
}
