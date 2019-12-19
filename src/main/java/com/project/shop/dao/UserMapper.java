package com.project.shop.dao;

import com.project.shop.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    //根据用户名字查询用户user
    @Select("select * from user where username =#{0} ")
    User selectUserByUserName(String username);

    @Select("select * from goodkind")
    List<Goodkind> selectallkind();

    @Select("select * from  goodinfo where kindid = (select id from goodkind where goodkind = #{0})")
    List<Goodinfo> selectAllGoods(String kind);

    @Select("select * from goodinfo where id = #{0}")
    Goodinfo selectGoodByid(int id);

    @Select("select * from cart where userid = #{userid} and goodid =#{goodid}")
    Cart selectcount(@Param("userid") int userid,@Param("goodid") int goodid);

    @Insert("insert into cart values(default,#{userid},#{goodid},1)")
    int insertcart(@Param("userid") int userid,@Param("goodid") int goodid);

    @Update("update cart set goodcount = goodcount + #{ifadd} where userid=#{userid} and goodid = #{goodid} ")
    int updatecart(int userid, int goodid, int ifadd);

    @Select("select * from mycart where userid = #{0}")
    List<Mycart> selectmycart(int userid);

    @Delete("delete  from cart where userid=#{userid} and goodid=#{goodid}")
    int deletecart(@Param("userid") int userid, @Param("goodid") int goodid);

    @Select("select * from userinfo where userid = #{0}")
    Userinfo seluserinfo(int userid);

    @Update("update userinfo set name = #{userinfo.name},sex = #{userinfo.sex},old=#{userinfo.old},detail=#{userinfo.detail},phone=#{userinfo.phone},location=#{userinfo.location} where userid = #{userinfo.userid}")
   int  changeuser(@Param("userinfo") Userinfo userinfo);

    @Delete("delete from cart where userid =#{0} ")
    int checkoutcart(int userid);

    @Insert("insert into `order` values(default,#{order.userid},#{order.goodid},#{order.count},#{order.time},0)")
    int insertorder(@Param("order") Order order);


    @Select("select * from myorder where userid = #{0}")
    List<Myorder> selectorder(int userid);

    @Delete("delete from `order` where id = #{0} ")
    int deleteorder(int orderid);

    @Insert("insert into userinfo(userid,money)values((select id from user where username=#{0}),500) ")
    int insertuserinfo(String username);

    @Select("select money from userinfo where userid = #{0}")
    int selectmoney(int userid);

    @Update("update userinfo set money = money - #{money} where userid = #{userid}")
    void updatemoney(@Param("userid") int userid,@Param("money") int money);
}