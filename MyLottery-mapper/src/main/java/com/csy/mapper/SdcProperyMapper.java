package com.csy.mapper;

import com.csy.domain.SdcPropery;

public interface SdcProperyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(SdcPropery record);
    int saveOrUpdate(SdcPropery record);

    int insertSelective(SdcPropery record);

    SdcPropery selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(SdcPropery record);

    int updateByPrimaryKey(SdcPropery record);
}