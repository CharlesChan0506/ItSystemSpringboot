package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.charles.itsystem.entity.Post;

import java.util.List;

public interface PostService {
    IPage<Post> selectPostList(Integer current, Integer size);  //查询所有岗位并分页
    int addPost(Post post);  //添加岗位
    int deletePostById(Integer postID);  //根据岗位ID删除岗位
    int updatePostById(Integer postID, Post post);  //根据岗位ID更新岗位
    Post selectPostById(Integer postID);  //根据岗位ID查询岗位
    List<Post> selectAllPost();  //查询所有岗位
}
