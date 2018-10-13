package com.csy.mapper;

import com.csy.domain.JsftProperty;

public interface JsftPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(JsftProperty record);
    int saveOrUpdate(JsftProperty record);

    int insertSelective(JsftProperty record);

    JsftProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(JsftProperty record);

    int updateByPrimaryKey(JsftProperty record);
}