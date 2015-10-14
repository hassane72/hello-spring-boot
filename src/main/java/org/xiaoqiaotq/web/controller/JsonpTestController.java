package org.xiaoqiaotq.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoqiaotq.domain.User;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/14
 *
 * /helloJsonp?callback=aaa
 * ----->
 * aaa({.....})
 */
@RestController
public class JsonpTestController {
    @RequestMapping(value = "/helloJsonp")
    public User hello(){
        User u=new User();
        u.setId((long)1);
        u.setUsername("dsf");
        return u;
    }
}
