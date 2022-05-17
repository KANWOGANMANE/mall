package com.sjq.edu.mapper;

import com.sjq.edu.entity.EduCourse;
import com.sjq.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author Kemp
 * @since 2022-01-14
 */
@Mapper
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {

    public EduTeacher getoneByids(Long id);


    List<EduCourse> selectteachercourse(String condition);
}
