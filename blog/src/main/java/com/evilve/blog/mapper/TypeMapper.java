package com.evilve.blog.mapper;

import com.evilve.blog.pojo.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
@Repository
public interface TypeMapper extends BaseMapper<Type> {
    List<Type> getTypes(@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);
    List<Type> leftTypes(@Param("currentPage") Integer currentPage,@Param("pageSize") Integer pageSize);
}
