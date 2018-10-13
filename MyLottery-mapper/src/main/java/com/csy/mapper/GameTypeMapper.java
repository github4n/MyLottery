package com.csy.mapper;

import com.csy.domain.GameType;

public interface GameTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GameType record);

    int insertSelective(GameType record);

    GameType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GameType record);

    int updateByPrimaryKey(GameType record);

    Integer selectPeriodCount(Long iGameId);


}