package org.xiaoqiaotq;

/**
 * author: will@ycode.cn
 * date  : 2016/2/7
 */
public interface DD {
    default void message(){
        System.err.println("DD interface");
    }

}
