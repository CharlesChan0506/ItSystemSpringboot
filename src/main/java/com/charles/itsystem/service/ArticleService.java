package com.charles.itsystem.service;

import com.charles.itsystem.entity.Article;
import com.charles.itsystem.entity.Comment;
import com.charles.itsystem.vo.ArticlePage;
import com.charles.itsystem.vo.ArticleVO;
import com.charles.itsystem.vo.CommentPage;

public interface ArticleService {

    int addArticle(Article article);  //发表文章
    ArticlePage selectArticleList(Integer current, Integer size);  //查询所有文章并分页
    ArticleVO selectArticleByArticleId(Integer articleID);  //根据文章ID查询文章具体信息
    int deleteArticleByArticleId(Integer articleID);  //根据文章ID删除文章
    int addComment(Comment comment);  //发表评论
    CommentPage selectCommentList(Integer articleID, Integer current, Integer size);  //根据文章ID查询评论并分页
    int deleteCommentByCommentId(Integer commentID);  //根据评论ID删除评论
}
