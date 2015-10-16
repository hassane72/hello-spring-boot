package org.xiaoqiaotq;

import org.junit.Test;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/10/14
 */
public class ZipTest {
    @Test
    public void test() throws Exception {
        FileInputStream image = new FileInputStream("C:\\Users\\Public\\Pictures\\Sample Pictures\\juhua.jpg");
        int available = image.available();
        byte[] bytes = new byte[available];
        image.read(bytes);

        File file = File.createTempFile("tempfile", ".tmp"); // will create files like tempfileXXX.tmp
        file.deleteOnExit();
    }
    @Test
    public void test2() throws Exception {
        String zipname = "D:\\DD\\DD.zip";

        try {
            FileInputStream fis = new FileInputStream(zipname);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry;

            //getNextEntry()！=null时，ZipInputStream读取每个压缩文件，
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Unzipping: " + entry.getName());

                int size;
                byte[] buffer = new byte[2048];
                File temp = File.createTempFile("12ddd", null);
//                temp.deleteOnExit();
                FileOutputStream fos = new FileOutputStream(temp);
                System.err.println(temp.toURI());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
            }

            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
