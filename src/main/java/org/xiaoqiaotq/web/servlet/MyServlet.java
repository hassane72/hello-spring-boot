package org.xiaoqiaotq.web.servlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/4/29.
 */
public class MyServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.err.println("-------------MyServle is invoking--------------fds第三方");
        resp.setContentType("text/html;charset=utf-8");
        Map<String, ? extends ServletRegistration> servletRegistrations = getServletContext().getServletRegistrations();
        for (Map.Entry<String,? extends ServletRegistration> entry:servletRegistrations.entrySet()){

            resp.getWriter().println(entry.getKey()+" : "+entry.getValue().getMappings());
        }
        resp.getWriter().write("</br>");
        Map<String, ? extends FilterRegistration> filterRegistrations = getServletContext().getFilterRegistrations();
        for (Map.Entry<String,? extends FilterRegistration> entry:filterRegistrations.entrySet()){

            resp.getWriter().println(entry.getKey() + " : " + entry.getValue().getUrlPatternMappings());
        }
    }
}
