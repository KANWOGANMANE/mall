package com.sjq.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.vo.CourseFrontVo;
import com.sjq.commonutils.vo.CoursePublishVo;
import com.sjq.commonutils.vo.CourseVo;
import com.sjq.commonutils.vo.CourseWebVo;
import com.sjq.edu.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

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

    List<EduCourse> getHotCourse();

    Map<String, Object> getCourseFontList(Page<EduCourse> pagecourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String id);

    void commentcountadd(String id);

    void viewcountadd(String id);

    Map<String, Object> getCourseCollection(Page<EduCourse> pagecourse, CourseFrontVo courseFrontVo);
}
