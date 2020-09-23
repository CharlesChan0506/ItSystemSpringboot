package com.charles.itsystem.controller;

import com.charles.itsystem.entity.User;
import com.charles.itsystem.service.CommonService;
import com.charles.itsystem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/common")
@RestController
public class CommonController {

    @Autowired
    private CommonService commonService;

    //用户注册账号
    @PostMapping("/register")
    public int register(User user){
        return commonService.register(user);
    }
    //用户登录
    @PostMapping("/login")
    public UserVO login(User user){
        return commonService.login(user);
    }
    //修改密码
    @PostMapping("/password")
    public int updatePassword(Integer userID, String password){
        return commonService.updatePassword(userID,password);
    }
}
