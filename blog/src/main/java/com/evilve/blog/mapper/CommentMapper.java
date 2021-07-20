package com.evilve.blog.mapper;

import com.evilve.blog.pojo.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface CommentMapper extends BaseMapper<Comment> {
    List<Comment> listCommentByBlogId(@Param("id") Long id,@Param("parentCommentId") Long parentCommentId);
}
