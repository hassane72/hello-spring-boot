package org.xiaoqiaotq.web.controller;

import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/8
 */
@Controller
public class MessageController {
    @Autowired
    BroadcasterFactory broadcasterFactory;

    @RequestMapping("/messagePage")
    public String helloMessage() {
        return "message/atmosphere";
    }


    @RequestMapping("/printBroadcastFactory")
    @ResponseBody
    public String helloMessage2() {

        return broadcasterFactory.toString();
    }
}
