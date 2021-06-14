package com.evilve.blog.mapper;

import com.evilve.blog.pojo.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.evilve.blog.vo.BlogQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    List<Blog> queryBlog(@Param("blogQuery") BlogQuery blogQuery, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    List<Blog> blogPage(@Param("query")String query,@Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);
    Blog blogById(Long id);
    List<Blog>blogByTypeId(@Param("id") Long id,@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);
    List<Blog> findByDate(String date);
    List<String> findGroupDate();
}
