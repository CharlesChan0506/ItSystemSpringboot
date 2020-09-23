package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {

    private Integer userID;
    private Integer staffID;
    private Integer depID;
    private String userName;
    private Integer role;
    private String loginTime;
    private Boolean isLogin;

}
