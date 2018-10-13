package com.csy.mapper;

import com.csy.domain.Pl3Property;

public interface Pl3PropertyMapper {
    int deleteByPrimaryKey(String sKey);

    int insert(Pl3Property record);
    int saveOrUpdate(Pl3Property record);

    int insertSelective(Pl3Property record);

    Pl3Property selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Pl3Property record);

    int updateByPrimaryKey(Pl3Property record);
}