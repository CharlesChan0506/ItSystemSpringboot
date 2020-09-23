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
@TableName("tb_paper_issue")
public class PaperIssue {

    @TableId(type = IdType.AUTO)
    private Integer paperIssueID;
    private Integer paperID;
    private Integer issueID;
    private Integer issueTypeID;
    private Integer issueNum;
    private String answer;
    private Integer point;
}
