package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.Pl3Property;
import org.springframework.stereotype.Component;

/**
 * @program: MyLottery
 * @description:
 * @author: Mr.chen
 * @create: 2018-09-17 16:26
 **/
@Component("pl3PropertyGenerate")
public class PL3PropertyGenerate implements  PropertyGenerate {
    public static final Long GAME_ID = 2L;

    @Override
    public boolean handle(long iGameId) {
        return GAME_ID.equals(iGameId);
    }

    public Pl3Property createProperty(GamePeriod gamePeriod) {
        Pl3Property pl3property = new Pl3Property();
        String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        int sumNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3() ;
        //sKey
        pl3property.setsKey(sKey);
        //总和
        pl3property.setiSum(sumNum);
        //总和大小
        if (sumNum >= 11 && sumNum <= 27) {
            pl3property.setsSumBigSmall("大");
        } else if (sumNum<=10 && sumNum >=0){
            pl3property.setsSumBigSmall("小");
        }
        //总和单双
        if (sumNum % 2 == 1) {
            pl3property.setsSumSingleDouble("单");
        } else {
            pl3property.setsSumSingleDouble("双");
        }
        return pl3property;
    }
}
