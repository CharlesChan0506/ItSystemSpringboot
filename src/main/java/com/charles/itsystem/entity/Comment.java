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
@TableName("tb_comment")
public class Comment {

    @TableId(type = IdType.AUTO)
    private Integer commentID;
    private Integer articleID;
    private Integer staffID;
    private Integer depID;
    private String comment;
}
