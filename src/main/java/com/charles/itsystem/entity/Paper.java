package com.charles.itsystem.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_paper")
public class Paper {

    @TableId(type = IdType.AUTO)
    private Integer paperID;
    private String paperName;
    private Date createTime;
}
