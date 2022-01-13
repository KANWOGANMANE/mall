package com.sjq.edu.dao;

import com.sjq.edu.entity.EduTeacher;
import org.springframework.stereotype.Repository;

@Repository
public interface EduTeacherDao {
    int deleteByPrimaryKey(String id);

    int insert(EduTeacher record);

    int insertSelective(EduTeacher record);

    EduTeacher selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduTeacher record);

    int updateByPrimaryKey(EduTeacher record);
}
