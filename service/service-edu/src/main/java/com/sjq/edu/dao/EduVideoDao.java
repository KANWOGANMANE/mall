package com.sjq.edu.dao;

import com.sjq.edu.entity.EduVideo;
import org.springframework.stereotype.Repository;

@Repository
public interface EduVideoDao {
    int deleteByPrimaryKey(String id);

    int insert(EduVideo record);

    int insertSelective(EduVideo record);

    EduVideo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduVideo record);

    int updateByPrimaryKey(EduVideo record);
}
