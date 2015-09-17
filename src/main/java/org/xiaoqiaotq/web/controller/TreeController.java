package org.xiaoqiaotq.web.controller;

import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.domain.tree.CustomUserGroup;
import org.xiaoqiaotq.repository.CustomUserGroupRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/8
 */
@RestController
@RequestMapping("/tree")
public class TreeController {
    @Autowired
    private CustomUserGroupRepository customUserGroupRepository;

    @RequestMapping("/save")
    public String save() {
        CustomUserGroup customUserGroup=new CustomUserGroup();
        customUserGroup.setGroupName("001078");
        customUserGroupRepository.save(customUserGroup);
        return "success";
    }
    @RequestMapping("/load")
    public CustomUserGroup load() {
        customUserGroupRepository.findOne(2l);
        customUserGroupRepository.findOne(3l);
        return customUserGroupRepository.findOne(1l);
    }
    @RequestMapping("/update1")
    @Transactional
    public CustomUserGroup update1() {

        CustomUserGroup one = customUserGroupRepository.findOne(1l);
        one.setGroupName("w");
        return one;
    }

    @RequestMapping("/update2")
    @Transactional
    public CustomUserGroup update2() {

        CustomUserGroup one = customUserGroupRepository.findOne(2l);
        one.setGroupName("awsf");
        return one;
    }
    @RequestMapping("/update3")
    @Transactional
    public CustomUserGroup update3() {

        CustomUserGroup one = customUserGroupRepository.findOne(3l);
        one.setGroupName("eeewa");
        return one;
    }
}
