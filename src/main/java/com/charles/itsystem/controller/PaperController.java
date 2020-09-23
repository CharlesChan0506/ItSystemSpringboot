package com.charles.itsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.*;
import com.charles.itsystem.service.PaperService;
import com.charles.itsystem.vo.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/paper")
@RestController
public class PaperController {

    @Autowired
    private PaperService paperService;

    //查询所有科目并分页
    @GetMapping("/subject/{current}/{size}")
    public IPage<Subject> selectSubjectList(@PathVariable Integer current, @PathVariable Integer size){
        return paperService.selectSubjectList(current, size);
    }
    //根据科目ID查询所有题目
    @GetMapping("/subject/detail/{current}/{size}/{subjectID}")
    public IPage<IssueVO> selectAllIssueBySubjectID(@PathVariable Integer current, @PathVariable Integer size, @PathVariable Integer subjectID){
        return paperService.selectAllIssueBySubjectID(current, size, subjectID);
    }
    //添加科目
    @PostMapping("/add/subject")
    public int addSubject(Subject subject){
        return paperService.addSubject(subject);
    }
    //添加单选题
    @PostMapping("/add/single")
    public int addSingleIssue(IssueSingle issueSingle){
        return paperService.addSingleIssue(issueSingle);
    }
    //添加多选题
    @PostMapping("/add/many")
    public int addManyIssue(IssueMany issueMany){
        return paperService.addManyIssue(issueMany);
    }
    //添加判断题
    @PostMapping("/add/judge")
    public int addJudgeIssue(IssueJudge issueJudge){
        return paperService.addJudgeIssue(issueJudge);
    }
    //查所有单选题并分页
    @GetMapping("/single/{current}/{size}")
    public IssuePage selectSingleIssueList(@PathVariable Integer current, @PathVariable Integer size){
        return paperService.selectSingleIssueList(current, size);
    }
    //查所有多选题并分页
    @GetMapping("/many/{current}/{size}")
    public IssuePage selectManyIssueList(@PathVariable Integer current, @PathVariable Integer size){
        return paperService.selectManyIssueList(current, size);
    }
    //查所有判断题并分页
    @GetMapping("/judge/{current}/{size}")
    public IssuePage selectJudgeIssueList(@PathVariable Integer current, @PathVariable Integer size){
        return paperService.selectJudgeIssueList(current, size);
    }
    //根据ID查单选题目信息
    @GetMapping("/single/{issueID}")
    public IssueVO selectSingleIssueById(@PathVariable Integer issueID){
        return paperService.selectSingleIssueById(issueID);
    }
    //根据ID查多选题目信息
    @GetMapping("/many/{issueID}")
    public IssueVO selectManyIssueById(@PathVariable Integer issueID){
        return paperService.selectManyIssueById(issueID);
    }
    //根据ID查判断题目信息
    @GetMapping("/judge/{issueID}")
    public IssueVO selectJudgeIssueById(@PathVariable Integer issueID){
        return paperService.selectJudgeIssueById(issueID);
    }
    //添加问卷
    @PostMapping("/add/paper")
    public int addPaper(@RequestBody PaperVO paperVO){
        return paperService.addPaper(paperVO);
    }
    //删除问卷
    @DeleteMapping("/{paperID}")
    public int deletePaperById(@PathVariable Integer paperID){
        return paperService.deletePaperById(paperID);
    }
    //查询所有问卷并分页
    @GetMapping("/{current}/{size}")
    public PaperPage selectPaperList(@PathVariable Integer current, @PathVariable Integer size){
        return paperService.selectPaperList(current, size);
    }
    //根据问卷ID查询问卷
    @GetMapping("/detail/{paperID}")
    public List<PaperDetailVO> selectPaperById(@PathVariable Integer paperID){
        return paperService.selectPaperById(paperID);
    }
    //添加员工答卷
    @PostMapping("/add/answer")
    public int addAnswer(Answer answer){
        return paperService.addAnswer(answer);
    }
    //添加员工反馈
    @PostMapping("/add/feedback")
    public int addFeedback(@RequestBody Map<String,Object> map){
        List<Feedback> feedbacks1 = (List<Feedback>) map.get("feedbacks");
        ObjectMapper mapper = new ObjectMapper();
        List<Feedback> feedbacks = mapper.convertValue(feedbacks1, new TypeReference<List<Feedback>>() { });
        String userID1 = map.get("userID").toString();
        String paperID1 = map.get("paperID").toString();
        Integer userID = Integer.parseInt(userID1);
        Integer paperID = Integer.parseInt(paperID1);
        return paperService.addFeedback(feedbacks,userID,paperID);
    }
    //查询用户答卷得分情况
    @GetMapping("/answer/{userID}/{current}/{size}")
    public IPage<AnswerVO> selectAnswerByUserID(@PathVariable Integer userID, @PathVariable Integer current, @PathVariable Integer size){
        return paperService.selectAnswerByUserID(userID,current,size);
    }
    //统计一张问卷所有题目员工答题分布
    @GetMapping("/count/issue/{paperID}")
    public List<CountIssueShowVO> countAllIssue(@PathVariable Integer paperID){
        return paperService.countAllIssue(paperID);
    }
    //统计所有员工一张问卷得分情况
    @GetMapping("/count/point/{paperID}")
    public List<CountPaperVO> countFinalPoint(@PathVariable Integer paperID){
        return paperService.countFinalPoint(paperID);
    }
}
