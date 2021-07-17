package com.evilve.blog.annotaction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//保留策略运行时
@Target(ElementType.METHOD)//作用在方法上
public @interface ExceptionLog {
    //异常检测是否启用  Y 启用 N 不启用
    String exceFlag() default "Y";

    //异常模块
    String exceModule(); //按照目前接口所属模块

    //异常接口路径
    String exceUrl();  //当前标记方法controller请求路径

    //异常等级
    String exceEstate();  //业务异常按1自增长，越大等级越高
}
