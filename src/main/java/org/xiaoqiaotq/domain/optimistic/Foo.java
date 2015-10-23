package org.xiaoqiaotq.domain.optimistic;

import org.xiaoqiaotq.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Version;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/10
 */
@Entity
public class Foo extends BaseEntity{
    private String firstName;
    private int age;
    private String secondName;
    private String thirdName;
    @Version
    private Long version;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
