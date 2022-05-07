package com.sjq.edu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.vo.EduTeacherVo;
import com.sjq.edu.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author Kemp
 * @since 2022-01-14
 */

public interface IEduTeacherService extends IService<EduTeacher> {

    public List<EduTeacher> findAllTeachers();

    public IPage pageTeacherCondition(IPage page, EduTeacherVo eduTeacherVo);

    public boolean insertTeacher(EduTeacherVo eduTeacherVo);

    public EduTeacher getByids(String id);

    List<EduTeacher> getHotTeacher();

    Map<String, Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
