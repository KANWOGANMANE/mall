package com.sjq.edu.service;

import com.sjq.commonutils.vo.OneSubject;
import com.sjq.edu.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-01
 */
public interface IEduSubjectService extends IService<EduSubject> {

    void saveSubject(MultipartFile file,IEduSubjectService edusubjectService);

    List<OneSubject> getAllOneTwoSubject();
}
