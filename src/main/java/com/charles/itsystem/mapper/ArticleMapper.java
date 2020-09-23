package com.charles.itsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.charles.itsystem.entity.Article;
import com.charles.itsystem.vo.ArticleVO;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    @Select("select * from tb_article where articleID = #{articleID}")
    @Results(value = {
            @Result(property = "comments", column = "articleID",
                    many = @Many(select = "com.charles.itsystem.mapper.CommentMapper.selectCommentByArticleId",fetchType = FetchType.EAGER))
    })
    ArticleVO selectArticleByArticleId(Integer articleID);  //根据文章ID查询文章具体信息
}
