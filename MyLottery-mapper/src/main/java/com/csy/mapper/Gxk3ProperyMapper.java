package com.csy.mapper;

import com.csy.domain.Gxk3Propery;

public interface Gxk3ProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Gxk3Propery record);
    int saveOrUpdate(Gxk3Propery record);

    int insertSelective(Gxk3Propery record);

    Gxk3Propery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Gxk3Propery record);

    int updateByPrimaryKey(Gxk3Propery record);
}