package com.project.shop.pojo;

public class Goodkind {
    private Integer id;

    private String goodkind;

    private String img;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodkind() {
        return goodkind;
    }

    public void setGoodkind(String goodkind) {
        this.goodkind = goodkind;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }



    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Goodkind{" +
                "id=" + id +
                ", goodkind='" + goodkind + '\'' +
                ", img='" + img + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}