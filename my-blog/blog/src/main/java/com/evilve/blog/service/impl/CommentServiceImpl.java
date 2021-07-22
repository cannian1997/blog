package com.evilve.blog.service.impl;

import com.evilve.blog.pojo.Comment;
import com.evilve.blog.mapper.CommentMapper;
import com.evilve.blog.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<Comment> listCommentByBlogId(Long id,Long parentCommentId) {
        return commentMapper.listCommentByBlogId(id,parentCommentId);
    }
}
