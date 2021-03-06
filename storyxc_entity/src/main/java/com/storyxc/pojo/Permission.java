package com.storyxc.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Xc
 * @description
 * @createdTime 2019/12/19 13:41
 */
@Data
public class Permission implements Serializable {
    private Integer id;
    private String name; // 权限名称
    private String keyword; // 权限关键字，用于权限控制
    private String description; // 描述
    private Set<Role> roles = new HashSet<Role>(0);
}
