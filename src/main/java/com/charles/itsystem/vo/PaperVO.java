package com.charles.itsystem.vo;

import com.charles.itsystem.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaperVO {

    private Paper paper = new Paper();
    private List<PaperIssue> paperIssues = new ArrayList<>();
}
