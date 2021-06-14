package com.evilve.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evilve.blog.pojo.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.evilve.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface BlogService extends IService<Blog> {

    List<Blog> queryBlog(BlogQuery blogQuery, Integer currentPage,Integer pageSize);
    List<Blog> blogPage(String query,Integer currentPage,Integer pageSize);
    Blog blogById(Long id);
    List<Blog>blogByTypeId(Long id,Integer currentPage,Integer pageSize);
    Map<String,List<Blog>> archiveBlog();
}
