package com.sjq.edu.dao;

import com.sjq.edu.entity.EduChapter;
import org.springframework.stereotype.Repository;

@Repository
public interface EduChapterDao {
    int deleteByPrimaryKey(String id);

    int insert(EduChapter record);

    int insertSelective(EduChapter record);

    EduChapter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(EduChapter record);

    int updateByPrimaryKey(EduChapter record);
}
