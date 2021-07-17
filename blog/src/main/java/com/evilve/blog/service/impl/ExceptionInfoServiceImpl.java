package com.evilve.blog.service.impl;

import com.evilve.blog.exception.entities.ExceptionInfo;
import com.evilve.blog.exception.mapper.ExceptionInfoMapper;
import com.evilve.blog.exception.service.ExceptionInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 异常日志表 服务实现类
 * </p>
 *
 * @author Evilve
 * @since 2021-07-17
 */
@Service
public class ExceptionInfoServiceImpl extends ServiceImpl<ExceptionInfoMapper, ExceptionInfo> implements ExceptionInfoService {

}
