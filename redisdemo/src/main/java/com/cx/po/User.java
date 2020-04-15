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
}
