package com.sjq.edu.mapper;

import com.sjq.commonutils.vo.CoursePublishVo;
import com.sjq.commonutils.vo.CourseWebVo;
import com.sjq.edu.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String courseId);

    CourseWebVo getBaseCourseInfo(String id);
}
