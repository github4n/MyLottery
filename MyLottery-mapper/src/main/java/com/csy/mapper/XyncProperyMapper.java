package com.csy.mapper;

import com.csy.domain.XyncPropery;

public interface XyncProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(XyncPropery record);
    int saveOrUpdate(XyncPropery record);

    int insertSelective(XyncPropery record);

    XyncPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(XyncPropery record);

    int updateByPrimaryKey(XyncPropery record);
}