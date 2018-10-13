package com.csy.generator;

import org.springframework.stereotype.Component;

import com.csy.domain.Bjpk10Property;
import com.csy.domain.GamePeriod;

@Component("bjpk10PropertyGenerate")
public class BJPK10PropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 9L;

	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算BJPK10对应的属性值
	 */
	public Bjpk10Property createProperty(GamePeriod gamePeriod) {
		Bjpk10Property bjpk10Property = new Bjpk10Property();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		int gySum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2();
		
		bjpk10Property.setsKey(sKey);
		bjpk10Property.setiGYSum(gySum);
		
		if (gySum > 11) {
			bjpk10Property.setsGYBigSmall("大");
		} else if (gySum <= 11) {
			bjpk10Property.setsGYBigSmall("小");
		}
		
		if (gySum % 2 == 1) {
			bjpk10Property.setsGYSingleDouble("单");
		} else {
			bjpk10Property.setsGYSingleDouble("双");
		}

		if (gamePeriod.getIopennum1() > gamePeriod.getIopennum10()) {
			bjpk10Property.setsDragonTigerNum1("龙");
		} else {
			bjpk10Property.setsDragonTigerNum1("虎");
		}
		
		if (gamePeriod.getIopennum2() > gamePeriod.getIopennum9()) {
			bjpk10Property.setsDragonTigerNum2("龙");
		} else {
			bjpk10Property.setsDragonTigerNum2("虎");
		}
		
		if (gamePeriod.getIopennum3() > gamePeriod.getIopennum8()) {
			bjpk10Property.setsDragonTigerNum3("龙");
		} else {
			bjpk10Property.setsDragonTigerNum3("虎");
		}
		
		if (gamePeriod.getIopennum4() > gamePeriod.getIopennum7()) {
			bjpk10Property.setsDragonTigerNum4("龙");
		} else {
			bjpk10Property.setsDragonTigerNum4("虎");
		}
		
		if (gamePeriod.getIopennum5() > gamePeriod.getIopennum6()) {
			bjpk10Property.setsDragonTigerNum5("龙");
		} else {
			bjpk10Property.setsDragonTigerNum5("虎");
		}
		
		return bjpk10Property;
	}



}
