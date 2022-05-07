package com.sjq.edu.service;

import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduCourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-05-03
 */
public interface IEduCourseCollectService extends IService<EduCourseCollect> {

    boolean saveCollection(String courseId, String memberid);

    List<EduCourse> selectcollection(String memberid);
}
