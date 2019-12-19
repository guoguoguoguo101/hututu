package com.project.shop.pojo;

public class Userinfo {
    private Integer id;

    private Integer userid;

    private String name;

    private String sex;

    private Integer old;

    private String detail;

    private String phone;

    private String location;

    private  int money;

    @Override
    public String toString() {
        return "Userinfo{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", old=" + old +
                ", detail='" + detail + '\'' +
                ", phone='" + phone + '\'' +
                ", location='" + location + '\'' +
                ", money=" + money +
                '}';
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getOld() {
        return old;
    }

    public void setOld(Integer old) {
        this.old = old;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}