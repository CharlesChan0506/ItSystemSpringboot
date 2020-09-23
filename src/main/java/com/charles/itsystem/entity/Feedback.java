package com.charles.itsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_feedback")
public class Feedback {

    @TableId(type = IdType.AUTO)
    private Integer feedbackID;
    private Integer userID;
    private Integer paperID;
    private Integer issueNum;
    private String userOption;
}
