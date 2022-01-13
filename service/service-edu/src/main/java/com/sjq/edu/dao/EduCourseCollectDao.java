package com.sjq.edu.dao;

import com.sjq.edu.entity.EduCourseCollect;
import org.springframework.stereotype.Repository;

@Repository
public interface EduCourseCollectDao {
    int deleteByPrimaryKey(String id);

    int insert(EduCourseCollect record);

    int insertSelective(EduCourseCollect record);

    EduCourseCollect selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduCourseCollect record);

    int updateByPrimaryKey(EduCourseCollect record);
}
