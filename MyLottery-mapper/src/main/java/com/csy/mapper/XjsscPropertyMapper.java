package com.csy.mapper;

import com.csy.domain.XjsscProperty;

public interface XjsscPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(XjsscProperty record);
    int saveOrUpdate(XjsscProperty record);

    int insertSelective(XjsscProperty record);

    XjsscProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(XjsscProperty record);

    int updateByPrimaryKey(XjsscProperty record);
}