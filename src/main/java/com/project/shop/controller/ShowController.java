package com.project.shop.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.project.shop.pojo.*;
import com.project.shop.service.UserService;
import com.project.shop.utils.ResultUtil;
import org.apache.ibatis.annotations.Select;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ShowController {

    @Autowired
    private UserService service;




    @RequestMapping("/index")
    public ModelAndView index(){
        System.out.println("**************************");
        ModelAndView modelAndView = new ModelAndView();
        List<Goodkind> l1 = new ArrayList<Goodkind>();

        l1 = service.selectallkind();
        System.out.println(l1);
//        modelAndView.setViewName("index");
        modelAndView.addObject("goodlist", l1);
        return modelAndView;
    }

    @RequestMapping(value = "/index/{kind}/{currentpage}")
    public ModelAndView detail(@PathVariable("kind") String kind,@PathVariable("currentpage") int currentpage){
        System.out.println(kind);
        System.out.println(currentpage);
        ModelAndView modelAndView = new ModelAndView();
        PageHelper.startPage(currentpage, 4);
        List<Goodinfo> l1 = service.selectAllGoods(kind);
        System.out.println(l1+"*******");

        PageInfo<Goodinfo> pageInfo = new PageInfo<>(l1);

        modelAndView.addObject("page", pageInfo);
        modelAndView.addObject("kind", kind);
        modelAndView.setViewName("detail");
        System.out.println(pageInfo);

        return  modelAndView;
    }

    @RequestMapping(value = "/show/{kind}/{id}")
    public ModelAndView show(@PathVariable String kind,@PathVariable int id){
        ModelAndView modelAndView = new ModelAndView();
           Goodinfo g1 = service.selectGoodById(id);
        System.out.println(g1);
        modelAndView.addObject("kind",kind);
        modelAndView.addObject("good",g1);
        modelAndView.setViewName("show");
        return  modelAndView;
    }

    @RequestMapping("/cart/addcart")
    @ResponseBody
    public ResultUtil addcart(@RequestParam("ifadd") int ifadd, @RequestParam("goodid") int goodid){

        Subject subject = SecurityUtils.getSubject();

        Session session = SecurityUtils.getSubject().getSession();

      User user = (User) session.getAttribute("currentUser");
        System.out.println("当前登录用户为"+user);
        int userid = user.getId();
        System.out.println("用户id为"+userid);
        System.out.println("查看有没有购物车");
        Cart cart = service.selectcount(userid,goodid);
        System.out.println(cart);
        if (cart ==null){

          int count =  service.insertcart(userid,goodid,ifadd);
            System.out.println("没有数据的时候是否插入成功呢 "+count);
        }else {
            int co = service.updatecart(userid,goodid,ifadd);
            System.out.println("更改购物车数据是否成功");
        }
        ResultUtil r1 = new ResultUtil(0, "success");
         return r1 ;

    }



    @RequestMapping("/cart/deletecart")
    @ResponseBody
    public ResultUtil deletecart(@RequestParam("goodid") int goodid){
        System.out.println("进入删除流程 删除的东西id为 "+goodid);
        Subject subject = SecurityUtils.getSubject();
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("currentUser");
        System.out.println("当前登录用户为"+user);
        int userid = user.getId();

        int count = service.deletecart(userid,goodid);

        ResultUtil r1 = new ResultUtil(0, "success");
        return r1 ;
    }


    @RequestMapping("/cart/deleteorder")
    @ResponseBody
    public ResultUtil deleteorder(@RequestParam("orderid") int orderid){
        System.out.println("进入删除流程 删除的订单id为 "+orderid);
        Subject subject = SecurityUtils.getSubject();


        int count = service.deleteorder(orderid);

        ResultUtil r1 = new ResultUtil(0, "success");
        return r1 ;
    }





    @RequestMapping("/cart")
    public ModelAndView cart(){

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {

            Session session = SecurityUtils.getSubject().getSession();
            User user = (User) session.getAttribute("currentUser");
            System.out.println("当前登录用户为"+user);
            int userid = user.getId();

            List<Mycart> l1 = service.selectmycart(userid);
            System.out.println(l1);

            int allmoney =0;
            for (Mycart c0:l1){
                allmoney  = allmoney+(c0.getGoodcount()*c0.getPrice());
            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("cart");
            modelAndView.addObject("mycart", l1);
            modelAndView.addObject("allmoney", allmoney);
            return modelAndView;
        } else {
            ModelAndView m1 = new ModelAndView();
            m1.setViewName("login");
            return m1;
    }}

    @RequestMapping("/cart/checkout")
    @ResponseBody
    public ResultUtil checkout(){
        System.out.println("Cart");
        System.out.println("展示购物车了");
        Subject subject = SecurityUtils.getSubject();
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("currentUser");
        System.out.println("当前登录用户为"+user);
        int userid = user.getId();
        System.out.println("开始结账");
        List<Mycart> l1 = service.selectmycart(userid);
        int money = 0;
        for (Mycart c2:l1){
            money = c2.getGoodcount()*c2.getPrice()+money;
        }
        System.out.println("总金额为"+money);

        int balance = service.selectmoney(userid);
        System.out.println("当前余额为"+balance);
        if (balance<money){
            ResultUtil r1 = new ResultUtil(0, "余额不足");
            return  ResultUtil.error("余额不足");
        }else {
            service.updatemoney(userid,money);
            int count = service.checkoutcart(userid);

            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");//可以方便地修改日期格式
            String hehe = dateFormat.format( now );
            System.out.println(hehe);

            for(Mycart c:l1){
                Order order = new Order();
                order.setUserid(userid);
                order.setGoodid(c.getGoodid());
                order.setCount(c.getGoodcount());
                order.setTime(hehe);
                System.out.println(order);

                int count0 =service.insertorder(order);
            }
            System.out.println("购物车变成order");
            return  ResultUtil.ok("结账成功");

        }


    }


    @RequestMapping("/order")
    public ModelAndView order(){

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {

            Session session = SecurityUtils.getSubject().getSession();
            User user = (User) session.getAttribute("currentUser");
            System.out.println("当前登录用户为"+user);
            int userid = user.getId();

            List<Myorder> l2 = service.selectorder(userid);
            System.out.println(l2);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("order");
            modelAndView.addObject("myorder", l2);
            return modelAndView;

        } else {
            ModelAndView m1 = new ModelAndView();
            m1.setViewName("login");
            return m1;
        }
    }


    @RequestMapping("/wallet")
    public ModelAndView wallet(){

        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {

            Session session = SecurityUtils.getSubject().getSession();
            User user = (User) session.getAttribute("currentUser");
            System.out.println("当前登录用户为"+user);
            int userid = user.getId();


            System.out.println("开始查询余额");
            int money= service.selectmoney(userid);
            System.out.println("余额为"+money);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("wallet");
            modelAndView.addObject("wallet", money);
            return modelAndView;

        } else {
            ModelAndView m1 = new ModelAndView();
            m1.setViewName("login");
            return m1;
        }
    }



    @RequestMapping("/test")
    public String detail(){
        System.out.println("detail");
        return "test";
    }



    @RequestMapping("/mine")
    public ModelAndView mine(){
        System.out.println("访问mine");
        Subject subject = SecurityUtils.getSubject();

        if (subject.isAuthenticated()) {
            Session session = SecurityUtils.getSubject().getSession();
            User user = (User) session.getAttribute("currentUser");
            System.out.println("当前登录用户为"+user);
            int userid = user.getId();

            Userinfo userinfo = service.seluesrinfo(userid);
            System.out.println(userinfo);

            ModelAndView m1 = new ModelAndView();
            m1.addObject("userinfo", userinfo);
            m1.setViewName("mine");
            System.out.println(userinfo);
            return m1;

        } else {
            ModelAndView m1 = new ModelAndView();
            m1.setViewName("login");
            return m1;
        }





    }

    @RequestMapping("/mine/changeuser")
    @ResponseBody
    public String changeuser(@RequestBody Userinfo userinfo){
        System.out.println("开始改数据");
        Session session = SecurityUtils.getSubject().getSession();
        User user = (User) session.getAttribute("currentUser");
        System.out.println("当前登录用户为"+user);
        int userid = user.getId();
        userinfo.setUserid(userid);
       int change =  service.changeuser(userinfo);
        System.out.println(userinfo);
        System.out.println(change);
        return "成功了";
    }


    @RequestMapping("/login")
    public String login(){
        System.out.println("login");
        return "login";
    }
    @RequestMapping("/register")
    public String register(){
        System.out.println("register");
        return "register";
    }
}
