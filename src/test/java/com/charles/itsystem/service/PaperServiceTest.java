package com.charles.itsystem.service;

import com.charles.itsystem.controller.PaperController;
import com.charles.itsystem.entity.Feedback;
import com.charles.itsystem.entity.Paper;
import com.charles.itsystem.entity.PaperIssue;
import com.charles.itsystem.mapper.PaperMapper;
import com.charles.itsystem.vo.PaperVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PaperServiceTest {

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private PaperService paperService;
    @Autowired
    private PaperController paperController;

    @Test
    void testInsert(){
        PaperVO paperVO = new PaperVO();
        paperVO.getPaper().setPaperName("软件工程初级测试");
        paperVO.getPaper().setCreateTime(new Date());
        paperMapper.insertRerturnId(paperVO.getPaper());
        System.out.println("result:"+paperVO.getPaper().getPaperID());
    }

    /**
     * 添加问卷功能单元测试
     */
    @Test
    void testAddPaper(){
        PaperVO paperVO = new PaperVO();
        paperVO.getPaper().setPaperName("软件工程初级测试");
        List<PaperIssue> paperIssues = paperVO.getPaperIssues();
        paperIssues.add(new PaperIssue(null,null,1,1,1,"A",25));
        paperIssues.add(new PaperIssue(null,null,1,2,2,"ACD",25));
        paperIssues.add(new PaperIssue(null,null,2,2,3,"ABC",25));
        paperIssues.add(new PaperIssue(null,null,1,3,4,"N",25));

        System.out.println("问卷总题数："+paperService.addPaper(paperVO));
    }

    /**
     * 根据科目ID查询所有题目并分页功能单元测试
     */
    @Test
    void testSelectAllIssueBySubjectID(){
        System.out.println("查询结果："+paperService.selectAllIssueBySubjectID(1,5,1).getTotal());
    }

    /**
     * 根据问卷ID查询问卷功能单元测试
     */
    @Test
    void testSelectPaperById(){
        System.out.println("查询结果：\n"+paperService.selectPaperById(4));
    }

    @Test
    void testAddFeedback(){
        Feedback feedback = new Feedback(null,1,18,1,"A");
        Feedback feedback2 = new Feedback(null,1,18,2,"B");
        Feedback feedback3 = new Feedback(null,1,18,3,"A");
        Feedback feedback4 = new Feedback(null,1,18,4,"A");
        List<Feedback> feedbacks = new ArrayList<>();
        feedbacks.add(feedback);
        feedbacks.add(feedback2);
        feedbacks.add(feedback3);
        feedbacks.add(feedback4);
        System.out.println("结果：" + paperService.addFeedback(feedbacks,1,18));
    }

    @Test
    void testCountAllIssue(){
        System.out.println("结果："+paperController.countAllIssue(25));
    }
    @Test
    void testCountFinalPoint(){
        System.out.println("结果："+paperService.countFinalPoint(25));
    }
    @Test
    void  testSelectPaperList() {
        System.out.println("结果：" + paperService.selectPaperList(1,1).getRecords());
    }
}
