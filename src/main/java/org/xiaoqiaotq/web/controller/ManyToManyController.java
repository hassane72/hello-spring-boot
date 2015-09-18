package org.xiaoqiaotq.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoqiaotq.domain.ManyToMany.Employee;
import org.xiaoqiaotq.domain.ManyToMany.Project;
import org.xiaoqiaotq.repository.EmployeeRepository;
import org.xiaoqiaotq.repository.ProjectRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/4.
 */
@RestController
@RequestMapping("manyToMany")
public class ManyToManyController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ProjectRepository projectRepository;
    @RequestMapping("/save")
    public Project home(){
        Project project=new Project();
        project.setName("project1");
        Employee employee1=new Employee();
        employee1.setName("employee1");
        Employee employee2=new Employee();
        employee2.setName("employee2");
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);
        Set set = new HashSet<>();
        set.add(employee1);
        set.add(employee2);
        project.setEmployees(set);
        projectRepository.save(project);
        return  project;
    }
    @RequestMapping("/findProject/{id}")
    @Transactional
    public Project findOne1(@PathVariable("id") long id){
        System.err.println("hello ");
        Project one = projectRepository.findOne(id);
        Set<Employee> employees = one.getEmployees();
        employees.forEach((t)->t.setName("llllaaaaa"));
        return one;
    }
    @RequestMapping("/findEmployee/{id}")
    public Employee findOne2(@PathVariable("id") long id){
        System.err.println("hello ");
        Employee one = employeeRepository.findOne(id);
        boolean exitsName = employeeRepository.exitsName("dsfdsf", id);
        System.err.println(exitsName);
        return one;
    }

}
