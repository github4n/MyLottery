package com.csy.generator;

import com.csy.domain.Ahk3Propery;
import com.csy.domain.GamePeriod;
import org.springframework.stereotype.Component;

/**
 * @program: MyLottery
 * @description:
 * @author: Mr.chen
 * @create: 2018-09-19 11:30
 **/
@Component("ahk3PropertyGenerate")
public class AHK3PropertyGenerate implements PropertyGenerate {
    public static final Long Game_Id = 11L;

    public boolean handle(long gameId) {
        return Game_Id.equals(gameId);
    }

    /**计算安徽快3的属性
     * @param gamePeriod
     * @return
     */
    public Ahk3Propery createProperty(GamePeriod gamePeriod) {
        Ahk3Propery ahk3Propery = new Ahk3Propery();
        //设置skey和开奖总和
        Integer gySum = gamePeriod.getIopennum1()+gamePeriod.getIopennum2()+ gamePeriod.getIopennum3();
        String sKey = gamePeriod.getIgameid()+"."+gamePeriod.getSgameperiod();
        ahk3Propery.setsSum(gySum);
        ahk3Propery.setsKey(sKey);

        //设置大小
        if (gySum>=3 && gySum <=10){
            ahk3Propery.setsBigSmall("小");
        }else  if (gySum >=11 && gySum <=17){
            ahk3Propery.setsBigSmall("大");
        }

        //设置单双
        if (gySum % 2 == 1){
            ahk3Propery.setsSingleDouble("单");
        }else {
            ahk3Propery.setsSingleDouble("双");
        }

        //设置鱼虾蟹
        ahk3Propery.setsFishShrimpCrab1(getFishShrimpCrab(gamePeriod.getIopennum1()));
        ahk3Propery.setsFishShrimpCrab2(getFishShrimpCrab(gamePeriod.getIopennum2()));
        ahk3Propery.setsFishShrimpCrab3(getFishShrimpCrab(gamePeriod.getIopennum3()));

        return ahk3Propery;
    }

    /**计算开奖号码对应的鱼虾蟹
     * @param openNum
     * @return
     */
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
