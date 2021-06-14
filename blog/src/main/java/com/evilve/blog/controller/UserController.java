package com.evilve.blog.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.evilve.blog.pojo.User;
import com.evilve.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Evilve
 * @since 2020-12-11
 */
@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public String toLogin(){
        return "admin/login";
    }

    @RequestMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes redirectAttributes){
        User user=userService.getUser(username,DigestUtils.md5DigestAsHex(password.getBytes()));
        if (user!=null){
            user.setPassword(null);
            session.setAttribute("user",user);
            //spring自带MD5工具api DigestUtils.md5DigestAsHex
            System.out.println(DigestUtils.md5DigestAsHex(password.getBytes()));
            return "admin/index";
        }else{
            redirectAttributes.addFlashAttribute("message","用户名或密码错误");
            return "redirect:/admin";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";//注销后返回登录
    }


}

