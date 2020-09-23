package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.Department;
import com.charles.itsystem.entity.Post;
import com.charles.itsystem.entity.Staff;
import com.charles.itsystem.entity.User;
import com.charles.itsystem.mapper.DepartmentMapper;
import com.charles.itsystem.mapper.PostMapper;
import com.charles.itsystem.mapper.StaffMapper;
import com.charles.itsystem.mapper.UserMapper;
import com.charles.itsystem.vo.StaffPage;
import com.charles.itsystem.vo.StaffVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("StaffService")
@Transactional
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserMapper userMapper;


    /**
     * 查询所有员工并分页
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public StaffPage selectStaffList(Integer current, Integer size) {
        StaffPage staffPage = new StaffPage();
        List<StaffVO> staffVOList = new ArrayList<>();;

        Page<Staff> page = new Page<>(current,size);
        QueryWrapper<Staff> wrapper = new QueryWrapper<>();
        IPage<Staff> iPage = staffMapper.selectPage(page,wrapper);
        List<Staff> staffs = iPage.getRecords();
        staffPage.setTotal(iPage.getTotal());  //设置总记录数

        for (Staff staff : staffs) {
            new Staff();
            new User();
            User user = userMapper.selectById(staff.getUserID());

            new Post();
            Post post = postMapper.selectById(staff.getPostID());

            new Department();
            Department department = departmentMapper.selectById(staff.getDepID());

            StaffVO staffVO = new StaffVO();
            staffVO.setStaffID(staff.getStaffID());
            staffVO.setUserName(user.getUserName());
            // 判断是否为新注册用户
            if (staff.getPostID() == null || staff.getDepID() == null ) {
                staffVOList.add(staffVO);
                break;
            }
            staffVO.setPostName(post.getPostName());
            staffVO.setDepName(department.getDepName());
            staffVO.setStaffName(staff.getStaffName());
            staffVO.setSex(staff.getSex());
            staffVO.setAge(staff.getAge());
            staffVO.setPhoneNum(staff.getPhoneNum());
            staffVO.setEmail(staff.getEmail());

            staffVOList.add(staffVO);
        }
        staffPage.setRecords(staffVOList);

        return staffPage;
    }

    /**
     * 根据ID删除员工
     * @param staffID
     * @return
     */
    @Override
    public int deleteStaffById(Integer staffID) {
        return staffMapper.deleteById(staffID);
    }

    /**
     * 根据ID查看员工个人信息
     * @param staffID
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public StaffVO selectStaffVOById(Integer staffID) {
        new Staff();
        Staff staff = staffMapper.selectById(staffID);

        new User();
        User user = userMapper.selectById(staff.getUserID());

        StaffVO staffVO = new StaffVO();
        staffVO.setStaffID(staff.getStaffID());
        staffVO.setUserName(user.getUserName());

        // 判断是否为新注册用户
        if (staff.getPostID() == null || staff.getDepID() == null ) {
            return staffVO;
        }
        new Post();
        Post post = postMapper.selectById(staff.getPostID());

        new Department();
        Department department = departmentMapper.selectById(staff.getDepID());

        staffVO.setPostName(post.getPostName());
        staffVO.setDepName(department.getDepName());
        staffVO.setStaffName(staff.getStaffName());
        staffVO.setSex(staff.getSex());
        staffVO.setAge(staff.getAge());
        staffVO.setPhoneNum(staff.getPhoneNum());
        staffVO.setEmail(staff.getEmail());

        return staffVO;
    }

    /**
     * 根据用户ID修改员工信息
     * @param staffID
     * @param staff
     * @return
     */
    @Override
    public int updateStaffById(Integer staffID, Staff staff) {
        UpdateWrapper<Staff> wrapper = new UpdateWrapper<>();
        wrapper.eq("staffID",staffID)
                .set("postID",staff.getPostID())
                .set("depID",staff.getDepID())
                .set("staffName",staff.getStaffName())
                .set("sex",staff.getSex())
                .set("age",staff.getAge())
                .set("phoneNum",staff.getPhoneNum())
                .set("email",staff.getEmail());

        return staffMapper.update(null,wrapper);
    }

    //根据ID查询员工信息
    @Override
    public Staff selectStaffById(Integer staffID) {
        return staffMapper.selectById(staffID);
    }
}
