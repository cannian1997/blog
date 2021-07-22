package com.evilve.blog.service;

import com.evilve.blog.pojo.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface CommentService extends IService<Comment> {
    List<Comment> listCommentByBlogId(Long id,Long parentCommentId);
}
