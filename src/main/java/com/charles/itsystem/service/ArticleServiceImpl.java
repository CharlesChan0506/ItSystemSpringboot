package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.*;
import com.charles.itsystem.mapper.*;
import com.charles.itsystem.vo.ArticlePage;
import com.charles.itsystem.vo.ArticleVO;
import com.charles.itsystem.vo.CommentPage;
import com.charles.itsystem.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ArticleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private StaffMapper staffMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 发表文章
     * @param article
     * @return
     */
    @Override
    public int addArticle(Article article) {
        return articleMapper.insert(article);
    }

    /**
     * 查询所有文章并分页
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public ArticlePage selectArticleList(Integer current, Integer size) {
        ArticlePage articlePage = new ArticlePage();
        List<ArticleVO> articleVOList = new ArrayList<>();

        Page<Article> page = new Page<>(current,size);
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        IPage<Article> iPage = articleMapper.selectPage(page,wrapper);
        List<Article> articles = iPage.getRecords();
        articlePage.setTotal(iPage.getTotal());  //设置总记录数

        for (Article article : articles) {
            new Article();
            new Staff();
            Staff staff = staffMapper.selectById(article.getStaffID());
            new Department();
            Department department = departmentMapper.selectById(article.getDepID());

            ArticleVO articleVO = new ArticleVO();
            articleVO.setArticleID(article.getArticleID());
            articleVO.setStaffName(staff.getStaffName());
            articleVO.setDepName(department.getDepName());
            articleVO.setTitle(article.getTitle());
            articleVO.setArticleContent(article.getArticleContent());

            articleVOList.add(articleVO);
        }
        articlePage.setRecords(articleVOList);

        return articlePage;
    }

    /**
     * 根据文章ID查询文章具体信息
     * @param articleID
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public ArticleVO selectArticleByArticleId(Integer articleID) {
        new Article();
        Article article = articleMapper.selectById(articleID);
        new Staff();
        Staff staff = staffMapper.selectById(article.getStaffID());
        new Department();
        Department department = departmentMapper.selectById(article.getDepID());

        ArticleVO articleVO = new ArticleVO();
        articleVO.setArticleID(articleID);
        articleVO.setStaffName(staff.getStaffName());
        articleVO.setDepName(department.getDepName());
        articleVO.setTitle(article.getTitle());
        articleVO.setArticleContent(article.getArticleContent());

        return articleVO;
    }

    /**
     * 根据文章ID删除文章
     * @param articleID
     * @return
     */
    @Override
    public int deleteArticleByArticleId(Integer articleID) {
        return articleMapper.deleteById(articleID);
    }

    /**
     * 发表评论
     * @param comment
     * @return
     */
    @Override
    public int addComment(Comment comment) {
        return commentMapper.insert(comment);
    }

    /**
     * 查询评论并分页
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public CommentPage selectCommentList(Integer articleID, Integer current, Integer size) {
        CommentPage commentPage = new CommentPage();
        List<CommentVO> commentVOList = new ArrayList<>();

        Page<Comment> page = new Page<>(current,size);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("articleID",articleID);
        IPage<Comment> iPage = commentMapper.selectPage(page,wrapper);
        List<Comment> comments = iPage.getRecords();
        commentPage.setTotal(iPage.getTotal());  //设置总记录数

        for (Comment comment : comments) {
            new Comment();
            new Staff();
            Staff staff = staffMapper.selectById(comment.getStaffID());
            new Department();
            Department department = departmentMapper.selectById(comment.getDepID());

            CommentVO commentVO = new CommentVO();
            commentVO.setCommentID(comment.getCommentID());
            commentVO.setStaffName(staff.getStaffName());
            commentVO.setDepName(department.getDepName());
            commentVO.setComment(comment.getComment());

            commentVOList.add(commentVO);
        }
        commentPage.setRecords(commentVOList);

        return commentPage;
    }

    /**
     * 删除评论
     * @param commentID
     * @return
     */
    @Override
    public int deleteCommentByCommentId(Integer commentID) {
        return commentMapper.deleteById(commentID);
    }
}
