package com.pingan.util;

import org.springframework.context.MessageSource;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Locale;

@Component
// jmx支持
@ManagedResource(objectName = "annojmx:name=PropertyReader", description = "PropertyReader")
public class PropertyReader {
    @Resource
    private MessageSource messageSource;

    private Integer jmxAttribute;

    @ManagedAttribute
    public Integer getJmxAttribute() {
        return jmxAttribute;
    }

    @ManagedAttribute
    public void setJmxAttribute(Integer jmxAttribute) {
        this.jmxAttribute = jmxAttribute;
    }

    @ManagedOperation
    public String getValue(String key) {
        return getValue(key, null);
    }

    public String getValue(String code, String message, Object... argument) {
        return messageSource.getMessage(code, argument, message, Locale.getDefault());
    }
}
