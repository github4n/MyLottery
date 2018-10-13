package com.csy.mapper;

import com.csy.domain.HnklsfPropery;

public interface HnklsfProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(HnklsfPropery record);
    int saveOrUpdate(HnklsfPropery record);

    int insertSelective(HnklsfPropery record);

    HnklsfPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(HnklsfPropery record);

    int updateByPrimaryKey(HnklsfPropery record);
}