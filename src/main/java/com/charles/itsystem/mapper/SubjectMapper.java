package com.charles.itsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.Subject;
import com.charles.itsystem.vo.IssueVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectMapper extends BaseMapper<Subject> {

    @Select("select issueSingleID issueID, singleContent issueContent, issueTypeID\n" +
            "from tb_issuesingle\n" +
            "where subjectID = #{subjectID}\n" +
            "union\n" +
            "select issueManyID, manyContent, issueTypeID\n" +
            "from tb_issuemany\n" +
            "where subjectID = #{subjectID}\n" +
            "union\n" +
            "select issueJudgeID, judgeContent, issueTypeID\n" +
            "from tb_issuejudge\n" +
            "where subjectID = #{subjectID}")
    List<IssueVO> selectAllIssueBySubjectID(IPage<IssueVO> iPage, Integer subjectID);  //根据科目ID查询所有题目
}
