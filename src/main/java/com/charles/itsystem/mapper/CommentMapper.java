package com.charles.itsystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    @Select("select * from tb_comment where articleID = #{articleID} limit 0,5")
    List<Comment> selectCommentByArticleId(IPage<Comment> iPage, Integer articleID);  //根据文章ID查询所有评论(初始化)
}
