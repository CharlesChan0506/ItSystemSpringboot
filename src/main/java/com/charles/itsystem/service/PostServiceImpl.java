package com.charles.itsystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.charles.itsystem.entity.Post;
import com.charles.itsystem.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("PostService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;

    /**
     * 查询所有岗位并分页
     * @param current
     * @param size
     * @return
     */
    @SuppressWarnings("Duplicates")
    @Override
    public IPage<Post> selectPostList(Integer current, Integer size) {
        Page<Post> page = new Page<>(current, size);
        QueryWrapper<Post> wrapper = new QueryWrapper<>();

        return postMapper.selectPage(page,wrapper);
    }

    /**
     * 添加岗位
     * @param post
     * @return
     */
    @Override
    public int addPost(Post post){
        return postMapper.insert(post);
    }

    /**
     * 根据岗位ID删除岗位
     * @param postID
     * @return
     */
    @Override
    public int deletePostById(Integer postID){
        return postMapper.deleteById(postID);
    }

    /**
     * 根据岗位ID更新岗位
     * @param post
     * @return
     */
    @Override
    public int updatePostById(Integer postID, Post post){
        UpdateWrapper<Post> wrapper = new UpdateWrapper<>();
        wrapper.set("postName",post.getPostName())
                .eq("postID",postID);

        return postMapper.update(null,wrapper);
    }

    /**
     * 根据岗位ID查询岗位
     * @param postID
     * @return
     */
    @Override
    public Post selectPostById(Integer postID){
        return postMapper.selectById(postID);
    }

    /**
     * //查询所有岗位
     * @return
     */
    @Override
    public List<Post> selectAllPost() {
        QueryWrapper<Post> wrapper = new QueryWrapper<>();

        return postMapper.selectList(wrapper);
    }
}
