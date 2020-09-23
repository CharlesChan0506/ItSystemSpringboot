package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperDetailVO {

    private Integer paperID;
    private String paperName;
    private Integer issueNum;
    private Integer issueTypeID;
    private String issueContent;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private Integer point;
    private String answer;
}
