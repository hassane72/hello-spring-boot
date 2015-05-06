package org.xiaoqiaotq.web.servlet;

import org.springframework.context.annotation.Bean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/4/29.
 */
public class SecondServlet extends HttpServlet {
    private static final String SHARE ="share";
    private static final AtomicInteger i =new AtomicInteger();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("doGet  " + req + " : " + resp);
        req.getRequestDispatcher("/sample.html").forward(req, resp);

    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("doPost  " + req + " : " + resp);
        resp.getWriter().println("hello second");
    }
}
