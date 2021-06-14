package com.evilve.blog.service;

import com.evilve.blog.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
public interface TypeService extends IService<Type> {
    List<Type> getTypes(Integer currentPage,Integer pageSize);
    List<Type> leftTypes(Integer currentPage,Integer pageSize);
}
