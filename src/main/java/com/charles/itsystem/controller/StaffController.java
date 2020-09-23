package com.charles.itsystem.controller;

import com.charles.itsystem.entity.Staff;
import com.charles.itsystem.service.StaffService;
import com.charles.itsystem.vo.StaffPage;
import com.charles.itsystem.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/staff")
@RestController
public class StaffController {

    @Autowired
    private StaffService staffService;

    //查询所有员工并分页
    @GetMapping("/{current}/{size}")
    public StaffPage selectStaffList(@PathVariable Integer current, @PathVariable Integer size){
        return staffService.selectStaffList(current, size);
    }
    //根据ID删除员工
    @DeleteMapping("/{staffID}")
    public int deleteStaffById(@PathVariable Integer staffID){
        return staffService.deleteStaffById(staffID);
    }
    //根据ID查看员工个人信息
    @GetMapping("/detail/{staffID}")
    public StaffVO selectStaffVOById(@PathVariable Integer staffID){
        return staffService.selectStaffVOById(staffID);
    }
    //根据用户ID修改员工信息
    @PutMapping("/detail/{staffID}")
    public int updateStaffById(@PathVariable Integer staffID, Staff staff){
        return staffService.updateStaffById(staffID, staff);
    }
    //根据ID查询员工信息
    @GetMapping("/{staffID}")
    public Staff selectStaffById(@PathVariable Integer staffID){
        return staffService.selectStaffById(staffID);
    }
}
