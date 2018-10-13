package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.Gxk3Propery;
import org.springframework.stereotype.Component;

/**
 * 彩种: 广西快3
 **/
@Component("gxk3PropertyGenerate")
public class GXK3PropertyGenerate implements PropertyGenerate {

    public static final Long GAME_ID = 17L;

    @Override
    public boolean handle(long iGameId) {
        return GAME_ID.equals(iGameId);
    }

    /**计算广西快3的属性值
     * @param gamePeriod
     * @return
     */
    public Gxk3Propery createProperty(GamePeriod gamePeriod) {
        Gxk3Propery gxk3Propery = new Gxk3Propery();
        String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        int sumNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3();
        gxk3Propery.setsKey(sKey);
        //设置总和
        gxk3Propery.setsSum(sumNum);
        //设置大小
        if (sumNum>=3&&sumNum<=10){
            gxk3Propery.setsSingleDouble("小");
        }else if (sumNum>=11&&sumNum<=17){
            gxk3Propery.setsSingleDouble("大");
        }
        //设置单双
        if (sumNum%2==1){
            gxk3Propery.setsSingleDouble("单");
        }else{
            gxk3Propery.setsSingleDouble("双");
        }
        //设置鱼虾蟹
        gxk3Propery.setsFishShrimpCrab1(getFishShrimpCrab(gamePeriod.getIopennum1()));
        gxk3Propery.setsFishShrimpCrab2(getFishShrimpCrab(gamePeriod.getIopennum2()));
        gxk3Propery.setsFishShrimpCrab3(getFishShrimpCrab(gamePeriod.getIopennum3()));
        return gxk3Propery;
    }

    //计算鱼虾蟹
    private String getFishShrimpCrab(Short iopennum){
        if (iopennum == 1){
            return "鱼";
        }else if (iopennum ==2){
            return "虾";
        }else if (iopennum ==3){
            return "葫芦";
        }else if (iopennum ==4){
            return "铜钱";
        }else if (iopennum ==5){
            return "蟹";
        }else if (iopennum ==6){
            return "鸡";
        }
        return null;
    }
}
