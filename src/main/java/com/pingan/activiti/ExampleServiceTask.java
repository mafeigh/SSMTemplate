package com.pingan.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

// 这个类必须线程安全，如果使用成员变量必须用threadlocal
public class ExampleServiceTask implements JavaDelegate {
    // 参数转换成大写
    public void execute(DelegateExecution execution) throws Exception {
        String var = (String) execution.getVariable("input");
        var = var.toUpperCase();
        execution.setVariable("input", var);
    }

}