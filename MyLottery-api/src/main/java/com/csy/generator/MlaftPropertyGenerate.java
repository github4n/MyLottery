package com.csy.generator;

import org.springframework.stereotype.Component;

import com.csy.domain.GamePeriod;
import com.csy.domain.MlaftProperty;

@Component("mlaftPropertyGenerate")
public class MlaftPropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 38L;

	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算幸运飞艇对应的属性值
	 */
	public MlaftProperty createProperty(GamePeriod gamePeriod) {
		MlaftProperty mlaftProperty = new MlaftProperty();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		int gySum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2();
		
		mlaftProperty.setsKey(sKey);
		mlaftProperty.setiGYSum(gySum);
		
		if (gySum > 11) {
			mlaftProperty.setsGYBigSmall("大");
		} else if (gySum <= 11) {
			mlaftProperty.setsGYBigSmall("小");
		}
		
		if (gySum % 2 == 1) {
			mlaftProperty.setsGYSingleDouble("单");
		} else {
			mlaftProperty.setsGYSingleDouble("双");
		}

		if (gamePeriod.getIopennum1() > gamePeriod.getIopennum10()) {
			mlaftProperty.setsDragonTigerNum1("龙");
		} else {
			mlaftProperty.setsDragonTigerNum1("虎");
		}
		
		if (gamePeriod.getIopennum2() > gamePeriod.getIopennum9()) {
			mlaftProperty.setsDragonTigerNum2("龙");
		} else {
			mlaftProperty.setsDragonTigerNum2("虎");
		}
		
		if (gamePeriod.getIopennum3() > gamePeriod.getIopennum8()) {
			mlaftProperty.setsDragonTigerNum3("龙");
		} else {
			mlaftProperty.setsDragonTigerNum3("虎");
		}
		
		if (gamePeriod.getIopennum4() > gamePeriod.getIopennum7()) {
			mlaftProperty.setsDragonTigerNum4("龙");
		} else {
			mlaftProperty.setsDragonTigerNum4("虎");
		}
		
		if (gamePeriod.getIopennum5() > gamePeriod.getIopennum6()) {
			mlaftProperty.setsDragonTigerNum5("龙");
		} else {
			mlaftProperty.setsDragonTigerNum5("虎");
		}
		
		return mlaftProperty;
	}
}
