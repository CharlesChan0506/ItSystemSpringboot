package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentVO {

    private Integer documentID;
    private String staffName;
    private String depName;
    private String docuName;
    private String docuType;
    private String fileSize;
    private String createTime;
}
