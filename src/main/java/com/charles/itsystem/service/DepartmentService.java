package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.Department;

import java.util.List;

public interface DepartmentService {
    IPage<Department> selectDepartmentList(Integer current, Integer size);  //查询所有部门并分页
    int addDepartment(Department department);  //添加部门
    int deleteDepartmentById(Integer depID);  //根据部门ID删除部门
    int updateDepartmentById(Integer depID, Department department);  //根据部门ID更新部门
    Department selectDepartmentById(Integer depID);  //根据部门ID查询部门
    List<Department> selectAllDepartment();  //查询所有部门
}
