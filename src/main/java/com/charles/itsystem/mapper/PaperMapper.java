package com.charles.itsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charles.itsystem.entity.Feedback;
import com.charles.itsystem.entity.Paper;
import com.charles.itsystem.vo.CountIssueVO;
import com.charles.itsystem.vo.CountPaperVO;
import com.charles.itsystem.vo.PaperDetailVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaperMapper extends BaseMapper<Paper> {

    @Insert("insert into tb_paper (paperName,createTime) values (#{paperName},#{createTime})")
    @Options(useGeneratedKeys = true,keyProperty = "paperID",keyColumn = "paperID")
    int insertRerturnId(Paper paper);  //插入问卷并返回主键

    @Select("select count(*) counts\n" +
            "from tb_answer\n" +
            "where paperID = #{paperID}")
    int selectPaperCount(Integer paperID);  //查询该问卷答卷人数

    @Select("select *\n" +
            "from(select t1.paperID, t1.paperName, t1.issueNum, t1.issueTypeID, s.singleContent issueContent, s.optionA, s.optionB, s.optionC, s.optionD, t1.point, t1.answer\n" +
            "from (select pi.*, p.paperName\n" +
            "from tb_paper_issue pi\n" +
            "left join tb_paper p\n" +
            "on pi.paperID = p.paperID\n" +
            "where pi.paperID = #{paperID}\n" +
            "having p.paperName is not null) t1\n" +
            "left join tb_issuesingle s\n" +
            "on t1.issueTypeID = s.issueTypeID\n" +
            "where t1.issueID = s.issueSingleID\n" +
            "union\n" +
            "select t1.paperID, t1.paperName, t1.issueNum, t1.issueTypeID, m.manyContent, m.optionA, m.optionB, m.optionC, m.optionD, t1.point, t1.answer\n" +
            "from (select pi.*, p.paperName\n" +
            "from tb_paper_issue pi\n" +
            "left join tb_paper p\n" +
            "on pi.paperID = p.paperID\n" +
            "where pi.paperID = #{paperID}\n" +
            "having p.paperName is not null) t1\n" +
            "left join tb_issuemany m\n" +
            "on t1.issueTypeID = m.issueTypeID\n" +
            "where t1.issueID = m.issueManyID\n" +
            "union\n" +
            "select t1.paperID, t1.paperName, t1.issueNum, t1.issueTypeID, j.judgeContent, null, null, null, null, t1.point, t1.answer\n" +
            "from (select pi.*, p.paperName\n" +
            "from tb_paper_issue pi\n" +
            "left join tb_paper p\n" +
            "on pi.paperID = p.paperID\n" +
            "where pi.paperID = #{paperID}\n" +
            "having p.paperName is not null) t1\n" +
            "left join tb_issuejudge j\n" +
            "on t1.issueTypeID = j.issueTypeID\n" +
            "where t1.issueID = j.issueJudgeID) as paper\n" +
            "order by issueNum")
    List<PaperDetailVO> selectPaperById(Integer paperID);  //根据问卷ID查询问卷

    @Select("select issueNum, userOption, count(*) counts\n" +
            "from tb_feedback\n" +
            "where paperID = #{paperID} and userOption = 'A'\n" +
            "group by issueNum, userOption\n" +
            "union\n" +
            "select issueNum, userOption, count(*) counts\n" +
            "from tb_feedback\n" +
            "where paperID = #{paperID} and userOption = 'B'\n" +
            "group by issueNum, userOption\n" +
            "union\n" +
            "select issueNum, userOption, count(*) counts\n" +
            "from tb_feedback\n" +
            "where paperID = #{paperID} and userOption = 'C'\n" +
            "group by issueNum, userOption\n" +
            "union\n" +
            "select issueNum, userOption, count(*) counts\n" +
            "from tb_feedback\n" +
            "where paperID = #{paperID} and userOption = 'D'\n" +
            "group by issueNum, userOption\n" +
            "union\n" +
            "select issueNum, userOption, count(*) counts\n" +
            "from tb_feedback\n" +
            "where paperID = #{paperID} and userOption = 'Y'\n" +
            "group by issueNum, userOption\n" +
            "union\n" +
            "select issueNum, userOption, count(*) counts\n" +
            "from tb_feedback\n" +
            "where paperID = #{paperID} and userOption = 'N'\n" +
            "group by issueNum, userOption")
    List<CountIssueVO> countAllIssue(Integer paperID);  //统计一张问卷所有题目员工答题分布

    @Select("select '0-60' section, count(*) counts\n" +
            "from tb_answer\n" +
            "where paperID = #{paperID} and finalPoint between 0 and 60\n" +
            "union\n" +
            "select '60-80', count(*)\n" +
            "from tb_answer\n" +
            "where paperID = #{paperID} and finalPoint between 60 and 70\n" +
            "union\n" +
            "select '60-80', count(*)\n" +
            "from tb_answer\n" +
            "where paperID = #{paperID} and finalPoint between 70 and 80\n" +
            "union\n" +
            "select '80-90', count(*)\n" +
            "from tb_answer\n" +
            "where paperID = #{paperID} and finalPoint between 80 and 90\n" +
            "union\n" +
            "select '>90', count(*)\n" +
            "from tb_answer\n" +
            "where paperID = #{paperID} and finalPoint > 90")
    List<CountPaperVO> countFinalPoint(Integer paperID);  //统计所有员工一张问卷得分情况

}
