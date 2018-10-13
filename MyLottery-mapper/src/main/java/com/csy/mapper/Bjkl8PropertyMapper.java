package com.csy.mapper;

import com.csy.domain.Bjkl8Property;

public interface Bjkl8PropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Bjkl8Property record);
    int saveOrUpdate(Bjkl8Property record);

    int insertSelective(Bjkl8Property record);

    Bjkl8Property selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Bjkl8Property record);

    int updateByPrimaryKey(Bjkl8Property record);
}