package com.sjq.acls.service.impl;

import com.sjq.acls.entity.User;
import com.sjq.acls.service.IAclPermissionService;
import com.sjq.acls.service.IAclUserService;
import com.sjq.acls.service.PermissionService;
import com.sjq.acls.service.UserService;
import com.sjq.security.entity.SecurityUser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 自定义userDetailsService - 认证用户详情
 * </p>
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private IAclUserService iacuserService;

    @Autowired
    private IAclPermissionService iAclPermissionService;

    /***
     * 根据账号获取用户信息
     * @param username:
     * @return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息

        User user = iacuserService.selectByUsername(username);
//        User user = userService.selectByUsername(username);

        // 判断用户是否存在
        if (null == user){
            //throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        com.sjq.security.entity.User curUser = new com.sjq.security.entity.User();
        BeanUtils.copyProperties(user,curUser);

        String id = user.getId();
        List<String> authorities = permissionService.selectPermissionValueByUserId(id);
//        List<String> authorities = permissionService.seleabccccc(id);
        //List<String> authorities = iAclPermissionService.selectPermissionValueByUserId(user.getId());

        SecurityUser securityUser = new SecurityUser(curUser);
        securityUser.setPermissionValueList(authorities);
        return securityUser;
    }

}
