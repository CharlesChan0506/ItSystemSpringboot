package com.charles.itsystem.service;

import com.charles.itsystem.entity.User;
import com.charles.itsystem.vo.UserVO;

public interface CommonService {
    boolean isExist(String userName);  //判断账号是否存在
    int register(User user);  //用户注册账号
    UserVO login(User user);  //用户登录
    int updatePassword(Integer userID, String password);  //修改密码
}
