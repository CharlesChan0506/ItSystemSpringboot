package com.charles.itsystem.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.Post;
import com.charles.itsystem.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    //查询所有岗位并分页
    @GetMapping("/{current}/{size}")
    public IPage<Post> selectPostList(@PathVariable Integer current, @PathVariable Integer size){
        return postService.selectPostList(current, size);
    }
    //添加岗位
    @PostMapping("/add")
    public int addPost(Post post){
        return postService.addPost(post);
    }
    //根据岗位ID删除岗位
    @DeleteMapping("/{postID}")
    public int deletePostById(@PathVariable Integer postID){
        return postService.deletePostById(postID);
    }
    //根据岗位ID更新岗位
    @PutMapping("/{postID}")
    public int updatePostById(@PathVariable Integer postID, Post post){
        return postService.updatePostById(postID,post);
    }
    //根据岗位ID查询岗位
    @GetMapping("/{postID}")
    public Post selectPostById(@PathVariable Integer postID){
        return postService.selectPostById(postID);
    }
    //查询所有岗位
    @GetMapping("/all")
    public List<Post> selectAllPost(){
        return postService.selectAllPost();
    }
}
