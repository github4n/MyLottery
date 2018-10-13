package com.csy.mapper;

import com.csy.domain.JsscProperty;

public interface JsscPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(JsscProperty record);
    int saveOrUpdate(JsscProperty record);

    int insertSelective(JsscProperty record);

    JsscProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(JsscProperty record);

    int updateByPrimaryKey(JsscProperty record);
}