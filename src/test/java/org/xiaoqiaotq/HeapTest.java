package org.xiaoqiaotq;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/9/16
 */
public class HeapTest {
    static final int SIZE=2*1024*1024;
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        int[] i = new int[SIZE];
        TimeUnit.SECONDS.sleep(100);
        System.err.println("success");
    }

    @Test
    public void testName() throws Exception {
        String dateString = "2015/3/1";
        Date date = (new SimpleDateFormat("yyyy/MM/dd")).parse(dateString);
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.add(Calendar.DAY_OF_MONTH, -1);
        System.err.println(instance.getTime());

    }
}
