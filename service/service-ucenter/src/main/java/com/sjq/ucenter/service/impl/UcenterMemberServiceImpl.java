package com.sjq.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sjq.commonutils.utils.JwtUtils;
import com.sjq.commonutils.utils.MD5;
import com.sjq.commonutils.vo.RegisterVo;
import com.sjq.ucenter.entity.UcenterMember;
import com.sjq.ucenter.mapper.UcenterMemberMapper;
import com.sjq.ucenter.service.IUcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-13
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements IUcenterMemberService {

    private String[] default_avatar = new String[]{
            "http://192.168.72.138:8888/group1/M00/00/00/wKhIimJGqIGAW3h3AAA0Kg5xJFw852.png",
            "http://192.168.72.138:8888/group1/M00/00/02/wKhIimJaO8SAP_aOAABOHO2s3a0196.jpg",
            "http://192.168.72.138:8888/group1/M00/00/02/wKhIimJaPAGACI38AABciCkFKm8400.jpg",
            "http://192.168.72.138:8888/group1/M00/00/02/wKhIimJaPE-AL5E1AAAZQgUfolg210.jpg",
            "http://192.168.72.138:8888/group1/M00/00/02/wKhIimJaPKmAVB90AAAwfnIRC9A052.jpg",
            "http://192.168.72.138:8888/group1/M00/00/02/wKhIimJaPNmAcAfeAABErNlxftQ464.jpg",
            "http://192.168.72.138:8888/group1/M00/00/02/wKhIimJaPPaANmlfAAAlCuWVSl4983.jpg"};

    @Override
    public String login(UcenterMember user) {
        String mobile = user.getMobile();
        String password = user.getPassword();
        if(StringUtils.isBlank(mobile) || StringUtils.isBlank(password)){
            return null;
        }
        QueryWrapper<UcenterMember> qw = new QueryWrapper<>();
        qw.eq("mobile",mobile);
        UcenterMember member = baseMapper.selectOne(qw);
        if(member == null){
            return null;
        }
        if(StringUtils.isNotBlank(password)){
            String encrypt = MD5.encrypt(password);
            if(!encrypt.equals(member.getPassword())){
                return null;
            }
        }
        if(member.getIsDisabled()){
            return null;
        }
        String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());
        return jwtToken;
    }

    @Override
    public boolean register(RegisterVo registerVo) {
        //获取注册的数据
        String mobile = registerVo.getMobile(); //手机号
        String nickname = registerVo.getNickname(); //昵称
        String password = registerVo.getPassword(); //密码
        if(StringUtils.isBlank(mobile)  || StringUtils.isBlank(nickname) || StringUtils.isBlank(password)){
            return false;
        }
        //是否有相投手机号
        QueryWrapper<UcenterMember> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(wrapper);
        if(count > 0) {
            return false;
        }
        //是否有相同昵称
        QueryWrapper<UcenterMember> qw = new QueryWrapper<>();
        qw.eq("nickname",nickname);
        Integer integer = baseMapper.selectCount(qw);
        if(integer > 0) {
            return false;
        }
        UcenterMember member = new UcenterMember();
        member.setMobile(mobile);
        member.setNickname(nickname);
        member.setPassword(MD5.encrypt(password));//密码需要加密的
        member.setIsDisabled(false);//用户不禁用
        member.setGmtCreate(LocalDateTime.now());
        member.setGmtModified(LocalDateTime.now());
        //默认头像
        member.setAvatar(default_avatar[new Random().nextInt(7)]);
        baseMapper.insert(member);
        return true;
    }

    @Override
    public Integer countRegisterDay(String day) {
        return baseMapper.countRegisterDay(day);
    }

}
