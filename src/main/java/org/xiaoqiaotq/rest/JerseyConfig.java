package org.xiaoqiaotq.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

//@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(LoginEndpoint.class);
    }
}
