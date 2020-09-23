package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.*;
import com.charles.itsystem.mapper.*;
import com.charles.itsystem.util.MyUtil;
import com.charles.itsystem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("PaperService")
@Transactional
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperMapper paperMapper;
    @Autowired
    private SubjectMapper subjectMapper;
    @Autowired
    private IssueSingleMapper issueSingleMapper;
    @Autowired
    private IssueManyMapper issueManyMapper;
    @Autowired
    private IssueJudgeMapper issueJudgeMapper;
    @Autowired
    private PaperIssueMapper paperIssueMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private FeedbackMapper feedbackMapper;

    /**
     * 查询所有科目并分页
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IPage<Subject> selectSubjectList(Integer current, Integer size) {
        Page<Subject> page = new Page<>(current, size);
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();

        return subjectMapper.selectPage(page,wrapper);
    }

    /**
     * 根据科目ID查询所有题目并分页
     * @param subjectID
     * @return
     */
    @Override
    public IPage<IssueVO> selectAllIssueBySubjectID(Integer current, Integer size, Integer subjectID) {
        IPage<IssueVO> iPage = new Page<>(current,size);
        List<IssueVO> issueVOs = subjectMapper.selectAllIssueBySubjectID(iPage,subjectID);
        iPage.setRecords(issueVOs);

        return iPage;
    }

    /**
     * 添加科目
     * @param subject
     * @return
     */
    @Override
    public int addSubject(Subject subject) {
        return subjectMapper.insert(subject);
    }

    /**
     * 添加单选题
     * @param issueSingle
     * @return
     */
    @Override
    public int addSingleIssue(IssueSingle issueSingle) {
        return issueSingleMapper.insert(issueSingle);
    }

    /**
     * 添加多选题
     * @param issueMany
     * @return
     */
    @Override
    public int addManyIssue(IssueMany issueMany) {
        return issueManyMapper.insert(issueMany);
    }

    /**
     * 添加判断题
     * @param issueJudge
     * @return
     */
    @Override
    public int addJudgeIssue(IssueJudge issueJudge) {
        return issueJudgeMapper.insert(issueJudge);
    }

    /**
     * 查所有单选题并分页
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IssuePage selectSingleIssueList(Integer current, Integer size) {
        IssuePage issuePage = new IssuePage();
        List<IssueVO> issueVOList = new ArrayList<>();

        Page<IssueSingle> page = new Page<>(current,size);
        QueryWrapper<IssueSingle> wrapper = new QueryWrapper<>();
        IPage<IssueSingle> iPage = issueSingleMapper.selectPage(page,wrapper);
        List<IssueSingle> issueSingles = iPage.getRecords();
        issuePage.setTotal(iPage.getTotal());  //设置总记录数

        for (IssueSingle issueSingle : issueSingles) {
            new IssueSingle();
            IssueVO issueVO = new IssueVO();

            issueVO.setIssueID(issueSingle.getIssueSingleID());
            issueVO.setIssueTypeID(issueSingle.getIssueTypeID());
            issueVO.setSubjectID(issueSingle.getSubjectID());
            issueVO.setIssueContent(issueSingle.getSingleContent());
            issueVO.setOptionA(issueSingle.getOptionA());
            issueVO.setOptionB(issueSingle.getOptionB());
            issueVO.setOptionC(issueSingle.getOptionC());
            issueVO.setOptionD(issueSingle.getOptionD());
            issueVO.setAnswer(issueSingle.getAnswer());

            issueVOList.add(issueVO);
        }
        issuePage.setRecords(issueVOList);

        return issuePage;
    }

    /**
     * 查所有多选题并分页
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IssuePage selectManyIssueList(Integer current, Integer size) {
        IssuePage issuePage = new IssuePage();
        List<IssueVO> issueVOList = new ArrayList<>();

        Page<IssueMany> page = new Page<>(current,size);
        QueryWrapper<IssueMany> wrapper = new QueryWrapper<>();
        IPage<IssueMany> iPage = issueManyMapper.selectPage(page,wrapper);
        List<IssueMany> issueManys = iPage.getRecords();
        issuePage.setTotal(iPage.getTotal());  //设置总记录数

        for (IssueMany issueMany : issueManys) {
            new IssueMany();
            IssueVO issueVO = new IssueVO();

            issueVO.setIssueID(issueMany.getIssueManyID());
            issueVO.setIssueTypeID(issueMany.getIssueTypeID());
            issueVO.setSubjectID(issueMany.getSubjectID());
            issueVO.setIssueContent(issueMany.getManyContent());
            issueVO.setOptionA(issueMany.getOptionA());
            issueVO.setOptionB(issueMany.getOptionB());
            issueVO.setOptionC(issueMany.getOptionC());
            issueVO.setOptionD(issueMany.getOptionD());
            issueVO.setAnswer(issueMany.getAnswer());

            issueVOList.add(issueVO);
        }
        issuePage.setRecords(issueVOList);

        return issuePage;
    }

    /**
     * 查所有判断题并分页
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IssuePage selectJudgeIssueList(Integer current, Integer size) {
        IssuePage issuePage = new IssuePage();
        List<IssueVO> issueVOList = new ArrayList<>();

        Page<IssueJudge> page = new Page<>(current,size);
        QueryWrapper<IssueJudge> wrapper = new QueryWrapper<>();
        IPage<IssueJudge> iPage = issueJudgeMapper.selectPage(page,wrapper);
        List<IssueJudge> issueJudges = iPage.getRecords();
        issuePage.setTotal(iPage.getTotal());  //设置总记录数

        for (IssueJudge issueJudge : issueJudges) {
            new IssueMany();
            IssueVO issueVO = new IssueVO();

            issueVO.setIssueID(issueJudge.getIssueJudgeID());
            issueVO.setIssueTypeID(issueJudge.getIssueTypeID());
            issueVO.setSubjectID(issueJudge.getSubjectID());
            issueVO.setIssueContent(issueJudge.getJudgeContent());
            issueVO.setAnswer(issueJudge.getAnswer());

            issueVOList.add(issueVO);
        }
        issuePage.setRecords(issueVOList);

        return issuePage;
    }

    /**
     * 根据ID查单选题目信息
     * @param issueID
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IssueVO selectSingleIssueById(Integer issueID) {
        new IssueSingle();
        IssueSingle issueSingle = issueSingleMapper.selectById(issueID);

        IssueVO issueVO = new IssueVO();
        issueVO.setIssueID(issueSingle.getIssueSingleID());
        issueVO.setIssueTypeID(issueSingle.getIssueTypeID());
        issueVO.setSubjectID(issueSingle.getSubjectID());
        issueVO.setIssueContent(issueSingle.getSingleContent());
        issueVO.setOptionA(issueSingle.getOptionA());
        issueVO.setOptionB(issueSingle.getOptionB());
        issueVO.setOptionC(issueSingle.getOptionC());
        issueVO.setOptionD(issueSingle.getOptionD());
        issueVO.setAnswer(issueSingle.getAnswer());

        return issueVO;
    }

    /**
     * 根据ID查多选题目信息
     * @param issueID
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IssueVO selectManyIssueById(Integer issueID) {
        new IssueMany();
        IssueMany issueMany = issueManyMapper.selectById(issueID);

        IssueVO issueVO = new IssueVO();
        issueVO.setIssueID(issueMany.getIssueManyID());
        issueVO.setIssueTypeID(issueMany.getIssueTypeID());
        issueVO.setSubjectID(issueMany.getSubjectID());
        issueVO.setIssueContent(issueMany.getManyContent());
        issueVO.setOptionA(issueMany.getOptionA());
        issueVO.setOptionB(issueMany.getOptionB());
        issueVO.setOptionC(issueMany.getOptionC());
        issueVO.setOptionD(issueMany.getOptionD());
        issueVO.setAnswer(issueMany.getAnswer());

        return issueVO;
    }

    /**
     * 根据ID查判断题目信息
     * @param issueID
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IssueVO selectJudgeIssueById(Integer issueID) {
        new IssueJudge();
        IssueJudge issueJudge = issueJudgeMapper.selectById(issueID);

        IssueVO issueVO = new IssueVO();
        issueVO.setIssueID(issueJudge.getIssueJudgeID());
        issueVO.setIssueTypeID(issueJudge.getIssueTypeID());
        issueVO.setSubjectID(issueJudge.getSubjectID());
        issueVO.setIssueContent(issueJudge.getJudgeContent());
        issueVO.setAnswer(issueJudge.getAnswer());

        return issueVO;
    }

    /**
     * 添加问卷
     * @param paperVO
     * @return
     */
    @Override
    public int addPaper(PaperVO paperVO) {
        //先更新问卷表
        paperVO.getPaper().setCreateTime(new Date());
        paperMapper.insertRerturnId(paperVO.getPaper());
        //再更新问卷试题表
        List<PaperIssue> paperIssues = paperVO.getPaperIssues();
        int sum = 0;
        for (PaperIssue paperIssue : paperIssues) {
            paperIssue.setPaperID(paperVO.getPaper().getPaperID());
            paperIssueMapper.insert(paperIssue);
            sum++;
        }
        //返回添加的总题数
        return sum;
    }

    /**
     * 查询所有问卷并分页
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public PaperPage selectPaperList(Integer current, Integer size) {
        PaperPage paperPage = new PaperPage();
        List<PaperListVO> paperListVOList = new ArrayList<>();

        Page<Paper> page = new Page<>(current, size);
        QueryWrapper<Paper> wrapper = new QueryWrapper<>();
        IPage<Paper> iPage = paperMapper.selectPage(page,wrapper);
        List<Paper> papers = iPage.getRecords();
        paperPage.setTotal(iPage.getTotal());  //设置总记录数

        for (Paper paper : papers) {
            new Paper();

            PaperListVO paperListVO = new PaperListVO();
            paperListVO.setPaperID(paper.getPaperID());
            paperListVO.setPaperName(paper.getPaperName());
            paperListVO.setCreateTime(MyUtil.transfoDate(paper.getCreateTime()));

            paperListVOList.add(paperListVO);
        }
        paperPage.setRecords(paperListVOList);

        return paperPage;
    }

    /**
     * 根据问卷ID查询问卷
     * @param paperID
     * @return
     */
    @Override
    public List<PaperDetailVO> selectPaperById(Integer paperID) {
        return paperMapper.selectPaperById(paperID);
    }

    /**
     * 添加员工答卷
     * @param answer
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public int addAnswer(Answer answer) {
        Answer newAnswer = new Answer();
        QueryWrapper<Answer> wrapper = new QueryWrapper<>();
        wrapper.eq("userID",answer.getUserID())
                .eq("paperID",answer.getPaperID());
        Answer contrast = answerMapper.selectOne(wrapper);
        //如果存在则更新值
        if (contrast != null){
            UpdateWrapper<Answer> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userID",answer.getUserID())
                    .eq("paperID",answer.getPaperID())
                    .set("finalPoint",answer.getFinalPoint())
                    .set("createTime",new Date());

            return answerMapper.update(null,updateWrapper);
        }else {
            newAnswer.setPaperID(answer.getPaperID());
            newAnswer.setUserID(answer.getUserID());
            newAnswer.setFinalPoint(answer.getFinalPoint());
            newAnswer.setCreateTime(new Date());

            return answerMapper.insert(newAnswer);
        }
    }

    /**
     * 删除问卷
     * @param paperID
     * @return
     */
    @Override
    public int deletePaperById(Integer paperID) {
        return paperMapper.deleteById(paperID) + paperIssueMapper.deleteById(paperID);
    }

    /**
     * 添加员工反馈
     * @param feedbacks
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public int addFeedback(List<Feedback> feedbacks, Integer userID, Integer paperID) {
        QueryWrapper<Feedback> wrapper = new QueryWrapper<>();
        wrapper.eq("userID",userID)
                .eq("paperID",paperID);
        List<Feedback> contrast = feedbackMapper.selectList(wrapper);
        //如果存在则更新值
        if (!contrast.isEmpty()){
            new ArrayList<>();
            for (Feedback feedback : feedbacks) {
                UpdateWrapper<Feedback> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("userID",feedback.getUserID())
                        .eq("paperID",feedback.getPaperID())
                        .eq("issueNum",feedback.getIssueNum())
                        .set("userOption",feedback.getUserOption());

                feedbackMapper.update(null,updateWrapper);
            }
            return 0;
        }else {
            for (Feedback feedback : feedbacks) {
                Feedback newFeedback = new Feedback();
                newFeedback.setUserID(feedback.getUserID());
                newFeedback.setPaperID(feedback.getPaperID());
                newFeedback.setIssueNum(feedback.getIssueNum());
                newFeedback.setUserOption(feedback.getUserOption());

                feedbackMapper.insert(newFeedback);
            }
            return 0;
        }
    }

    /**
     * 查询用户答卷得分情况
     * @param userID
     * @return
     */
    @Override
    public IPage<AnswerVO> selectAnswerByUserID(Integer userID, Integer current, Integer size) {
        IPage<AnswerVO> iPage = new Page<>(current,size);
        List<AnswerVO> answers = answerMapper.selectAllAnswerByUserId(iPage,userID);
        iPage.setRecords(answers);

        return iPage;
    }

    /**
     * 统计一张问卷所有题目员工答题分布
     * @param paperID
     * @return
     */
    @Override
    public List<CountIssueShowVO> countAllIssue(Integer paperID) {
        List<CountIssueShowVO> countIssueShowVOList = new ArrayList<>();

        QueryWrapper<PaperIssue> wrapper = new QueryWrapper<>();
        wrapper.eq("paperID",paperID);
        List<PaperIssue> paperIssues = paperIssueMapper.selectList(wrapper);
        List<CountIssueVO> countIssueVOList = paperMapper.countAllIssue(paperID);  //获得题目统计集合
        Integer count = paperMapper.selectPaperCount(paperID); //该试卷答题人数

        for (int i = 0; i < paperIssues.size(); i++) {
            CountIssueShowVO countIssueShowVO = new CountIssueShowVO();
            countIssueShowVO.setIssueNum(i+1);  //设置题号

            for (int j = 0; j < countIssueVOList.size(); j++) {

                if (countIssueVOList.get(j).getIssueNum() == i+1 ){

                    switch (countIssueVOList.get(j).getUserOption()) {
                        case "A":
                            countIssueShowVO.setOptionA(countIssueVOList.get(j).getCounts()); //A

                            break;
                        case "B":
                            countIssueShowVO.setOptionB(countIssueVOList.get(j).getCounts()); //B

                            break;
                        case "C":
                            countIssueShowVO.setOptionC(countIssueVOList.get(j).getCounts()); //C

                            break;
                        case "D":
                            countIssueShowVO.setOptionD(countIssueVOList.get(j).getCounts()); //D

                            break;
                        case "Y":
                            countIssueShowVO.setOptionY(countIssueVOList.get(j).getCounts()); //Y

                            break;
                        default:
                            countIssueShowVO.setOptionN(countIssueVOList.get(j).getCounts()); //N

                            break;
                    }
                }
            }
            QueryWrapper<PaperIssue> wrapper1 = new QueryWrapper<>();
            wrapper1.eq("paperID",paperID)
                    .eq("issueNum",i+1);
            PaperIssue paperIssue = paperIssueMapper.selectOne(wrapper1);
            String answer = paperIssue.getAnswer(); //查找正确答案
            DecimalFormat df = new DecimalFormat("0.00"); //设置保留位数

            switch (answer) {
                case "A":
                    if (countIssueShowVO.getOptionA() != null){
                        countIssueShowVO.setPrecision(Double.parseDouble(df.format((double)countIssueShowVO.getOptionA() / count)));
                    }else {
                        countIssueShowVO.setPrecision(0.00);
                    }
                    break;
                case "B":
                    if (countIssueShowVO.getOptionB() != null){
                        countIssueShowVO.setPrecision(Double.parseDouble(df.format((double)countIssueShowVO.getOptionB() / count)));
                    }else {
                        countIssueShowVO.setPrecision(0.00);
                    }
                    break;
                case "C":
                    if (countIssueShowVO.getOptionC() != null){
                        countIssueShowVO.setPrecision(Double.parseDouble(df.format((double)countIssueShowVO.getOptionC() / count)));
                    }else {
                        countIssueShowVO.setPrecision(0.00);
                    }
                    break;
                case "D":
                    if (countIssueShowVO.getOptionD() != null){
                        countIssueShowVO.setPrecision(Double.parseDouble(df.format((double)countIssueShowVO.getOptionD() / count)));
                    }else {
                        countIssueShowVO.setPrecision(0.00);
                    }
                    break;
                case "Y":
                    if (countIssueShowVO.getOptionY() != null){
                        countIssueShowVO.setPrecision(Double.parseDouble(df.format((double)countIssueShowVO.getOptionY() / count)));
                    }else {
                        countIssueShowVO.setPrecision(0.00);
                    }
                    break;
                default:
                    if (countIssueShowVO.getOptionN() != null){
                        countIssueShowVO.setPrecision(Double.parseDouble(df.format((double)countIssueShowVO.getOptionN() / count)));
                    }else {
                        countIssueShowVO.setPrecision(0.00);
                    }
                    break;
            }
            countIssueShowVOList.add(countIssueShowVO);
        }
        return countIssueShowVOList;
    }

    /**
     * 统计所有员工一张问卷得分情况
     * @param paperID
     * @return
     */
    @Override
    public List<CountPaperVO> countFinalPoint(Integer paperID) {
        return paperMapper.countFinalPoint(paperID);
    }
}
