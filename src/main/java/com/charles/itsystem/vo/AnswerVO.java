package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerVO {

    private Integer answerID;
    private String paperName;
    private Integer finalPoint;
    private Date createTime;
}
