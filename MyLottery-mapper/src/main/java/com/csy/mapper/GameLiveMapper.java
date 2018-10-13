package com.csy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.csy.domain.GameLive;

@Mapper
public interface GameLiveMapper {

	List<GameLive> getAll(); 
}