package com.sjq.edu.dao;

import com.sjq.edu.entity.EduCourse;
import org.springframework.stereotype.Repository;

@Repository
public interface EduCourseDao {
    int deleteByPrimaryKey(String id);

    int insert(EduCourse record);

    int insertSelective(EduCourse record);

    EduCourse selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduCourse record);

    int updateByPrimaryKey(EduCourse record);
}
