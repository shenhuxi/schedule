package com.zpself.scheduling.data.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author LiMingSu
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    public ApplicationContextUtil() {
    }
    /**
     * 根据接口Class获取所有实现该接口的Bean实例
     */
    public static <T> Map<String, T> getBeansOfType(Class<T> clzz) {
        return applicationContext.getBeansOfType(clzz);
    }
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)  throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }
}
