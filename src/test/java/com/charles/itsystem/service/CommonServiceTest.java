package com.charles.itsystem.service;

import com.charles.itsystem.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    /**
     * 注册功能单元测试
     */
    @Test
    void testRegiser(){
        User user = new User();
        user.setUserName("Charles");
        user.setPassword("123456");

        System.out.println("受影响行数："+commonService.register(user));
    }

    @Test
    void testLogin(){
        User user = new User();
        user.setUserName("admin");
        user.setPassword("123456");


    }
}
