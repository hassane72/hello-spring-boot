package org.xiaoqiaotq;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.junit.Test;
import org.xiaoqiaotq.domain.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/7.
 */
public class ObjectMapperTest {

    @Test
    public void test() throws Exception {
        User u = new User();
        u.setId(2l);
        u.setUsername("dsf");
        u.setPass("asdf");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String s = objectMapper.writeValueAsString(u);
        System.err.println(s);
    }
    @Test
    public void test2() throws Exception {
        File file = new File("d:/webrtc_trace.log");
        FileInputStream fileInputStream = new FileInputStream(file);
        boolean assignableFrom = InputStream.class.isAssignableFrom(FileInputStream.class);
        System.err.println(assignableFrom);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        String s = objectMapper.writeValueAsString(fileInputStream);
        System.err.println(s);
    }
    @Test
    public void test3() throws Exception {
        Long a=3l;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        Class<Long> type = a.TYPE;
        System.err.println(type.getSimpleName());
        System.err.println(User.class);
        String s = objectMapper.writeValueAsString(a);
        System.err.println(s);
    }
}
