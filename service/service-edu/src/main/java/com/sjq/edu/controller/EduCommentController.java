package com.sjq.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.utils.JwtUtils;
import com.sjq.commonutils.vo.UcenterMemberVo;
import com.sjq.edu.client.ucenterClient;
import com.sjq.edu.entity.EduComment;
import com.sjq.edu.service.IEduCommentService;
import com.sjq.edu.service.IEduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-15
 */
@RestController
@RequestMapping("/eduservice/comment")
//@CrossOrigin
public class EduCommentController {

    @Autowired
    private IEduCommentService eduCommentService;

    @Autowired
    private IEduCourseService eduCourseService;

    @Autowired
    private ucenterClient ucenterClient;

    //根据课程id查询评论列表
    @GetMapping("getCommentListByCourseId/{page}/{limit}")
    public Result getCommentListByCourseId(@PathVariable Long page,
                                           @PathVariable Long limit,
                                           String courseId){
        Map<String, Object> map = eduCommentService.getCourseList(page,limit,courseId);
        if(map == null) return Result.fail();
        return Result.ok(map);
    }

    //添加评论
    @PostMapping("savecomment")
    public Result savecomment(@RequestBody EduComment eduComment, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if(StringUtils.isBlank(memberId)){
            return Result.fail("请先登陆");
        }
        eduComment.setMemberId(memberId);
          //远程调用根据token查询用户信息
//        UcenterMemberVo ucenterMemberVo = ucenterClient.getInfoUcccc(memberId);
//        if(ucenterMemberVo!=null){
//            eduComment.setNickname(ucenterMemberVo.getNickname());
//            eduComment.setAvatar(ucenterMemberVo.getAvatar());
//        }
        eduComment.setGmtCreate(LocalDateTime.now());
        eduComment.setGmtModified(LocalDateTime.now());
        boolean b = eduCommentService.save(eduComment);
        if(b){
            String courseId = eduComment.getCourseId();
            eduCourseService.commentcountadd(courseId);
            return Result.ok();
        }
        return Result.fail();
    }


}
