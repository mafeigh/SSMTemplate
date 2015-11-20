package com.pingan.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.pingan.validator.NotNullEx;
import javax.validation.constraints.Max;
import java.util.Date;

public class Apply {
    // field为字段名，在错误信息中使用
    @NotNullEx(field = "id", message="{is.null}")
    @Max(value = 100)
    private Integer id;

    @NotNullEx(field = "name", message="{is.null}")
    private String name;

    private Double money;

    private Date birthdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}