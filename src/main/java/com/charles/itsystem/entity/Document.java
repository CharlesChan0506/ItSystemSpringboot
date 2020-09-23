package com.charles.itsystem.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_document")
public class Document {

    @TableId(type = IdType.AUTO)
    private Integer documentID;
    private Integer staffID;
    private Integer depID;
    private String docuName;
    private String docuType;
    private String fileName;
    private String fileSize;
    private String filePath;
    private String relativePath;
    private Date createTime;
}
