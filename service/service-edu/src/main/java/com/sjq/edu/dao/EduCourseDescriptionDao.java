package com.sjq.edu.dao;

import com.sjq.edu.entity.EduCourseDescription;
import org.springframework.stereotype.Repository;

@Repository
public interface EduCourseDescriptionDao {
    int deleteByPrimaryKey(String id);

    int insert(EduCourseDescription record);

    int insertSelective(EduCourseDescription record);

    EduCourseDescription selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduCourseDescription record);

    int updateByPrimaryKey(EduCourseDescription record);
}
