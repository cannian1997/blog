package com.evilve.blog.service.impl;

import com.evilve.blog.pojo.Type;
import com.evilve.blog.mapper.TypeMapper;
import com.evilve.blog.service.TypeService;
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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getTypes(Integer currentPage, Integer pageSize) {
        List<Type> list=null;
        if (currentPage==null||pageSize==null){
            list=typeMapper.getTypes(currentPage,pageSize);
        }else {
            list=typeMapper.getTypes((currentPage-1)*pageSize,pageSize);
        }
        return list;
    }

    @Override
    public List<Type> leftTypes(Integer currentPage, Integer pageSize) {
        return typeMapper.leftTypes((currentPage-1)*pageSize,pageSize);
    }
}
