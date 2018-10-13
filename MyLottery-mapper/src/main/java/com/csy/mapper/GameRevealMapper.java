package com.csy.mapper;

import java.util.List;

import com.csy.domain.GameReveal;

public interface GameRevealMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(GameReveal record);

    int insertSelective(GameReveal record);

    GameReveal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(GameReveal record);

    int updateByPrimaryKeyWithBLOBs(GameReveal record);

    int updateByPrimaryKey(GameReveal record);

	List<GameReveal> selectByIgameId(Long iGameId); 
}