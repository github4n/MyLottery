package com.csy.mapper;

import com.csy.domain.GdklsfProperty;

public interface GdklsfPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(GdklsfProperty record);
    int saveOrUpdate(GdklsfProperty record);

    int insertSelective(GdklsfProperty record);

    GdklsfProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(GdklsfProperty record);

    int updateByPrimaryKey(GdklsfProperty record);
}