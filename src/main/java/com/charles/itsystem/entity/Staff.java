package com.charles.itsystem.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_staff")
public class Staff {

    @TableId(type = IdType.AUTO)
    private Integer staffID;
    private Integer userID;
    private Integer postID;
    private Integer depID;
    private String staffName;
    private String sex;
    private String age;
    private String phoneNum;
    private String email;
}
