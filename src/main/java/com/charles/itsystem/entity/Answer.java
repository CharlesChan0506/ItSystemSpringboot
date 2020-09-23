package com.charles.itsystem.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_answer")
public class Answer {

    @TableId(type = IdType.AUTO)
    private Integer answerID;
    private Integer paperID;
    private Integer userID;
    private Integer finalPoint;
    private Date createTime;

}
