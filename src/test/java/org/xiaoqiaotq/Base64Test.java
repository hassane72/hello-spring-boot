package org.xiaoqiaotq;

import org.junit.Test;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/5/7.
 */
public class Base64Test {
    @Test
    public void test() throws Exception {
//        FileInputStream image = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\juhua.jpg");
//        int available = image.available();
//        byte[] bytes = new byte[available];
//        image.read(bytes);
//        BASE64Encoder base64Encoder = new BASE64Encoder();
//        String encode = base64Encoder.encode(bytes);
//        System.err.println("------------------------------");
//        System.err.println(encode);
    }
    @Test
    public void test2() throws Exception {
        String test="打发打发aaaa";
        System.err.println(test);
        String gb18030 = new String(test.getBytes("gb18030"), "utf-8");
        System.err.println(gb18030);
    }
    @Test
    public void test3() throws Exception {
        String test="打发打发";
        byte[] bytes = test.getBytes("gb18030");
        for (byte aByte : bytes) {
            System.err.println(Integer.toHexString(aByte));
        }
    }
    @Test
    public void test4() throws Exception {
        int a=3;
        if(a>1){
            System.err.println(1);
        } if (a>2){
            System.err.println(2);
        }
    }
}
