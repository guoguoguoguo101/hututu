package com.project.shop.service.impl;

import com.project.shop.dao.UserMapper;
import com.project.shop.pojo.*;
import com.project.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserByUserName(String username) {

        return    userMapper.selectUserByUserName(username);
    }

    @Override
    public int insertUser(User user1) {
        return userMapper.insert(user1);
    }

    @Override
    public List<Goodkind> selectallkind() {
        return userMapper.selectallkind();
    }

    @Override
    public List<Goodinfo> selectAllGoods(String kind) {
        return userMapper.selectAllGoods(kind);
    }

    @Override
    public Goodinfo selectGoodById(int id) {
        return userMapper.selectGoodByid(id);
    }

    @Override
    public Cart selectcount(int userid, int goodid) {
        return userMapper.selectcount(userid,goodid);
    }

    @Override
    public int insertcart(int userid, int goodid, int ifadd) {
        return userMapper.insertcart(userid,goodid);
    }

    @Override
    public int updatecart(int userid, int goodid, int ifadd) {
        return userMapper.updatecart(userid,goodid,ifadd);
    }

    @Override
    public List<Mycart> selectmycart(int userid) {
        return userMapper.selectmycart(userid);
    }

    @Override
    public int deletecart(int userid, int goodid) {
        return userMapper.deletecart(userid,goodid);
    }

    @Override
    public Userinfo seluesrinfo(int userid) {

        return userMapper.seluserinfo(userid);
    }

    @Override
    public int changeuser(Userinfo userinfo) {
        return userMapper.changeuser(userinfo);
    }

    @Override
    public int checkoutcart(int userid) {
        return userMapper.checkoutcart(userid);
    }

    @Override
    public int insertorder(Order order) {
      return userMapper.insertorder(order);
    }

    @Override
    public List<Myorder> selectorder(int userid) {
        return userMapper.selectorder(userid);
    }

    @Override
    public int deleteorder(int orderid) {
        return userMapper.deleteorder(orderid);
    }

    @Override
    public int insertuserinfo(String username) {
        return userMapper.insertuserinfo(username);
    }

    @Override
    public int selectmoney(int userid) {
        return userMapper.selectmoney(userid);
    }

    @Override
    public void updatemoney(int userid,int money) {
         userMapper.updatemoney(userid,money);
    }
}
