package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.Department;
import com.charles.itsystem.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("DepartmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询所有部门并分页
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IPage<Department> selectDepartmentList(Integer current, Integer size) {
        Page<Department> page = new Page<>(current, size);
        QueryWrapper<Department> wrapper = new QueryWrapper<>();

        return departmentMapper.selectPage(page,wrapper);
    }

    /**
     * 添加部门
     * @param department
     * @return
     */
    @Override
    public int addDepartment(Department department){
        return departmentMapper.insert(department);
    }

    /**
     * 根据部门ID删除部门
     * @param depID
     * @return
     */
    @Override
    public int deleteDepartmentById(Integer depID){
        return departmentMapper.deleteById(depID);
    }

    /**
     * 根据部门ID更新部门
     * @param department
     * @return
     */
    @Override
    public int updateDepartmentById(Integer depID, Department department){
        UpdateWrapper<Department> wrapper = new UpdateWrapper<>();
        wrapper.set("depName",department.getDepName())
                .set("depDescribe",department.getDepDescribe())
                .eq("depID",depID);

        return departmentMapper.update(null,wrapper);
    }

    /**
     * 根据部门ID查询部门
     * @param depID
     * @return
     */
    @Override
    public Department selectDepartmentById(Integer depID){
        return departmentMapper.selectById(depID);
    }

    /**
     * 查询所有部门
     * @return
     */
    @Override
    public List<Department> selectAllDepartment() {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();

        return departmentMapper.selectList(wrapper);
    }
}
