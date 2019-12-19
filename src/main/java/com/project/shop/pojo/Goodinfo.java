package com.project.shop.pojo;

public class Goodinfo {
    private Integer id;

    private Integer kindid;

    private String name;
    private String detail;

    @Override
    public String toString() {
        return "Goodinfo{" +
                "id=" + id +
                ", kindid=" + kindid +
                ", name='" + name + '\'' +
                ", detail='" + detail + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", flag=" + flag +
                '}';
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String img;

    private Integer price;

    private Integer count;

    private Integer flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKindid() {
        return kindid;
    }

    public void setKindid(Integer kindid) {
        this.kindid = kindid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

}