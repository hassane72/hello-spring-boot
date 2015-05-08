package org.xiaoqiaotq.rest;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/hello")
public class LoginEndpoint {

    @GET
    public String message() {
        return "Hello";
    }

}
