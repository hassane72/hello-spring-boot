package org.xiaoqiaotq;

import org.apache.log4j.xml.SAXErrorHandler;
import org.junit.Test;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/7.
 */
public class Base64Test {
    @Test
    public void test() throws Exception {
        FileInputStream image = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\juhua.jpg");
        int available = image.available();
        byte[] bytes = new byte[available];
        image.read(bytes);
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String encode = base64Encoder.encode(bytes);
        System.err.println("------------------------------");
        System.err.println(encode);
    }
}
