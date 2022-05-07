package com.sjq.edu.mapper;

import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduCourseCollect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 课程收藏 Mapper 接口
 * </p>
 *
 * @author kemp
 * @since 2022-05-03
 */
public interface EduCourseCollectMapper extends BaseMapper<EduCourseCollect> {

    List<EduCourse> selectcollection(String memberid);
}
