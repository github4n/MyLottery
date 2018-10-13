package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.JsftProperty;
import org.springframework.stereotype.Component;

/**
 * 彩种: 极速飞艇
 */
@Component("jsftPropertyGenerate")
public class JSFTPropertyGenerate implements PropertyGenerate {
	public static final Long GAME_ID = 41L;

	public boolean handle(long iGameId) {
		return GAME_ID.equals(iGameId);
	}
	
	@Override
	/**
	 * 计算极速飞艇对应的属性值
	 */
	public JsftProperty createProperty(GamePeriod gamePeriod) {

		JsftProperty jsftProperty = new JsftProperty();
		String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
		int gySum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2();

		jsftProperty.setsKey(sKey);
		jsftProperty.setiGYSum(gySum);

		if (gySum > 11) {
			jsftProperty.setsGYBigSmall("大");
		} else if (gySum <= 11) {
			jsftProperty.setsGYBigSmall("小");
		}

		if (gySum % 2 == 1) {
			jsftProperty.setsGYSingleDouble("单");
		} else {
			jsftProperty.setsGYSingleDouble("双");
		}

		if (gamePeriod.getIopennum1() > gamePeriod.getIopennum10()) {
			jsftProperty.setsDragonTigerNum1("龙");
		} else {
			jsftProperty.setsDragonTigerNum1("虎");
		}

		if (gamePeriod.getIopennum2() > gamePeriod.getIopennum9()) {
			jsftProperty.setsDragonTigerNum2("龙");
		} else {
			jsftProperty.setsDragonTigerNum2("虎");
		}

		if (gamePeriod.getIopennum3() > gamePeriod.getIopennum8()) {
			jsftProperty.setsDragonTigerNum3("龙");
		} else {
			jsftProperty.setsDragonTigerNum3("虎");
		}

		if (gamePeriod.getIopennum4() > gamePeriod.getIopennum7()) {
			jsftProperty.setsDragonTigerNum4("龙");
		} else {
			jsftProperty.setsDragonTigerNum4("虎");
		}

		if (gamePeriod.getIopennum5() > gamePeriod.getIopennum6()) {
			jsftProperty.setsDragonTigerNum5("龙");
		} else {
			jsftProperty.setsDragonTigerNum5("虎");
		}

		return jsftProperty;
	}



}
