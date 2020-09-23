package com.charles.itsystem.service;

import com.charles.itsystem.entity.Staff;
import com.charles.itsystem.vo.StaffPage;
import com.charles.itsystem.vo.StaffVO;

public interface StaffService {
    StaffPage selectStaffList(Integer current, Integer size);  //查询所有员工并分页
    int deleteStaffById(Integer staffID);  //根据ID删除员工
    StaffVO selectStaffVOById(Integer staffID);  //根据ID查看员工个人信息
    int updateStaffById(Integer staffID, Staff staff);  //根据员工ID修改员工信息
    Staff selectStaffById(Integer staffID); //根据ID查询员工信息
}
