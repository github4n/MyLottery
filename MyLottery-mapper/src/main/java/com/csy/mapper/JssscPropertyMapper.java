package com.csy.mapper;

import com.csy.domain.JssscProperty;

public interface JssscPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(JssscProperty record);
    int saveOrUpdate(JssscProperty record);

    int insertSelective(JssscProperty record);

    JssscProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(JssscProperty record);

    int updateByPrimaryKey(JssscProperty record);
}