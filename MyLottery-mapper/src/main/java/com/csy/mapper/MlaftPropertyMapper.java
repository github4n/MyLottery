package com.csy.mapper;

import com.csy.domain.MlaftProperty;

public interface MlaftPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(MlaftProperty record);
    int saveOrUpdate(MlaftProperty record);

    int insertSelective(MlaftProperty record);

    MlaftProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(MlaftProperty record);

    int updateByPrimaryKey(MlaftProperty record);
}