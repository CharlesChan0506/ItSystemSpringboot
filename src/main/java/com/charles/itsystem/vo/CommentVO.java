package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentVO {

    private Integer commentID;
    private String staffName;
    private String depName;
    private String comment;
}
