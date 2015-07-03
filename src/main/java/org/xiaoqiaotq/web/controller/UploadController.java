package org.xiaoqiaotq.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * author: xiaoqiaotq@gmail.com
 * date  : 2015/7/2.
 */
@RequestMapping("/upload")
@Controller
public class UploadController {
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public String save(MultipartFile file,HttpServletRequest req) throws Exception{
        if(!file.isEmpty()){
            String uploadDir=req.getServletContext().getRealPath("/upload");

            SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/dd/");
            String subPath = sdf.format(new Date());
            File dir = new File(uploadDir+subPath);
            if(!dir.exists()){
                dir.mkdirs();
            }
            System.err.println(dir + "/" + file.getOriginalFilename());
            file.transferTo(new File(dir, file.getOriginalFilename()));
        }
        return "upload success";
    }
    @RequestMapping("/form")
    public String form() throws Exception{
        return "upload_form";
    }
}
