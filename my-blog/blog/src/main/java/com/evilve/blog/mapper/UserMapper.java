package com.evilve.blog.mapper;

import com.evilve.blog.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface UserMapper extends BaseMapper<User> {
    User getUser(@Param("username") String username,@Param("password") String password);
}
