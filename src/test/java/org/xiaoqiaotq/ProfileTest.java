package org.xiaoqiaotq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.xiaoqiaotq.domain.Book;

/**
 * author: will@ycode.cn
 * date  : 2016/1/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@WebAppConfiguration
public class ProfileTest {
    @Autowired
    private Book book;
    @Test
    public void test1() throws Exception {
        System.err.println("===================ProfileTest=================");
        System.err.println(book);
    }
}
