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
@TableName("tb_issuemany")
public class IssueMany {

    @TableId(type = IdType.AUTO)
    private Integer issueManyID;
    private Integer issueTypeID;
    private Integer subjectID;
    private String manyContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
}
