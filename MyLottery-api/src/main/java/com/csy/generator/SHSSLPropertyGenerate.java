package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.ShsslPropery;
import org.springframework.stereotype.Component;

/**
 *彩种: 上海时时乐
 **/
@Component("shsslPropertyGenerate")
public class SHSSLPropertyGenerate implements PropertyGenerate {
    public static final  Long Game_Id =3L;

    public boolean handle(long gameId) {
        return Game_Id.equals(gameId);
    }


    /**计算上海时时乐的属性值
     * @param gamePeriod
     * @return
     */
    public ShsslPropery createProperty(GamePeriod gamePeriod) {
        ShsslPropery  shsslProperty = new ShsslPropery();
        String sKey = gamePeriod.getIgameid()+"."+gamePeriod.getSgameperiod();
        Integer sum = gamePeriod.getIopennum1() +gamePeriod.getIopennum2()+gamePeriod.getIopennum3();
        //设置skey和开奖总和sum
        shsslProperty.setsKey(sKey);
        shsslProperty.setsSum(sum);

        //设置单双
        if (sum%2==1) {
            shsslProperty.setsSingleDouble("单");
        }else {
            shsslProperty.setsSingleDouble("双");
        }

        //设置大小
        if (sum >=0 && sum<=10){
            shsslProperty.setsBigSmall("小");
        }else if (sum >=11 && sum <=27){
            shsslProperty.setsBigSmall("大");
        }

        return shsslProperty;
    }
}
