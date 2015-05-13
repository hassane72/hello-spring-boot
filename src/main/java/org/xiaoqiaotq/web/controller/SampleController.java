package org.xiaoqiaotq.web.controller;

import com.google.gson.Gson;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereServlet;
import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xiaoqiaotq.domain.Book;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.web.messageService.Message;

import javax.inject.Inject;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by wang on 2015/4/21.
 */
@RestController
public class SampleController {

    @Value("${asdf}")
    String hello;
    @Autowired
    Book book =new Book("concurrency aa");
    @Autowired
    JavaMailSender javaMailSender;

    @RequestMapping(value = "/aadda")
    public String hello(){
        System.err.println("dsfsdf" +
                "sdfsdfdsf杀毒发");
        System.err.println("你好ssssss");
        return "你好ddddddddd";
    }

    @RequestMapping(value = "/bbb")
    public String bbb(){
        return "bbb";
    }

    @RequestMapping(value = "/user/{id}")
    public User hello(@PathVariable("id") int id){
        User u=new User();
        u.setId((long)id);
        u.setUsername("张三"+id);
        return u;
    }

    @RequestMapping("/properties")
    public java.util.Properties properties() {
        return System.getProperties();
    }

    @RequestMapping("/testProp")
    public Book book() {
        book.setPrice(3);
        return book;
    }

    @RequestMapping("/message1")
    public Book message(HttpServletRequest request) {
        BroadcasterFactory broadcasterFactory = (BroadcasterFactory) request.getServletContext().getAttribute(BroadcasterFactory.class.getName());
        book.setId(66);
        return book;
    }

    @RequestMapping("/getServletContext")
    public Book getServletContext(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        Enumeration<String> attributeNames = servletContext.getAttributeNames();
        while (attributeNames.hasMoreElements()){
            String s = attributeNames.nextElement();
            System.err.println(s+" : "+servletContext.getAttribute(s));
        }
        AtmosphereFramework atmosphereFramework = (AtmosphereFramework) servletContext.getAttribute("atmosphereServlet");
        System.out.println(atmosphereFramework.metaBroadcaster());
        System.out.println(atmosphereFramework.getBroadcasterFactory());
        Gson gson = new Gson();
        Message message=new Message();
        message.setAuthor("zhsandddss");
        message.setTime(21231313131l);
        message.setMessage("hello faaffffff");
        atmosphereFramework.metaBroadcaster().broadcastTo("/", gson.toJson(message), true);
        System.err.println("broa  dcast : " + atmosphereFramework.getBroadcasterFactory().lookupAll());
        return book;

    }
    @RequestMapping("/senEmail")
    public String senEmail(){
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            public void prepare(MimeMessage mimeMessage) throws Exception {

                mimeMessage.setRecipient(javax.mail.Message.RecipientType.TO,
                        new InternetAddress("843036893@qq.com"));
                mimeMessage.setFrom(new InternetAddress("javamail123@126.com"));
                mimeMessage.setText("下班了");
            }
        };

        try {
            this.javaMailSender.send(preparator);
            return "send success";
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println (ex.getMessage());
            return "send failure";
        }
    }

    @RequestMapping("/testBean")
    public Book testBean(@RequestBody Book book) {
        book.setAuthor("wang");
        return book;
    }
}
