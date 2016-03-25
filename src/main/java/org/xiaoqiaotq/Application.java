package org.xiaoqiaotq;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.ContainerInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.xiaoqiaotq.cluster.HelloServiceHandler;
import org.xiaoqiaotq.domain.Iface.Banana;
import org.xiaoqiaotq.domain.Iface.Fruit;
import org.xiaoqiaotq.domain.Iface.Orange;
import org.xiaoqiaotq.web.servlet.MyServlet;
import org.xiaoqiaotq.web.servlet.SecondServlet;
import service.demo.Hello;

import javax.annotation.PostConstruct;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wang on 2015/4/27.
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
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
//        registration.addInitParameter(ApplicationConfig.RESUME_ON_HEARTBEAT, "false");
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
    @Bean
    public TProtocolFactory tProtocolFactory() {
        return new TBinaryProtocol.Factory();
    }
    /**
     * 接收消息
     * @param protocolFactory
     * @param handler
     * @return
     */
    @Bean
    public Servlet helloService(TProtocolFactory protocolFactory, HelloServiceHandler handler) {
        return new TServlet(new Hello.Processor<>(handler), protocolFactory);
    }

    @Autowired
    private ServletContext servletContext;

    @Bean
    public BroadcasterFactory broadcasterFactory() {
//        AtmosphereFramework framework = (AtmosphereFramework) servletContext.getAttribute("atmosphereServlet");
//        return framework.getBroadcasterFactory();
        return null;
    }
    @Bean
    public Fruit orange34(){
        return new Orange();
    }
    @Bean
    public Fruit banana2(){
        return new Banana();
    }
    @Autowired
    List<Fruit> fruitList;
    @PostConstruct
    public void init(){
        System.err.println(fruitList);
    }

    @Bean
    public TaskExecutor dsf(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(4);
        return  threadPoolTaskExecutor;
    }
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        WordsFilter wordsFilter = new WordsFilter();
//        registrationBean.setFilter(wordsFilter);
//        registrationBean.setOrder(Integer.MIN_VALUE);
//        registrationBean.addUrlPatterns("/*");
//        return registrationBean;
//    }
}
