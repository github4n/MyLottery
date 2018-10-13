package com.csy.mapper;

import com.csy.domain.Azxy10Property;
import com.csy.domain.Bjkl8Property;

/**
 * @program: MyLottery
 * @description:
 * @author: Mr.chen
 * @create: 2018-09-18 10:58
 **/
public interface Azxy10PropertyMapper {

    int deleteByPrimaryKey(String sKey);

    int insert(Azxy10Property record);
    int saveOrUpdate(Azxy10Property record);

    int insertSelective(Azxy10Property record);

    Bjkl8Property selectByPrimaryKey(String sKey);

    int updateByPrimaryKeySelective(Azxy10Property record);

    int updateByPrimaryKey(Azxy10Property record);
}
