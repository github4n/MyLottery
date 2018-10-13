package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.JsscProperty;
import org.springframework.stereotype.Component;

/**
 * 彩种: 极速赛车
 **/
@Component("jsscPropertyGenerate")
public class JSSCPropertyGenerate implements PropertyGenerate {
    public static final Long GAME_ID = 37L;

    @Override
    public boolean handle(long iGameId) {
        return GAME_ID.equals(iGameId);
    }


    /**计算极速赛车的属性值
     * @param gamePeriod
     * @return
     *
     */
    public JsscProperty createProperty(GamePeriod gamePeriod) {
        JsscProperty  jsscProperty = new JsscProperty();
        String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        int gySum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2();

        jsscProperty.setsKey(sKey);
        jsscProperty.setiGYSum(gySum);

        if (gySum > 11) {
            jsscProperty.setsGYBigSmall("大");
        } else if (gySum <= 11) {
            jsscProperty.setsGYBigSmall("小");
        }

        if (gySum % 2 == 1) {
            jsscProperty.setsGYSingleDouble("单");
        } else {
            jsscProperty.setsGYSingleDouble("双");
        }

        if (gamePeriod.getIopennum1() > gamePeriod.getIopennum10()) {
            jsscProperty.setsDragonTigerNum1("龙");
        } else {
            jsscProperty.setsDragonTigerNum1("虎");
        }

        if (gamePeriod.getIopennum2() > gamePeriod.getIopennum9()) {
            jsscProperty.setsDragonTigerNum2("龙");
        } else {
            jsscProperty.setsDragonTigerNum2("虎");
        }

        if (gamePeriod.getIopennum3() > gamePeriod.getIopennum8()) {
            jsscProperty.setsDragonTigerNum3("龙");
        } else {
            jsscProperty.setsDragonTigerNum3("虎");
        }

        if (gamePeriod.getIopennum4() > gamePeriod.getIopennum7()) {
            jsscProperty.setsDragonTigerNum4("龙");
        } else {
            jsscProperty.setsDragonTigerNum4("虎");
        }

        if (gamePeriod.getIopennum5() > gamePeriod.getIopennum6()) {
            jsscProperty.setsDragonTigerNum5("龙");
        } else {
            jsscProperty.setsDragonTigerNum5("虎");
        }

        return jsscProperty;
    }
}
