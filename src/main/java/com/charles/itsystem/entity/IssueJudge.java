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
@TableName("tb_issuejudge")
public class IssueJudge {

    @TableId(type = IdType.AUTO)
    private Integer issueJudgeID;
    private Integer issueTypeID;
    private Integer subjectID;
    private String judgeContent;
    private String answer;
}
