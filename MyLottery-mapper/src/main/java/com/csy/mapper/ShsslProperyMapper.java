package com.csy.mapper;

import com.csy.domain.ShsslPropery;

public interface ShsslProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(ShsslPropery record);
    int saveOrUpdate(ShsslPropery record);

    int insertSelective(ShsslPropery record);

    ShsslPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(ShsslPropery record);

    int updateByPrimaryKey(ShsslPropery record);
}