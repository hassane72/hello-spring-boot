package org.xiaoqiaotq.cluster;

import org.apache.thrift.TException;
import org.springframework.stereotype.Component;
import service.demo.Hello;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/8/21
 */
@Component
public class HelloServiceHandler implements Hello.Iface {
    @Override
    public String helloString(String para) throws TException {
        return "hello who are you"+para;
    }

    @Override
    public int helloInt(int para) throws TException {
        return 0;
    }

    @Override
    public boolean helloBoolean(boolean para) throws TException {
        return false;
    }

    @Override
    public void helloVoid() throws TException {

    }

    @Override
    public String helloNull() throws TException {
        return null;
    }
}
