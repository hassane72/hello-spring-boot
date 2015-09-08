package org.xiaoqiaotq.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/8
 */
@Controller
public class MessageController {
    @RequestMapping("/messagePage")
    public String helloMessage() {
        return "message/atmosphere";
    }
}
