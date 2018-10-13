package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.Jsk3Propery;
import org.springframework.stereotype.Component;

/**
 * 彩种: 江苏快3
 **/
@Component("jsk3PropertyGenerate")
public class JSK3PropertyGenerate implements  PropertyGenerate {
    public static final Long Game_Id = 10L;

    public boolean handle(long gameId) {
        return Game_Id.equals(gameId);
    }

    public Jsk3Propery createProperty(GamePeriod gamePeriod) {
        Jsk3Propery  jsk3Propery = new Jsk3Propery();
        Integer gySum = gamePeriod.getIopennum1()+ gamePeriod.getIopennum2()+gamePeriod.getIopennum3();
        String  sKey = gamePeriod.getIgameid()+"."+gamePeriod.getSgameperiod();
        //设置总和
        jsk3Propery.setsSum(gySum);
        jsk3Propery.setsKey(sKey);

        //设置单双
        if (gySum%2==1) {
            jsk3Propery.setsSingleDouble("单");
        }else {
            jsk3Propery.setsSingleDouble("双");
        }

        //设置大小
        if (gySum >=3 && gySum<=10){
            jsk3Propery.setsBigSmall("小");
        }else if (gySum >=11 && gySum <= 17 ){
            jsk3Propery.setsBigSmall("大");
        }

        //设置鱼虾蟹
        jsk3Propery.setsFishShrimpCrab1(getFishShrimpCrab(gamePeriod.getIopennum1()));
        jsk3Propery.setsFishShrimpCrab2(getFishShrimpCrab(gamePeriod.getIopennum2()));
        jsk3Propery.setsFishShrimpCrab3(getFishShrimpCrab(gamePeriod.getIopennum3()));


        return jsk3Propery;
    }


    private  String getFishShrimpCrab(Short  openNum){
        if (openNum == 1){
            return  "鱼";
        } else if(openNum ==2){
            return "虾";
        } else if(openNum ==3){
            return "葫芦";
        } else if (openNum ==4){
            return "铜钱";
        } else if (openNum ==5){
            return "蟹";
        }
        return "鸡";
    }
}