package com.sjq.ucenter.service;

import com.sjq.commonutils.vo.RegisterVo;
import com.sjq.ucenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author kemp
 * @since 2022-04-13
 */
public interface IUcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember user);

    boolean register(RegisterVo registerVo);

    Integer countRegisterDay(String day);
}
