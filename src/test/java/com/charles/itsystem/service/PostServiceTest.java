package com.charles.itsystem.service;

import com.charles.itsystem.entity.Post;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PostServiceTest {

    @Autowired
    private PostService postService;

    /**
     * 查询所有岗位并分页功能单元测试
     */
    @Test
    void testSelectPostList(){
        System.out.println("结果："+postService.selectPostList(2,1).getTotal());
    }

    /**
     * 添加岗位功能单元测试
     */
    @Test
    void testAddPost(){
        Post post = new Post();
        post.setPostName("社员");

        System.out.println("受影响行数："+postService.addPost(post));
    }

    /**
     * 根据岗位ID查询岗位功能单元测试
     */
    @Test
    void testSelectPostById(){
        System.out.println(postService.selectPostById(1));
    }

    /**
     * 根据岗位ID更新岗位功能单元测试
     */
    @Test
    void testUpdatePostById(){
        Post post = new Post();
        post.setPostID(1);
        post.setPostName("普通社员");

        System.out.println("受影响行数："+postService.updatePostById(post.getPostID(),post));
    }
}
