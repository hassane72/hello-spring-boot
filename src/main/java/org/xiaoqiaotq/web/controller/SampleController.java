package org.xiaoqiaotq.web.controller;

import com.google.gson.Gson;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.BroadcasterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;
import org.xiaoqiaotq.Application;
import org.xiaoqiaotq.domain.AutoDelLogTimeEntity;
import org.xiaoqiaotq.domain.Book;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.service.AsyncService;
import org.xiaoqiaotq.web.messageService.Message;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Enumeration;
import java.util.Map;

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

    @Autowired
    AsyncService asyncService;

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
        System.err.println("broa  dcast : " + atmosphereFramework.getBroadcasterFactory().lookupAll());
        Message message=new Message();
        message.setAuthor("zhsandddss");
        message.setMessage("hello faaffffff");
//        atmosphereFramework.metaBroadcaster().broadcastTo("/", gson.toJson(message), true);
        atmosphereFramework.getBroadcasterFactory().lookup("zhsan", true).broadcast(gson.toJson(message));
        System.err.println("sdfsdaf");
        System.err.println("sadfsdffdss");
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
    @RequestMapping("/writeFile")
    public String writeFile(String input) {
        System.err.println(this);
        File file=new File("./aa/bb");
        file.mkdirs();
        return "success";
    }

    @RequestMapping("/getApplicationContext")
         public String getApplicationContext() {
        ApplicationContext context = Application.context;
        Object userService = context.getBean("userService");
        System.err.println(context);
        System.err.println(userService);
        return "success";
    }
    @RequestMapping("/testAsync")
    @ResponseBody
    public String testAsync() {
        System.err.println("main thread  start "+Thread.currentThread().getName());
        asyncService.aa();
        System.err.println("main thread  end "+Thread.currentThread().getName());
        return "success";
    }
    @RequestMapping("/testSchedule")
    @ResponseBody
    public String testSchedule() {
        asyncService.bb();
        return "success";
    }
    @RequestMapping("/saveAutoDelLogTime")
    @ResponseBody
    public AutoDelLogTimeEntity saveAutoDelLogTime(AutoDelLogTimeEntity autoDelLogTime) {
        asyncService.saveAutoDelLogTime(autoDelLogTime);
        return autoDelLogTime;
    }
    @RequestMapping("/wordFilterTest")
    @ResponseBody
    public String wordFilter(String words,HttpServletRequest request,@RequestParam Map map) {
        String aa = request.getParameter("aa");
        String[] bb = request.getParameterValues("bb");
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.err.println(aa);
        System.err.println(words);
        return words;
    }

}
