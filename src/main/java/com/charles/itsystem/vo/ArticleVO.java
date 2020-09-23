package com.charles.itsystem.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleVO {

    private Integer articleID;
    private String staffName;
    private String depName;
    private String title;
    private String articleContent;
}
