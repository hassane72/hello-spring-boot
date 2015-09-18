package org.xiaoqiaotq.domain;

import javax.persistence.Entity;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/18
 */
@Entity
public class AutoDelLogTimeEntity extends BaseEntity {
    //0,3,6,12
    private int month;

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
}
