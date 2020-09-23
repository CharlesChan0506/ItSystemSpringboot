package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.*;
import com.charles.itsystem.vo.*;

import java.util.List;

public interface PaperService {
    /*制作问卷*/
    IPage<Subject> selectSubjectList(Integer current, Integer size);  //查询所有科目并分页
    IPage<IssueVO> selectAllIssueBySubjectID(Integer current, Integer size, Integer subjectID);  //根据科目ID查询所有题目
    int addSubject(Subject subject);  //添加科目
    int addSingleIssue(IssueSingle issueSingle);  //添加单选题
    int addManyIssue(IssueMany issueMany);  //添加多选题
    int addJudgeIssue(IssueJudge issueJudge);  //添加判断题
    IssuePage selectSingleIssueList(Integer current, Integer size); //查所有单选题并分页
    IssuePage selectManyIssueList(Integer current, Integer size);  //查所有多选题并分页
    IssuePage selectJudgeIssueList(Integer current, Integer size);  //查所有判断题并分页
    IssueVO selectSingleIssueById(Integer issueID);  //根据ID查单选题目信息
    IssueVO selectManyIssueById(Integer issueID);  //根据ID查多选题目信息
    IssueVO selectJudgeIssueById(Integer issueID);  //根据ID查判断题目信息
    int addPaper(PaperVO paperVO);  //添加问卷
    int deletePaperById(Integer paperID);  //删除问卷
    /*答卷*/
    PaperPage selectPaperList(Integer current, Integer size);  //查询所有问卷并分页
    List<PaperDetailVO> selectPaperById(Integer paperID);  //根据问卷ID查询问卷
    int addAnswer(Answer answer);  //添加员工答卷
    int addFeedback(List<Feedback> feedbacks, Integer userID, Integer paperID);  //添加员工反馈
    /*统计答卷情况*/
    IPage<AnswerVO> selectAnswerByUserID(Integer userID, Integer current, Integer size);  //查询用户答卷得分情况
    List<CountIssueShowVO> countAllIssue(Integer paperID);  //统计一张问卷所有题目员工答题分布
    List<CountPaperVO> countFinalPoint(Integer paperID);  //统计所有员工一张问卷得分情况
}
