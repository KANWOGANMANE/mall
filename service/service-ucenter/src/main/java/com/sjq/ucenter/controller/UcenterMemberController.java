package com.sjq.ucenter.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjq.commonutils.result.Result;
import com.sjq.commonutils.utils.JwtUtils;
import com.sjq.commonutils.vo.RegisterVo;
import com.sjq.commonutils.vo.UcenterMemberVo;
import com.sjq.ucenter.entity.UcenterMember;
import com.sjq.ucenter.service.IUcenterMemberService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author kemp
 * @since 2022-04-13
 */
@RestController
@RequestMapping("/ucenter/member")
//@CrossOrigin
public class UcenterMemberController {

    @Autowired
    private IUcenterMemberService ucenterMemberService;

    //登陆
    @PostMapping("login")
    public Result login(@RequestBody UcenterMember user){
        String token = ucenterMemberService.login(user);
        if(StringUtils.isNotBlank(token)){
            return Result.ok(token);
        }
        return Result.fail();
    }

    //注册
    @PostMapping("register")
    public Result registerUser(@RequestBody RegisterVo registerVo) {
        boolean register = ucenterMemberService.register(registerVo);
        return register?Result.ok() : Result.fail();
    }

    //根据token获取用户信息
    @GetMapping("getMemberInfo")
    public Result getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        UcenterMember member = ucenterMemberService.getById(memberId);
        if(member == null) return Result.fail();
        return Result.ok(member);
    }

    //根据用户id获取用户信息
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberVo getInfoUcccc(@PathVariable String id){
        UcenterMember member = ucenterMemberService.getById(id);
        if(member==null) return null;
        UcenterMemberVo membervo =  new UcenterMemberVo();
        BeanUtils.copyProperties(member,membervo);
        return membervo;
    }

    //查询某天注册人数
    @GetMapping("countRegister/{day}")
    public Integer countRegister(@PathVariable String day){
        Integer count = ucenterMemberService.countRegisterDay(day);
        return count;
    }





}
