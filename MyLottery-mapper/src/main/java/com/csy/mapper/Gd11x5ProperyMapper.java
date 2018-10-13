package com.csy.mapper;

import com.csy.domain.Gd11x5Propery;

public interface Gd11x5ProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Gd11x5Propery record);
    int saveOrUpdate(Gd11x5Propery record);

    int insertSelective(Gd11x5Propery record);

    Gd11x5Propery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Gd11x5Propery record);

    int updateByPrimaryKey(Gd11x5Propery record);
}