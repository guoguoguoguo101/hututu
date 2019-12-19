package com.project.shop.controller;


import com.project.shop.dao.UserMapper;
import com.project.shop.pojo.User;
import com.project.shop.service.UserService;
import com.project.shop.service.impl.UserServiceImpl;
import com.project.shop.utils.Md5Encoder;
import com.project.shop.utils.RegexUtil;
import com.project.shop.utils.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.context.SpringContextUtils;

@Controller
public class UserController {


    @Autowired
    private UserService service;


    @RequestMapping("/login0")
    @ResponseBody
   public ResultUtil login0(User user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        User user1 = service.selectUserByUserName(user.getUsername());
        System.out.println(user1);
////
////        return null;
        System.out.println("走到这一步");
        Subject subject = SecurityUtils.getSubject();
        System.out.println("能获得subject么");
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
            token.setRememberMe(false);
            try {
                System.out.println("开始try");
                subject.login(token);
                System.out.println("登录成功");
                Session session = SecurityUtils.getSubject().getSession();
                System.out.println("获取session");

                User user0 = service.selectUserByUserName(user.getUsername());
                System.out.println(user0);

                session.setAttribute("currentUser", user0);
                System.out.println("把当前的用户放到session中");
                System.out.println("往session中填入东西 返回成功");
                return ResultUtil.ok("登陆成功");
            } catch (UnknownAccountException uae) {
                return ResultUtil.error("未知的用户类型");
            } catch (IncorrectCredentialsException ice) {
                return ResultUtil.error("账号或者密码错误");
            } catch (LockedAccountException lae) {
                return ResultUtil.error("账号已被锁定");
            } catch (AuthenticationException ae) {
                return ResultUtil.error("账号或者密码错误");
            } catch (Exception e) {
                return ResultUtil.error("人品问题，登录失败");
            }
        } else {
            return ResultUtil.error("干哈?!你都已经登陆成功了");
        }
//    }
    }

    @RequestMapping("/register0")
    @ResponseBody
    public ResultUtil register0(@RequestParam(value = "userName") String username, @RequestParam(value = "password1") String password1, @RequestParam(value = "password2") String password2) {
        if (username == null || password1 == null || password2 == null) {
            return ResultUtil.error("不能为空");
        }
        if (!password1.matches(RegexUtil.REGEX_PASSWORD)) {
            return ResultUtil.error("密码格式有误请重新输入");
        }

        if (!password1.equals(password2)) {
            return ResultUtil.error("；两次密码不一致");
        }
        //通过 username在数据库中找 user 判断是否重名

        User user = service.selectUserByUserName(username);
        if (user != null) {
            return ResultUtil.error("；用户名已经存在了哦亲亲 请换一个");
        }
        // 进行MD5加密存入数据库 加密次数2次 盐值用户名
        String newpassword = Md5Encoder.md5Encode(username, password1);
        //将user1 插入至数据库(两张表)
        User user1 = new User();
        user1.setUsername(username);
        user1.setPassword(newpassword);
        int i1 = service.insertUser(user1);
        int i2 = service.insertuserinfo(username);
        return ResultUtil.ok("注册成功");
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResultUtil logout() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
            return ResultUtil.ok("成功登出");
        } catch (Exception e) {
            return ResultUtil.error("人品太差了吧，注销都能失败");
        }
    }
}




