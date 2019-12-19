package com.project.shop.dao;

import com.project.shop.pojo.Goodkind;

public interface GoodkindMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goodkind record);

    int insertSelective(Goodkind record);

    Goodkind selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goodkind record);

    int updateByPrimaryKey(Goodkind record);
}