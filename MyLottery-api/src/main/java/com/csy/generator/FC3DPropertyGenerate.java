package com.csy.generator;

import com.csy.domain.Fc3dProperty;
import com.csy.domain.GamePeriod;
import org.springframework.stereotype.Component;

/**
 * 彩種:福彩3d
 **/
@Component("fc3dPropertyGenerate")
public class FC3DPropertyGenerate implements PropertyGenerate {
    public static final Long GAME_ID = 1L;

    public boolean handle(long gameId) {
        return GAME_ID.equals(gameId);
    }

    public Fc3dProperty createProperty(GamePeriod gamePeriod) {
        Fc3dProperty fc3dProperty = new Fc3dProperty();
        String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        int sumNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3() ;
        //sKey
        fc3dProperty.setsKey(sKey);
        //总和
        fc3dProperty.setiSum(sumNum);
        //总和大小
        if (sumNum >= 11 && sumNum <= 27) {
            fc3dProperty.setsSumBigSmall("大");
        } else if (sumNum<=10 && sumNum >=0){
            fc3dProperty.setsSumBigSmall("小");
        }
        //总和单双
        if (sumNum % 2 == 1) {
            fc3dProperty.setsSumSingleDouble("单");
        } else {
            fc3dProperty.setsSumSingleDouble("双");
        }
        return fc3dProperty;
    }
}
