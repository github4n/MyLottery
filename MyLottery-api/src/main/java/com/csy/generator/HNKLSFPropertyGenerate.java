package com.csy.generator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import com.csy.domain.GamePeriod;
import com.csy.domain.HnklsfPropery;

@Component("HNKLSFPropertyGenerate")
public class HNKLSFPropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 29L;
	
	@Override
	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算cqssc对应的属性值
	 */
	public HnklsfPropery createProperty(GamePeriod gamePeriod) {
		HnklsfPropery hnklsfPropery = new HnklsfPropery();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		int sNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3() + gamePeriod.getIopennum4() + gamePeriod.getIopennum5()+ gamePeriod.getIopennum6()+ gamePeriod.getIopennum7()+ gamePeriod.getIopennum8();
		//sKey
		hnklsfPropery.setsKey(sKey);
		//总和
		hnklsfPropery.setiSum(sNum);
		//总和单双
		if (sNum % 2 == 1) {
			hnklsfPropery.setsSumSingleDouble("单");
		} else {
			hnklsfPropery.setsSumSingleDouble("双");
		}
		//总和大小
		if (sNum == 84) {
			hnklsfPropery.setsSumBigSmall("和");
		} else if (sNum>=36 && sNum <= 83) {
			hnklsfPropery.setsSumBigSmall("小");
		}else if(sNum>=85 && sNum <= 132){
			hnklsfPropery.setsSumBigSmall("大");			
		}
		//尾大 尾小
		if (sNum % 10 <= 4) {
			hnklsfPropery.setsSumTailSize("尾小");
		} else if (sNum % 10 > 4) {
			hnklsfPropery.setsSumTailSize("尾大");
		}
		

		if (gamePeriod.getIopennum1() > gamePeriod.getIopennum8()) {
			hnklsfPropery.setsDragonTigerNum1("龙");
		} else {
			hnklsfPropery.setsDragonTigerNum1("虎");
		}
		
		if (gamePeriod.getIopennum2() > gamePeriod.getIopennum7()) {
			hnklsfPropery.setsDragonTigerNum2("龙");
		} else {
			hnklsfPropery.setsDragonTigerNum2("虎");
		}
		
		if (gamePeriod.getIopennum3() > gamePeriod.getIopennum6()) {
			hnklsfPropery.setsDragonTigerNum3("龙");
		} else {
			hnklsfPropery.setsDragonTigerNum3("虎");
		}
		
		if (gamePeriod.getIopennum4() > gamePeriod.getIopennum5()) {
			hnklsfPropery.setsDragonTigerNum4("龙");
		} else {
			hnklsfPropery.setsDragonTigerNum4("虎");
		}
		
		return hnklsfPropery;
	}
	
}
