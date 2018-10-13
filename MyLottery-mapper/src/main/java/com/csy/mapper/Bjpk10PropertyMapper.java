package com.csy.mapper;

import com.csy.domain.Bjpk10Property;

public interface Bjpk10PropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Bjpk10Property record);
    int saveOrUpdate(Bjpk10Property record);
    
    int insertSelective(Bjpk10Property record);

    Bjpk10Property selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Bjpk10Property record);

    int updateByPrimaryKey(Bjpk10Property record);
}