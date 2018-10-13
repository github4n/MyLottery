package com.csy.mapper;

import com.csy.domain.Ahk3Propery;

public interface Ahk3ProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Ahk3Propery record);
    int saveOrUpdate(Ahk3Propery record);

    int insertSelective(Ahk3Propery record);

    Ahk3Propery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Ahk3Propery record);

    int updateByPrimaryKey(Ahk3Propery record);
}