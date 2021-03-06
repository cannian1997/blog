package com.evilve.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.evilve.blog.pojo.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface UserService extends IService<User> {
    User getUser( String username, String password);
}
