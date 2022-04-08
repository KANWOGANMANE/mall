package com.sjq.edu.service;

import com.sjq.commonutils.vo.OneChapter;
import com.sjq.edu.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
public interface IEduChapterService extends IService<EduChapter> {

    List<OneChapter> getChapterAndVideoBycid(String courseId);

    boolean deleteChapter(String chapterId);

    boolean removeChapterByCourseId(String cid);
}
