package org.xiaoqiaotq.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xiaoqiaotq.domain.Book;

import javax.annotation.PostConstruct;

/**
 * author: will@ycode.cn
 * date  : 2016/1/21
 */
@Component
public class ConfigProperties {
    @Autowired
    private Book book;
    @PostConstruct
    public void init(){
        System.err.println("ConfigProperties is initialized");
        System.err.println(book);
    }
}
