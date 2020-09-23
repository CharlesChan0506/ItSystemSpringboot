package com.charles.itsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.Department;
import com.charles.itsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/department")
@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    //查询所有部门并分页
    @GetMapping("/{current}/{size}")
    public IPage<Department> selectDepartmentList(@PathVariable("current") Integer current, @PathVariable("size") Integer size){
        return departmentService.selectDepartmentList(current,size);
    }
    //添加部门
    @PostMapping("/add")
    public int addDepartment(Department department){
        return departmentService.addDepartment(department);
    }
    //根据部门ID删除部门
    @DeleteMapping("/{depID}")
    public int deleteDepartmentById(@PathVariable("depID") Integer depID){
        return departmentService.deleteDepartmentById(depID);
    }
    //根据部门ID更新部门
    @PutMapping("/{depID}")
    public int updateDepartmentById(@PathVariable("depID") Integer depID, Department department){
        return departmentService.updateDepartmentById(depID,department);
    }
    //根据部门ID查询部门
    @GetMapping("/{depID}")
    public Department selectDepartmentById(@PathVariable("depID") Integer depID){
        return departmentService.selectDepartmentById(depID);
    }
    //查询所有部门
    @GetMapping("/all")
    public List<Department> selectAllDepartment(){
        return departmentService.selectAllDepartment();
    }
}
