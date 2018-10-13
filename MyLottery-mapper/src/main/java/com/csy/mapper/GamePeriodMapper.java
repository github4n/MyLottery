package com.csy.mapper;

import java.util.Date;
import java.util.List;

import com.csy.domain.GamePeriod;
import com.csy.dto.GamePeriodDTO;
import org.apache.ibatis.annotations.Param;

//彩种开奖信息
public interface GamePeriodMapper {
	/**
	 * 获取彩种下一次开奖时间
	 * @param iGameId  彩种id
	 */
	GamePeriod nextShowData(Long iGameId);
	/**
	 * 根据彩种id获取对应彩种最新一期开奖信息
	 * @param id  彩种id
	 * @return
	 */
    GamePeriod getNewDate(@Param("id") Long id, @Param("iOpen")Long iOpen);

    /**
     * 根据彩种id和需要的条数获取对应彩种近期开奖的数据
     * @param iGameId  彩种id
     * @return
     */
	List<GamePeriod> getByIGameIdAndCount(GamePeriodDTO dto);
	
	
	/**
	 * 通过指定彩种id和开奖日期查询某一天的开奖数据
	 * @param dOpenTime
	 * @return
	 */
	List<GamePeriod> getByIGameIdAndOpenTime(GamePeriodDTO dto);
	
	/**
	 * 
	 * @param gamePeriod
	 */
	void save(GamePeriod gamePeriod);
	
	/**
	 * 如果采集有重复数据，则更新！
	 * @param gamePeriod
	 */
	void saveOrUpdate(GamePeriod gamePeriod);

	Integer selectOverPeriodCount(@Param("iGameId") Long iGameId, @Param("date") Date date);

	Integer selectTodayPeriodCount(@Param("igameId") Long igameId, @Param("nowDate") Date nowDate);

	GamePeriod selectMaxPeriod(Long igameId);

	GamePeriod selectMaxPeriodData(Long igameId);

	List<GamePeriod> selectAllPeriodNotOpen();

	GamePeriod selectMinPeriodData(Long iGameId);

	GamePeriod getFirstPeriodEveryDay(@Param("igameId") Long igameId, @Param("date") Date date);
}