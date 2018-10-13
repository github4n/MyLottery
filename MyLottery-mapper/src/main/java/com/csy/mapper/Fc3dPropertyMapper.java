package com.csy.mapper;

import com.csy.domain.Fc3dProperty;

public interface Fc3dPropertyMapper {

	int insert(Fc3dProperty record);
    int saveOrUpdate(Fc3dProperty record);


    Fc3dProperty selectByPrimaryKey(String sKey);
}