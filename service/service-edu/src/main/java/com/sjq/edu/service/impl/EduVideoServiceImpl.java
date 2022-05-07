package com.sjq.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.commonutils.vo.DleVideoVo;
import com.sjq.commonutils.vo.OneChapter;
import com.sjq.edu.client.fdfsClient;
import com.sjq.edu.entity.EduUpload;
import com.sjq.edu.entity.EduVideo;
import com.sjq.edu.mapper.EduVideoMapper;
import com.sjq.edu.service.IEduChapterService;
import com.sjq.edu.service.IEduCourseService;
import com.sjq.edu.service.IEduUploadService;
import com.sjq.edu.service.IEduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements IEduVideoService {

    @Autowired
    private fdfsClient fdfsClient;

    @Autowired
    private IEduUploadService uploadService;

    @Autowired
    private IEduCourseService eduCourseService;

    @Autowired
    private IEduChapterService eduChapterService;

    @Override
    public boolean removeVideoByCourseId(String cid) {
        QueryWrapper<EduVideo> qwEdu = new QueryWrapper();
        qwEdu.eq("course_id",cid);
        qwEdu.select("video_source_id");
        List<EduVideo> eduVideos = baseMapper.selectList(qwEdu);
        if(eduVideos != null){
            for(EduVideo a:eduVideos){
                EduUpload i = uploadService.getById(a.getVideoSourceId());
                if(i != null){
                    fdfsClient.deleteFile(new DleVideoVo(i.getFgroup(),i.getFpath()));
                    uploadService.removeById(i.getId());
                }
            }
        }
        QueryWrapper<EduVideo> qw = new QueryWrapper();
        qw.eq("course_id",cid);
        int delete = baseMapper.delete(qw);
        return delete>0?true:false;
    }

    @Override
    public void viewCountadd(String vid) {
        QueryWrapper<EduVideo> qw = new QueryWrapper();
        qw.eq("video_source_id",vid);
        EduVideo eduVideo = baseMapper.selectOne(qw);
        String courseId = eduVideo.getCourseId();
        eduCourseService.viewcountadd(courseId);
    }

    @Override
    public List<OneChapter> getCourseListByVid(String vid) {
        QueryWrapper<EduVideo> qw = new QueryWrapper<>();
        qw.eq("video_source_id",vid);
        EduVideo eduVideo = baseMapper.selectOne(qw);
        String courseId = eduVideo.getCourseId();
        List<OneChapter> chapterVideoList = eduChapterService.getChapterAndVideoBycid(courseId);
        if(chapterVideoList != null) return chapterVideoList;
        return null;
    }
}
