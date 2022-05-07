package com.sjq.acls.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sjq.acls.entity.AclUser;
import com.sjq.acls.entity.User;
import com.sjq.acls.mapper.AclUserMapper;
import com.sjq.acls.service.IAclUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author kemp
 * @since 2022-04-18
 */
@Primary
@Service
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUser> implements IAclUserService {

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<AclUser> qw = new QueryWrapper<>();
        qw.eq("username",username);
        AclUser aclUser = baseMapper.selectOne(qw);
        User user = new User();
        if(aclUser != null){
            BeanUtils.copyProperties(aclUser,user);
        }
        return user;
    }
}
