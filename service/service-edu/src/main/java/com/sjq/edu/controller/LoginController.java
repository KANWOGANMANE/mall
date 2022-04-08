package com.sjq.edu.controller;

import com.sjq.commonutils.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;


/**
 * @Author Kemp
 * @create 2022/2/24 18:33
 */
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class LoginController {

    //
    @PostMapping("login")
    public Result login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HashMap<String,String> result = new HashMap();
        result.put("token","admin");
        return Result.ok(result);
    }

    @GetMapping("info")
    public Result info(){
        HashMap<String,String> result = new HashMap();
        result.put("roles","admin");
        result.put("name","admin");
        result.put("avatar","http://192.168.72.138:8888/group1/M00/00/00/wKhIimJGqIGAW3h3AAA0Kg5xJFw852.png");
        return Result.ok(result);
    }
}
