package com.csy.mapper;

import com.csy.domain.SyytjPropery;

public interface SyytjProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(SyytjPropery record);
    int saveOrUpdate(SyytjPropery record);

    int insertSelective(SyytjPropery record);

    SyytjPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(SyytjPropery record);

    int updateByPrimaryKey(SyytjPropery record);
}