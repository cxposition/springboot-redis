package com.cx.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cx
 * @Time 2020/4/13 21:18
 * @Description
 */
@Data
public class User implements Serializable{
    private String id;
    private String name;
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
