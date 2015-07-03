package org.xiaoqiaotq;

import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.ContainerInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.xiaoqiaotq.web.servlet.MyServlet;
import org.xiaoqiaotq.web.servlet.SecondServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wang on 2015/4/27.
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer{
    public static ApplicationContext context;
    public static Map map=new HashMap();
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        context=run;
    }
    @Bean
    public ServletRegistrationBean delegateServiceExporterServlet() {
        return new ServletRegistrationBean(new MyServlet(), "/myServlet");
    }
    @Bean
    public Servlet second() {
        return new SecondServlet();
    }
    @Bean
    public EmbeddedAtmosphereInitializer atmosphereInitializer() {
        return new EmbeddedAtmosphereInitializer();
    }
    @Bean
    public ServletRegistrationBean atmosphereServlet() {
        // Dispatcher servlet is mapped to '/home' to allow the AtmosphereServlet
        // to be mapped to '/chat'
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new AtmosphereServlet(), "/message/*");
        registration.addInitParameter("org.atmosphere.cpr.packages", "org.xiaoqiaotq");
        registration.addInitParameter("org.atmosphere.interceptor.HeartbeatInterceptor"
                + ".clientHeartbeatFrequencyInSeconds", "10");
        registration.setLoadOnStartup(0);
        // Need to occur before the EmbeddedAtmosphereInitializer
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
    private static class EmbeddedAtmosphereInitializer extends ContainerInitializer
            implements ServletContextInitializer {

        @Override
        public void onStartup(ServletContext servletContext) throws ServletException {
            onStartup(Collections.<Class<?>> emptySet(), servletContext);
        }

    }
}
