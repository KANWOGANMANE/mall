package com.sjq.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sjq.commonutils.vo.CoursePublishVo;
import com.sjq.commonutils.vo.CourseVo;
import com.sjq.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
public interface IEduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseVo courseVo);

    CourseVo getCourseInfo(String courseId);

    boolean updateCourseInfo(CourseVo courseVo);

    CoursePublishVo publicshCourseInfo(String id);

    IPage pageCourseCondition(IPage<EduCourse> page, EduCourse eduCourse);

    boolean deleteCourse(String cid);
}
