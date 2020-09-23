package com.charles.itsystem.service;

import com.charles.itsystem.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 查询所有部门并分页功能单元测试
     */
    @Test
    void testSelectDepartmentList(){
//        PageInfo pageInfo = departmentService.selectDepartmentList(2,5);
//        System.out.println("结果："+ pageInfo.getList().toString());
    }

    /**
     * 添加部门功能单元测试
     */
    @Test
    void testAddDepartment(){
        Department department = new Department();
        department.setDepName("客服部");
        department.setDepDescribe("负责售后咨询和帮助的部门");

        System.out.println("受影响行数："+departmentService.addDepartment(department));
    }

    /**
     * 根据部门ID查询部门功能单元测试
     */
    @Test
    void testSelectDepartmentById(){
        System.out.println(departmentService.selectDepartmentById(1));
    }

    /**
     * 根据部门ID更新部门功能单元测试
     */
    @Test
    void testUpdateDepartmentById(){
        Department department = new Department();
        department.setDepID(1);
        department.setDepName("研发部");
        department.setDepDescribe("负责产品的代码开发");

        System.out.println("受影响行数："+departmentService.updateDepartmentById(department.getDepID(),department));
    }
}
