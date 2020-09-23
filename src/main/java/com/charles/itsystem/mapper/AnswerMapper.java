package com.charles.itsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.Answer;
import com.charles.itsystem.vo.AnswerVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerMapper extends BaseMapper<Answer> {

    @Select("select a.answerID,  p.paperName, a.finalPoint, a.createTime\n" +
            "from tb_answer a\n" +
            "left join tb_paper p\n" +
            "on a.paperID = p.paperID\n" +
            "where a.userID = #{userID}")
    List<AnswerVO> selectAllAnswerByUserId(IPage<AnswerVO> iPage, Integer userID);  //根据用户ID查询用户答卷得分情况
}
