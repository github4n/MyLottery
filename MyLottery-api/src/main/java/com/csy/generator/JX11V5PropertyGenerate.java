package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.Jx11v5Propery;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 彩种: 江西11选5
 **/
@Component("jx11v5PropertyGenerate")
public class JX11V5PropertyGenerate implements PropertyGenerate {
    public static final Long Game_Id = 14L;

    public boolean handle(long gameId) {
        return Game_Id.equals(gameId);
    }

    /**计算江西11选5的属性值
     * @param gamePeriod
     * @return
     */
    public Jx11v5Propery createProperty(GamePeriod gamePeriod) {
        Jx11v5Propery jx11v5Propery = new Jx11v5Propery();
        String sKey = gamePeriod.getIgameid()+"."+gamePeriod.getSgameperiod();
        Integer gySum = gamePeriod.getIopennum1()+gamePeriod.getIopennum2()+gamePeriod.getIopennum3()
                +gamePeriod.getIopennum4()+gamePeriod.getIopennum5();
        //设置skey 和 开奖总和
        jx11v5Propery.setsKey(sKey);
        jx11v5Propery.setsSum(gySum);

        //大小
        if (gySum>=31){
            jx11v5Propery.setsBigSmall("大");
        }else if (gySum <=29){
            jx11v5Propery.setsBigSmall("小");
        }else {
            jx11v5Propery.setsBigSmall("和");
        }

        //单双
        if (gySum%2==1){
            jx11v5Propery.setsSingleDouble("单");
        }else {
            jx11v5Propery.setsSingleDouble("双");
        }

        //设置龙虎
        if (gamePeriod.getIopennum1() > gamePeriod.getIopennum5()){
            jx11v5Propery.setsDragonTiger("龙");
        }else {
            jx11v5Propery.setsDragonTiger("虎");
        }

        //设置头三中三尾三
        jx11v5Propery.setsTopThree(checkThree(gamePeriod.getIopennum1(),gamePeriod.getIopennum2(),gamePeriod.getIopennum3()));
        jx11v5Propery.setsMiddleThree(checkThree(gamePeriod.getIopennum2(),gamePeriod.getIopennum3(),gamePeriod.getIopennum4()));
        jx11v5Propery.setsPostThree(checkThree(gamePeriod.getIopennum3(),gamePeriod.getIopennum4(),gamePeriod.getIopennum5()));

        return jx11v5Propery;
    }


    /**计算前中后3个数的开奖结果
     * @param openNum1
     * @param openNum2
     * @param openNum3
     * @return String //顺子,半顺,杂六
     */
    private String checkThree(int openNum1, int openNum2, int openNum3) {
        List<String> shunZi = Arrays.asList("1,2,3", "2,3,4", "3,4,5", "4,5,6", "5,6,7", "6,7,8", "7,8,9", "8,9,10", "9,10,11",
                "11,0,1","0,1,2");
        List<String> banshun = Arrays.asList("1,2","2,3","3,4","4,5","5,6","6,7","7,8","8,9","9,10","10,11","11,1");
        if (openNum1==openNum2 && openNum2 ==openNum3 && openNum1 ==openNum3){
            //两两相等
            return "杂六";

        }else {
            for (String sz : shunZi) {
                if (sz.contains(openNum1 + "") && sz.contains(openNum2 + "") && sz.contains(openNum3 + "")) {
                    return "顺子";
                }
            }
            for (String bs : banshun){
                if ((bs.contains(openNum1+"") && bs.contains(openNum2+"")) ||(bs.contains(openNum2+"") && bs.contains(openNum3+""))
                        ||(bs.contains(openNum1+"") && bs.contains(openNum3+""))){
                    //防止1,1,3 為半順
                    if(openNum1==openNum2 && !bs.contains(openNum3+"")
                            || openNum2 ==openNum3 && !bs.contains(openNum1+"")
                            || openNum3 ==openNum1 && !bs.contains(openNum2+"")){
                        return "杂六";
                    }
                    return "半顺";
                }
            }
        }
        return "杂六";
    }
}
