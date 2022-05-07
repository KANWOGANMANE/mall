package com.sjq.acls.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjq.acls.entity.User;
import com.sjq.acls.mapper.UserMapper;
import com.sjq.acls.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public User selectByUsername(String username) {
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.eq("username", username);
        return baseMapper.selectOne(qw);
    }
}
