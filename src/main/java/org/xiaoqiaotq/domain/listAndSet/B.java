package org.xiaoqiaotq.domain.listAndSet;

import org.xiaoqiaotq.domain.BaseEntity;

import javax.persistence.Entity;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/11/30
 */
@Entity
public class B extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
