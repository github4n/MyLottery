package com.csy.mapper;

import com.csy.domain.TjklsfPropery;

public interface TjklsfProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(TjklsfPropery record);
    int saveOrUpdate(TjklsfPropery record);

    int insertSelective(TjklsfPropery record);

    TjklsfPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(TjklsfPropery record);

    int updateByPrimaryKey(TjklsfPropery record);
}