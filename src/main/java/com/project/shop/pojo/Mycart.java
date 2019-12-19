package com.project.shop.pojo;

public class Mycart {
    private int id;
    private int userid;
    private int goodid;
    private int goodcount;
    private String name;
    private int kindid;
    private String img;
    private int price;
    private String goodkind;
    private  int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getGoodid() {
        return goodid;
    }

    public void setGoodid(int goodid) {
        this.goodid = goodid;
    }

    public int getGoodcount() {
        return goodcount;
    }

    public void setGoodcount(int goodcount) {
        this.goodcount = goodcount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKindid() {
        return kindid;
    }

    public void setKindid(int kindid) {
        this.kindid = kindid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGoodkind() {
        return goodkind;
    }

    public void setGoodkind(String goodkind) {
        this.goodkind = goodkind;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Mycart{" +
                "id=" + id +
                ", userid=" + userid +
                ", goodid=" + goodid +
                ", goodcount=" + goodcount +
                ", name='" + name + '\'' +
                ", kindid=" + kindid +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", goodkind='" + goodkind + '\'' +
                ", count=" + count +
                '}';
    }
}
