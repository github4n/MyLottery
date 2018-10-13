package com.csy.mapper;

import com.csy.domain.Jx11v5Propery;

public interface Jx11v5ProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Jx11v5Propery record);
    int saveOrUpdate(Jx11v5Propery record);

    int insertSelective(Jx11v5Propery record);

    Jx11v5Propery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Jx11v5Propery record);

    int updateByPrimaryKey(Jx11v5Propery record);
}