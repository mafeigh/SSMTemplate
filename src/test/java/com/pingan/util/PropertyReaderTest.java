package com.pingan.util;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

/**
 * Created by zhangyu on 2015/9/23.
 * Integrate test with spring
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class PropertyReaderTest extends AbstractTestNGSpringContextTests {
    @Resource
    protected PropertyReader propertyReader;

    @Test
    public void getNullValueTest() {
        assertThat(propertyReader.getValue("upload.limit"), nullValue());
    }

    @Test
    public void getValueTest() {
        assertThat(propertyReader.getValue("UploadFileMaxSize"), equalTo("10"));
    }
}
