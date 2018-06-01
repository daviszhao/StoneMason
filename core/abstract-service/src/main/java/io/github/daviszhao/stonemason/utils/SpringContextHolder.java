package io.github.daviszhao.stonemason.utils;


import org.springframework.context.ApplicationContext;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class SpringContextHolder {
    private static ApplicationContext context;

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    @Inject
    public void setContext(ApplicationContext context1) {
        context = context1;
    }
}
