package com.sjq.edu.controller;


import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.vo.OneChapter;
import com.sjq.edu.entity.EduChapter;
import com.sjq.edu.service.IEduChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-02
 */
@RestController
@RequestMapping("/eduservice/chapter")
//@CrossOrigin
public class EduChapterController {

    @Autowired
    private IEduChapterService eduChapterService;

    @GetMapping("getChapterAndVideo/{courseId}")
    public Result getChapterAndVideo(@PathVariable String courseId){
        List<OneChapter> res = eduChapterService.getChapterAndVideoBycid(courseId);
        if (res == null) {
            return Result.fail();
        }
        return Result.ok(res);
    }

    @PostMapping("addChapter")
    public Result addChapter(@RequestBody EduChapter chapter){
        chapter.setGmtCreate(LocalDateTime.now());
        chapter.setGmtModified(LocalDateTime.now());
        boolean b = eduChapterService.save(chapter);
        if (!b) {
            Result.fail();
        }
        return Result.ok();
    }

    @GetMapping("getChapterInfo/{chapterid}")
    public Result getChapterInfo(@PathVariable String chapterid){
        EduChapter eduChapter = eduChapterService.getById(chapterid);
        if (eduChapter == null) {
            return Result.fail();
        }
        return Result.ok(eduChapter);
    }

    @PostMapping("updateChapter")
    public Result updateChapter(@RequestBody EduChapter eduChapter){
        eduChapter.setGmtModified(LocalDateTime.now());
        boolean b = eduChapterService.updateById(eduChapter);
        if(b){
            return Result.ok();
        }
        return Result.fail();
    }

    @DeleteMapping("deleteChapter/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId){
        boolean b = eduChapterService.deleteChapter(chapterId);
        if(b){
            return Result.ok();
        }
        return Result.fail("请先删除小节");
    }

}
