package com.project.shop.dao;

import com.project.shop.pojo.Goodinfo;

public interface GoodinfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Goodinfo record);

    int insertSelective(Goodinfo record);

    Goodinfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Goodinfo record);

    int updateByPrimaryKey(Goodinfo record);
}