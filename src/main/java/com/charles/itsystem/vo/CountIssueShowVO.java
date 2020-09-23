package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountIssueShowVO {

    private Integer issueNum;
    private Integer optionA;
    private Integer optionB;
    private Integer optionC;
    private Integer optionD;
    private Integer optionY;
    private Integer optionN;
    private Double precision;
}
