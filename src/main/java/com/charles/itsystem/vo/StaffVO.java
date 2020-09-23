package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffVO {

    private Integer staffID;
    private String userName;
    private String postName;
    private String depName;
    private String staffName;
    private String sex;
    private String age;
    private String phoneNum;
    private String email;
}
