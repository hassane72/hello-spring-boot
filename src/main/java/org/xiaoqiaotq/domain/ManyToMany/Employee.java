package org.xiaoqiaotq.domain.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.xiaoqiaotq.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/7/22
 *
 * 人员
 */
@Entity
public class Employee extends BaseEntity {
    private String name;

    @ManyToMany(mappedBy = "employees")
    @JsonBackReference
    private Set<Project> projects=new HashSet<>();
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }
}
