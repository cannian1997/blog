package com.evilve.blog.aspect;

import com.alibaba.fastjson.JSONObject;
import com.evilve.blog.annotaction.ExceptionLog;
import com.evilve.blog.pojo.ExceptionInfo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger= LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.evilve.blog.web.*.*(..))")
    public void log(){}

    @Pointcut("@annotation(com.evilve.blog.annotaction.ExceptionLog)")
    public void exceLog(){}


    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);


        logger.info("request : {}",requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("--------------doAfter-----------------");
    }

    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result :{}",result);
    }


    @AfterThrowing(value = "exceLog()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e){
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        //??????????????????
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //???????????????????????????
        Method method = methodSignature.getMethod();
        //??????????????????????????????????????????
        ExceptionLog annotation = method.getAnnotation(ExceptionLog.class);
        if (annotation != null) {
            String flag = annotation.exceFlag();
            if ("Y".equals(flag)) {
                Object[] args = joinPoint.getArgs();
                String params = JSONObject.toJSONString(args);
                String exceEstate = annotation.exceEstate(); //????????????
                String exceModule = annotation.exceModule();//????????????
                String exceUrl = annotation.exceUrl();//????????????
                //????????????????????????
                String message = e.getMessage();//????????????
                String messageDetail = e.toString();//??????????????????
                //??????????????????

            }
        }
    }

    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

}
