package org.xiaoqiaotq.domain.ManyToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.xiaoqiaotq.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/7/22
 *
 *  项目
 */
@Entity
public class Project extends BaseEntity {
    private String name;
    @ManyToMany
    @JsonManagedReference
    private Set<Employee> employees=new HashSet<>();

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

}
