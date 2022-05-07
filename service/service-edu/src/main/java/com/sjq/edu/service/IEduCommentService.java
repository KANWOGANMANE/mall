package com.sjq.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.edu.entity.EduComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-15
 */
public interface IEduCommentService extends IService<EduComment> {

    Map<String, Object> getCourseList(Long page, Long limit, String courseid);
}
