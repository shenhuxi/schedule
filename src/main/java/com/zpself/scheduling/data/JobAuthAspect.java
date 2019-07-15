package com.zpself.scheduling.data;

import com.zpself.scheduling.handler.CommonError;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;

@Aspect
@Component
public class JobAuthAspect {

    @Value("${jobAuth:}")
    private String jobAuth;


    @Pointcut("execution(* com.zpself.scheduling.web.controller..*.*(..))")
    public void jobAspect() {
    }

    @Around("jobAspect()")
    public Object doServiceAround(ProceedingJoinPoint proceedingJoinPoint) throws CommonError {
        try {
            if(StringUtils.isBlank(jobAuth)){
                return  proceedingJoinPoint.proceed();
            }
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            List<GrantedAuthority> list=new ArrayList<>(authorities);
            for (GrantedAuthority auth:list) {
                if(jobAuth.equals(auth.getAuthority())){
                    return  proceedingJoinPoint.proceed();
                }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw new RuntimeException(throwable);
        }
        HashMap<Object, Object> result = new LinkedHashMap<>(2);
        result.put("message","没有权限访问");
        result.put("exception",null);
        return result;

    }

}
