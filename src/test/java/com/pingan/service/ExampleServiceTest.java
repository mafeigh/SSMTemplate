package com.pingan.service;

import com.pingan.mapper.ApplyMapper;
import com.pingan.model.Apply;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/**
 * Created by ZhangYu on 2015/9/23.
 * Unit test with mockito
 */
public class ExampleServiceTest {
    private ExampleService exampleService;

    @BeforeClass
    public void beforeClass() {
        exampleService = new ExampleService();

        //创建mock对象，参数可以是类，也可以是接口
        ApplyMapper mapper = mock(ApplyMapper.class);

        //设置方法的预期返回值
        when(mapper.selectByPrimaryKey(1)).thenReturn(new Apply());
        when(mapper.selectByPrimaryKey(0)).thenReturn(null);
        exampleService.setApplyMapper(mapper);
    }

    @Test
    public void getApplyTest() {
        assertThat(exampleService.getApply(0), nullValue());
        assertThat(exampleService.getApply(1), notNullValue());
    }

    @AfterClass
    public void afterClass() {
    }
}
