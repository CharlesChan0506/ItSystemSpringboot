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
@TableName("tb_issuesingle")
public class IssueSingle {

    @TableId(type = IdType.AUTO)
    private Integer issueSingleID;
    private Integer issueTypeID;
    private Integer subjectID;
    private String singleContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
}
