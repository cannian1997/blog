package com.evilve.blog.mapper;

import com.evilve.blog.pojo.ExceptionInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 异常日志表 Mapper 接口
 * </p>
 *
 * @author Evilve
 * @since 2021-07-17
 */
@Mapper
public interface ExceptionInfoMapper extends BaseMapper<ExceptionInfo> {

}
