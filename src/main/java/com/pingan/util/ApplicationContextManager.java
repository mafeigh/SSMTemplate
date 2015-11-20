package com.pingan.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextManager implements ApplicationContextAware {

    public static ApplicationContext contex;

    public void setApplicationContext(ApplicationContext arg0) {
        ApplicationContextManager.contex = arg0;
    }

    public static Object getBean(String key) {
        if (contex == null) return null;
        return contex.getBean(key);
    }
}
