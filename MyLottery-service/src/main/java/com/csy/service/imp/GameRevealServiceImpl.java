package com.csy.service.imp;

import com.csy.domain.GameReveal;
import com.csy.mapper.GameRevealMapper;
import com.csy.service.IGameRevealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//import org.springframework.beans.factory.annotation.Autowired;

//玩彩技巧
@Service
public class GameRevealServiceImpl implements IGameRevealService{
	@Autowired
	private GameRevealMapper gameRevealMapper;
	
	
	public List<GameReveal> getGameReveal(Long iGameId) {
		if (iGameId == null) {
			List<GameReveal> fc3ds = gameRevealMapper.selectByIgameId(1L);
			List<GameReveal> pl3s = gameRevealMapper.selectByIgameId(2L);
			for (GameReveal pl3 : pl3s) {
				fc3ds.add(pl3);
			}
			return fc3ds;
		}else {
			return gameRevealMapper.selectByIgameId(iGameId);
		}
	}
}
