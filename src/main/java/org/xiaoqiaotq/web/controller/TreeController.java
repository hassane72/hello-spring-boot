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

        return customUserGroupRepository.findOne(1l);
    }
}
