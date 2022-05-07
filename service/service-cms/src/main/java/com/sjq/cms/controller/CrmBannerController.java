package com.sjq.cms.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sjq.cms.entity.CrmBanner;
import com.sjq.cms.service.ICrmBannerService;
import com.sjq.commonutils.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-11
 */
@RestController
@RequestMapping("/educms/banner")
//@CrossOrigin
public class CrmBannerController {

    @Autowired
    @Qualifier("crmService")
    private ICrmBannerService crmBannerService;

    @GetMapping("pageBanner/{page}/{limit}")
    public Result pageBanner(@PathVariable Long page,@PathVariable Long limit){
        QueryWrapper<CrmBanner> qw = new QueryWrapper<>();
        IPage<CrmBanner> p = new Page(page,limit);
        crmBannerService.page(p, qw);
        int count = crmBannerService.count(qw);
        p.setTotal(count);
        return Result.ok(p);
    }

    //2 添加banner
    @PostMapping("addBanner")
    public Result addBanner(@RequestBody CrmBanner crmBanner) {
        crmBanner.setGmtCreate(LocalDateTime.now());
        crmBanner.setGmtModified(LocalDateTime.now());
        crmBannerService.save(crmBanner);
        return Result.ok();
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public Result get(@PathVariable String id) {
        CrmBanner banner = crmBannerService.getById(id);
        return Result.ok(banner);
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public Result updateById(@RequestBody CrmBanner banner) {
        banner.setGmtModified(LocalDateTime.now());
        crmBannerService.updateById(banner);
        return Result.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable String id) {
        crmBannerService.removeById(id);
        return Result.ok();
    }

    //查询所有banner 前2个
    @GetMapping("getAllBanner")
    public Result getAllBanner() {
        List<CrmBanner> list = crmBannerService.selectAllBanner();
        if(list == null) return Result.fail();
        return Result.ok(list);
    }

}
