package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.charles.itsystem.entity.Staff;
import com.charles.itsystem.entity.User;
import com.charles.itsystem.mapper.StaffMapper;
import com.charles.itsystem.mapper.UserMapper;
import com.charles.itsystem.util.MyUtil;
import com.charles.itsystem.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("CommonService")
@Transactional
public class CommonServiceImpl implements CommonService {

    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private UserMapper userMapper;

    /**
     * 判断账号是否存在
     * @param userName
     * @return
     */
    public boolean isExist(String userName){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("userName",userName);

        User user = userMapper.selectOne(wrapper);

        return user != null;
    }

    /**
     * 用户注册账号
     * @param user
     */
    public int register(User user){
        //先判断账号是否存在，不存在则将信息写入数据库
        if (!isExist(user.getUserName())){
            //先更新user表
            user.setRole(1);
            userMapper.insert(user);
            //再更新staff表
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("userName",user.getUserName());

            User newUser = userMapper.selectOne(wrapper);
            Staff staff = new Staff();
            staff.setUserID(newUser.getUserID());

            return staffMapper.insert(staff);
        }
        return 0;
    }

    /**
     * 用户登录操作
     * @param user
     * @return
     */
    public UserVO login(User user) {
        UserVO userVO = new UserVO();
        new User();
        //判断账号是否存在，存在就校验
        if (isExist(user.getUserName())){
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("userName",user.getUserName());

            User user1 = userMapper.selectOne(wrapper);
            //校验
            if ((user.getUserName().equals(user1.getUserName())) && (user.getPassword().equals(user1.getPassword()))){
                QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
                staffQueryWrapper.eq("userID",user1.getUserID());
                new Staff();
                Staff staff = staffMapper.selectOne(staffQueryWrapper);

//                session.setAttribute("userID",staff.getUserID());
//                session.setAttribute("staffID",staff.getStaffID());
//                session.setAttribute("depID",staff.getDepID());

                userVO.setUserID(user1.getUserID());
                userVO.setStaffID(staff.getStaffID());
                userVO.setDepID(staff.getDepID());
                userVO.setUserName(user.getUserName());
                userVO.setRole(user1.getRole());
                userVO.setLoginTime(MyUtil.transfoDate(user1.getLoginTime()));
                userVO.setIsLogin(true);
                //更新登录时间
                user1.setLoginTime(new Date());
                userMapper.update(user1,wrapper);

                return userVO;
            }
        }
        //登录失败
        userVO.setIsLogin(false);

        return userVO;
    }

    /**
     * 修改密码
     * @param userID
     * @param password
     * @return
     */
    @Override
    public int updatePassword(Integer userID, String password) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("userID",userID)
                .set("password",password);

        return userMapper.update(null,wrapper);
    }
}
