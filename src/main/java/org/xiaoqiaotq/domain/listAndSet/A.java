package org.xiaoqiaotq.domain.listAndSet;

import org.xiaoqiaotq.domain.BaseEntity;

import javax.persistence.Entity;
import java.util.List;
import java.util.Set;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/11/30
 */
@Entity
public class A extends BaseEntity {

    private Set<B> bSet;
    private List<C> cList;

    public Set<B> getbSet() {
        return bSet;
    }

    public void setbSet(Set<B> bSet) {
        this.bSet = bSet;
    }

    public List<C> getcList() {
        return cList;
    }

    public void setcList(List<C> cList) {
        this.cList = cList;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
