package com.evilve.blog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evilve.blog.pojo.Blog;
import com.evilve.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> queryBlog(@Param("blogQuery") BlogQuery blogQuery, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    List<Blog> blogPage(@Param("query")String query,@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    Blog blogById(Long id);
    List<Blog>blogByTypeId(@Param("id") Long id,@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);
    List<Blog> findByDate(String date);
    List<String> findGroupDate();
}
