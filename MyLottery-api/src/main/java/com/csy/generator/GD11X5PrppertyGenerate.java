package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.Gd11x5Propery;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * 彩种: 广东11选5
 **/
@Component("gd11x5PropertyGenerate")
public class GD11X5PrppertyGenerate implements PropertyGenerate {
    public static final Long GAME_ID = 15L;

    public boolean handle(long gameId) {
        return GAME_ID.equals(gameId);
    }

    public Gd11x5Propery createProperty(GamePeriod gamePeriod) {
        Gd11x5Propery gd11x5Propery = new Gd11x5Propery();
        String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        int sumNum = gamePeriod.getIopennum1() + gamePeriod.getIopennum2() + gamePeriod.getIopennum3() + gamePeriod.getIopennum4() + gamePeriod.getIopennum5();
        //sKey
        gd11x5Propery.setsKey(sKey);
        //总和
        gd11x5Propery.setsSum(sumNum);
        //总和大小
        if (sumNum >= 31) {
            gd11x5Propery.setsBigSmall("大");
        } else if (sumNum<=29){
            gd11x5Propery.setsBigSmall("小");
        }else {
            gd11x5Propery.setsBigSmall("和");
        }
        //总和单双
        if (sumNum % 2 == 1) {
            gd11x5Propery.setsSingleDouble("单");
        } else {
            gd11x5Propery.setsSingleDouble("双");
        }
        //龙虎
        if (gamePeriod.getIopennum1() > gamePeriod.getIopennum5()) {
            gd11x5Propery.setsDragonTiger("龙");

        } else {
            gd11x5Propery.setsDragonTiger("虎");
        }
        //前三、中三、后三
        gd11x5Propery.setsTopThree(this.checkThree(gamePeriod.getIopennum1(), gamePeriod.getIopennum2(), gamePeriod.getIopennum3()));
        gd11x5Propery.setsMiddleThree(this.checkThree(gamePeriod.getIopennum2(), gamePeriod.getIopennum3(), gamePeriod.getIopennum4()));
        gd11x5Propery.setsPostThree(this.checkThree(gamePeriod.getIopennum3(), gamePeriod.getIopennum4(), gamePeriod.getIopennum5()));

        return gd11x5Propery;
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

