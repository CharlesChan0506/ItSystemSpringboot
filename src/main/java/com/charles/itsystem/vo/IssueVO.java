package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueVO {

    private Integer issueID;
    private Integer issueTypeID;
    private Integer subjectID;
    private String issueContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String answer;
}
