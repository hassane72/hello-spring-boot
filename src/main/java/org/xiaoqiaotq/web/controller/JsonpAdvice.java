package org.xiaoqiaotq.web.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;
import org.xiaoqiaotq.domain.User;

@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {
    public JsonpAdvice() {
        super("callback");
    }
//    @ModelAttribute
//    public User aa(){
//        User u=new User();
//        u.setUsername("sdf"+1);
//        return u;
//    }
} 