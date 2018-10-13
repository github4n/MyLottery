package com.csy.mapper;

import com.csy.domain.PcddProperty;

public interface PcddPropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(PcddProperty record);
    int saveOrUpdate(PcddProperty record);

    int insertSelective(PcddProperty record);

    PcddProperty selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(PcddProperty record);

    int updateByPrimaryKey(PcddProperty record);
}