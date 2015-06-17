package org.xiaoqiaotq.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

/**
 * author : xiaoqiaotq@gmail.com
 * date   : 15-6-17.
 */
@Component
public class HelloListener implements ApplicationListener<ContextRefreshedEvent>{

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.err.println(event.getApplicationContext());
    }
}
