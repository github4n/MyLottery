package com.csy.mapper;

import com.csy.domain.Jsk3Propery;

public interface Jsk3ProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Jsk3Propery record);
    int saveOrUpdate(Jsk3Propery record);

    int insertSelective(Jsk3Propery record);

    Jsk3Propery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Jsk3Propery record);

    int updateByPrimaryKey(Jsk3Propery record);
}