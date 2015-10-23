package org.xiaoqiaotq.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoqiaotq.domain.optimistic.Foo;
import org.xiaoqiaotq.domain.tree.CustomUserGroup;
import org.xiaoqiaotq.repository.CustomUserGroupRepository;
import org.xiaoqiaotq.repository.FooRepository;

import javax.transaction.Transactional;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/8
 */
@RestController
@RequestMapping("/optimistic")
public class OptimisticController {
    @Autowired
    private FooRepository fooRepository ;

    @RequestMapping("/save")
    public String save() {
        Foo foo=new Foo();
        foo.setFirstName("adsfd");
        foo.setSecondName("阿三地方");
        foo.setThirdName("是fads发sadf");
        fooRepository.save(foo);
        return "success";
    }
//    @RequestMapping("/load")
//    public CustomUserGroup load() {
//        customUserGroupRepository.findOne(2l);
//        customUserGroupRepository.findOne(3l);
//        return customUserGroupRepository.findOne(1l);
//    }
    @RequestMapping("/update1")
    @Transactional
    public Foo update1() {

        Foo one = fooRepository.findOne(1l);
        one.setFirstName("w");
        return one;
    }

    @RequestMapping("/update2")
    @Transactional
    public Foo update2() {

        Foo one = fooRepository.findOne(1l);
        one.setSecondName("awsf");
        return one;
    }
    @RequestMapping("/update3")
    @Transactional
    public Foo update3() {

        Foo one = fooRepository.findOne(1l);
        one.setThirdName("eeewa");
        return one;
    }
}
