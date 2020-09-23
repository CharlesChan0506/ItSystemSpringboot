package com.charles.itsystem.controller;

import com.charles.itsystem.entity.Article;
import com.charles.itsystem.entity.Comment;
import com.charles.itsystem.service.ArticleService;
import com.charles.itsystem.vo.ArticlePage;
import com.charles.itsystem.vo.ArticleVO;
import com.charles.itsystem.vo.CommentPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    //发表文章
    @PostMapping("/add")
    public int addArticle(Article article){
        return articleService.addArticle(article);
    }
    //查询所有文章并分页
    @GetMapping("/{current}/{size}")
    public ArticlePage selectArticleList(@PathVariable Integer current, @PathVariable Integer size){
        return articleService.selectArticleList(current, size);
    }
    //根据文章ID查询文章具体信息
    @GetMapping("/detail/{articleID}")
    public ArticleVO selectArticleByArticleId(@PathVariable Integer articleID){
        return articleService.selectArticleByArticleId(articleID);
    }
    //根据文章ID删除文章
    @DeleteMapping("/{articleID}")
    public int deleteArticleByArticleId(@PathVariable Integer articleID){
        return articleService.deleteArticleByArticleId(articleID);
    }
    //发表评论
    @PostMapping("/comment/add")
    public int addComment(Comment comment){
        return articleService.addComment(comment);
    }
    //查询评论并分页
    @GetMapping("/comment/{articleID}/{current}/{size}")
    public CommentPage selectCommentList(@PathVariable Integer articleID, @PathVariable Integer current, @PathVariable Integer size){
        return articleService.selectCommentList(articleID, current, size);
    }
    //删除评论
    @DeleteMapping("/comment/{commentID}")
    public int deleteCommentByCommentId(@PathVariable Integer commentID){
        return articleService.deleteCommentByCommentId(commentID);
    }
}
