package org.xiaoqiaotq.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/13.
 */
@Controller
public class OrdinaryController {
    @RequestMapping("/visit")
    public String visit() {
        return "visit";
    }
}
