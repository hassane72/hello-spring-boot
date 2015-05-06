package org.xiaoqiaotq.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xiaoqiaotq.domain.User;
import org.xiaoqiaotq.service.UserService;
import org.xiaoqiaotq.util.Servlets;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/sys/user")
public class UserController {
    //每页显示记录数
    private static final String PAGE_SIZE = "15";
    //分页插件显示数
    private static final int paginationSize = 4;

    @Autowired
    private UserService userService;


    @RequestMapping("/home")
    public String home(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
                       @RequestParam(value = "page.size", defaultValue = PAGE_SIZE) int pageSize,
                       @RequestParam(value = "sortType", defaultValue = "auto") String sortType, Model model,
                       ServletRequest request){
        Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
        Page<User> page=userService.queryUser( searchParams, pageNumber, pageSize, sortType);
        model.addAttribute("page", page);
        model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
        //pagination
        int current =  page.getNumber() + 1;
        int begin = Math.max(1, current - paginationSize/2);
        int end = Math.min(begin + (paginationSize - 1), page.getTotalPages());

        request.setAttribute("current", current);
        request.setAttribute("begin", begin);
        request.setAttribute("end", end);

        return "user/user_list";
    }
    @RequestMapping(value="/createOrUpdate",method=RequestMethod.POST)
    public String createOrUpdate(@Valid@ModelAttribute("user") User user,BindingResult result,Model model,RedirectAttributes redirectAttributes){
        if(result.hasErrors()){
            return "/user/user_form";
        }
        String message="";
        if(user.isNew()){
            user.setRegisterDate(new Date());
            message="创建成功";
        }else{
            message="修改成功";
        }
        redirectAttributes.addFlashAttribute("message", message);
        userService.save(user);
        return "redirect:/sys/user/home";
    }
    @RequestMapping(value="/create",method=RequestMethod.GET)
    public String createForm(User user){
        return "user/user_form";
    }
    @RequestMapping(value="/update/{id}",method=RequestMethod.GET)
    public String updateForm(@PathVariable long id,Model model){
//        User user=userService.find(id);
//        model.addAttribute("user", user);
        return "user/user_form";
    }
    @RequestMapping(value="/del/{id}",method=RequestMethod.GET)
    public String del(@PathVariable long id,RedirectAttributes redirectAttributes){
//        userService.remove(id);
        redirectAttributes.addFlashAttribute("message", "删除成功");
        return "redirect:/sys/user/home";
    }
    @RequestMapping(value="/exists")
    @ResponseBody
    public String exists(@RequestParam String username){
        return String.valueOf(!userService.isExist(username));
    }
    @ModelAttribute
    public void getUser(@RequestParam(value = "id", defaultValue = "-1") Long id, Model model) {
        if (id != -1) {
            model.addAttribute("user", userService.find(id));
        }
    }
}

