package com.sjq.edu.dao;

import com.sjq.edu.entity.EduSubject;
import org.springframework.stereotype.Repository;

@Repository
public interface EduSubjectDao {
    int deleteByPrimaryKey(String id);

    int insert(EduSubject record);

    int insertSelective(EduSubject record);

    EduSubject selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduSubject record);

    int updateByPrimaryKey(EduSubject record);
}
