package com.project.shop.service;

import com.project.shop.pojo.*;

import java.util.List;

public interface UserService {
    User selectUserByUserName(String username);

    int insertUser(User user1);

    //商品展示种类的sql
    List<Goodkind> selectallkind();

    //查询某一类所有商品呗
    List<Goodinfo> selectAllGoods(String kind);

    //按id查找这个东西
    Goodinfo selectGoodById(int id);

    //找有没有这个东西 cart里
    Cart selectcount(int userid, int goodid);

    //购物车插入第一个数据
    int insertcart(int userid, int goodid, int ifadd);

    //更改购物车数据了
    int updatecart(int userid, int goodid, int ifadd);

    //查看我的购物车
    List<Mycart> selectmycart(int userid);

    //删除当前用户的 这个商品
    int deletecart(int userid, int goodid);

    //查看当前的个人信息
    Userinfo seluesrinfo(int userid);

    //改变个人信息
   int changeuser(Userinfo userinfo);

   //清空购物车
    int checkoutcart(int userid);

    //购物车变成订单
    int insertorder(Order order);

    //查找订单
    List<Myorder> selectorder(int userid);

    //删除某个订单
    int deleteorder(int orderid);

    //注册成功后将user里面的id添加到userinfo中
    int insertuserinfo(String username);

    //查询余额
    int selectmoney(int userid);

    //改钱
    void updatemoney(int userid,int money);
}
