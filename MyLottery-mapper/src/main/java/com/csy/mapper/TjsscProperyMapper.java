package com.csy.mapper;

import com.csy.domain.TjsscPropery;

public interface TjsscProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(TjsscPropery record);
    int saveOrUpdate(TjsscPropery record);

    int insertSelective(TjsscPropery record);

    TjsscPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(TjsscPropery record);

    int updateByPrimaryKey(TjsscPropery record);
}