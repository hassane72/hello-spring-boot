package org.xiaoqiaotq.domain.tree;

import org.xiaoqiaotq.domain.User;

import javax.persistence.*;
import java.util.Set;

/**
 * 自定义用户分组
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/19.
 */
@Entity
public class CustomUserGroup extends Node<CustomUserGroup>{
    @Id
    @GeneratedValue
    private Long id;

    private String groupName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
