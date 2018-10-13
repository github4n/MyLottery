package com.csy.mapper;

import com.csy.domain.CqsscProperty;

public interface CqsscPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(CqsscProperty record);
    int saveOrUpdate(CqsscProperty record);

    int insertSelective(CqsscProperty record);

    CqsscProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(CqsscProperty record);

    int updateByPrimaryKey(CqsscProperty record);
    
}