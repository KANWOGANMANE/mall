package com.sjq.commonutils.vo;

import lombok.Data;

/**
 * @Author Kemp
 * @create 2022/4/7 17:01
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
