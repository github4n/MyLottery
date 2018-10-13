package com.csy.generator;

import com.csy.domain.Azxy10Property;
import com.csy.domain.GamePeriod;
import org.springframework.stereotype.Component;

/**
 * @program: MyLottery
 * @description:
 * @author: Mr.chen
 * @create: 2018-09-18 09:48
 **/
@Component("azxy10PropertyGenerate")
public class AZXY10PropertyGenerate implements PropertyGenerate {
    public static final  Long Game_Id = 33L;

    public boolean handle(long gameId) {
        return Game_Id.equals(gameId);
    }

    /**计算澳洲幸运10的属性
     * @param gamePeriod
     * @return
     */
    public Azxy10Property createProperty(GamePeriod gamePeriod) {
        Azxy10Property azxy10Property = new Azxy10Property();
        String skey = gamePeriod.getIgameid()+"."+gamePeriod.getSgameperiod();
        azxy10Property.setsKey(skey);
        int gySum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2();
        azxy10Property.setiGYSum(gySum);

        if (gySum > 11) {
            azxy10Property.setsGYBigSmall("大");
        } else if (gySum <= 11) {
            azxy10Property.setsGYBigSmall("小");
        }

        if (gySum % 2 == 1) {
            azxy10Property.setsGYSingleDouble("单");
        } else {
            azxy10Property.setsGYSingleDouble("双");
        }

        if (gamePeriod.getIopennum1() > gamePeriod.getIopennum10()) {
            azxy10Property.setsDragonTigerNum1("龙");
        } else {
            azxy10Property.setsDragonTigerNum1("虎");
        }

        if (gamePeriod.getIopennum2() > gamePeriod.getIopennum9()) {
            azxy10Property.setsDragonTigerNum2("龙");
        } else {
            azxy10Property.setsDragonTigerNum2("虎");
        }

        if (gamePeriod.getIopennum3() > gamePeriod.getIopennum8()) {
            azxy10Property.setsDragonTigerNum3("龙");
        } else {
            azxy10Property.setsDragonTigerNum3("虎");
        }

        if (gamePeriod.getIopennum4() > gamePeriod.getIopennum7()) {
            azxy10Property.setsDragonTigerNum4("龙");
        } else {
            azxy10Property.setsDragonTigerNum4("虎");
        }

        if (gamePeriod.getIopennum5() > gamePeriod.getIopennum6()) {
            azxy10Property.setsDragonTigerNum5("龙");
        } else {
            azxy10Property.setsDragonTigerNum5("虎");
        }
        return azxy10Property;
    }
}
