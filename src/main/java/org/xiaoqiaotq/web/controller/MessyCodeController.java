package org.xiaoqiaotq.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/7/3.
 * 乱码测试
 */
@Controller
public class MessyCodeController {
    @RequestMapping("/testMessy")
    @ResponseBody
    public String test(String param){
        return param;
    }
    @RequestMapping("/messy/form")
    public String form(String param){
        return "messy_code_form";
    }
}
