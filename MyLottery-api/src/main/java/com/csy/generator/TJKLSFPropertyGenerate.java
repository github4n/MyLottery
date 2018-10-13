package com.csy.generator;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import com.csy.domain.GamePeriod;
import com.csy.domain.TjklsfPropery;

@Component("TJKLSFPropertyGenerate")
public class TJKLSFPropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 26L;
	
	@Override
	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算cqssc对应的属性值
	 */
	public TjklsfPropery createProperty(GamePeriod gamePeriod) {
		TjklsfPropery tjklsfPropery = new TjklsfPropery();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		int sNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3() + gamePeriod.getIopennum4() + gamePeriod.getIopennum5()+ gamePeriod.getIopennum6()+ gamePeriod.getIopennum7()+ gamePeriod.getIopennum8();
		//sKey
		tjklsfPropery.setsKey(sKey);
		//总和
		tjklsfPropery.setsSum(sNum);
		//总和单双
		if (sNum % 2 == 1) {
			tjklsfPropery.setsSumSingleDouble("单");
		} else {
			tjklsfPropery.setsSumSingleDouble("双");
		}
		//总和大小
		if (sNum == 84) {
			tjklsfPropery.setsSumBigSmall("和");
		} else if (sNum>=36 && sNum <= 83) {
			tjklsfPropery.setsSumBigSmall("小");
		}else if(sNum>=85 && sNum <= 132){
			tjklsfPropery.setsSumBigSmall("大");			
		}
		//尾大 尾小
		if (sNum % 10 <= 4) {
			tjklsfPropery.setsSumTailSize("尾小");
		} else if (sNum % 10 > 4) {
			tjklsfPropery.setsSumTailSize("尾大");
		}
		

		if (gamePeriod.getIopennum1() > gamePeriod.getIopennum8()) {
			tjklsfPropery.setsDragonTigerNum1("龙");
		} else {
			tjklsfPropery.setsDragonTigerNum1("虎");
		}
		
		if (gamePeriod.getIopennum2() > gamePeriod.getIopennum7()) {
			tjklsfPropery.setsDragonTigerNum2("龙");
		} else {
			tjklsfPropery.setsDragonTigerNum2("虎");
		}
		
		if (gamePeriod.getIopennum3() > gamePeriod.getIopennum6()) {
			tjklsfPropery.setsDragonTigerNum3("龙");
		} else {
			tjklsfPropery.setsDragonTigerNum3("虎");
		}
		
		if (gamePeriod.getIopennum4() > gamePeriod.getIopennum5()) {
			tjklsfPropery.setsDragonTigerNum4("龙");
		} else {
			tjklsfPropery.setsDragonTigerNum4("虎");
		}
		
		return tjklsfPropery;
	}
	
}
