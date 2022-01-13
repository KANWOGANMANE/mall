package com.sjq.edu.dao;

import com.sjq.edu.entity.EduComment;
import org.springframework.stereotype.Repository;

@Repository
public interface EduCommentDao {
    int deleteByPrimaryKey(String id);

    int insert(EduComment record);

    int insertSelective(EduComment record);

    EduComment selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduComment record);

    int updateByPrimaryKey(EduComment record);
}
