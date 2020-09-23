package com.charles.itsystem.service;

import com.charles.itsystem.entity.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StaffServiceTest {

    @Autowired
    private StaffService staffService;

    /**
     * 根据用户ID修改员工信息功能单元测试
     */
    @Test
    void testUpdateById(){
        Staff staff = new Staff();
        staff.setPostID(1);
        staff.setDepID(1);
        staff.setStaffName("李俊丽");
        staff.setSex("男");
        staff.setAge("19");
        staff.setPhoneNum("1978955132");
        staff.setEmail("1978955132@163.com");
    }

    /**
     * 根据ID查看员工信息功能单元测试
     */
    @Test
    void testSelectStaffById(){
        System.out.println("结果："+staffService.selectStaffById(1));
    }

    /**
     * 查询所有员工并分页功能单元测试
     */
    @Test
    void testSelectStaffList(){
        System.out.println("结果："+staffService.selectStaffList(2,2));
    }
}
