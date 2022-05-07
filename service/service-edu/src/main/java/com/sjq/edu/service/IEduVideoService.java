package com.sjq.edu.service;

import com.sjq.commonutils.vo.OneChapter;
import com.sjq.edu.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
public interface IEduVideoService extends IService<EduVideo> {

    boolean removeVideoByCourseId(String cid);

    void viewCountadd(String vid);

    List<OneChapter> getCourseListByVid(String id);
}
