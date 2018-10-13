package com.csy.generator;

import com.csy.domain.GamePeriod;
import com.csy.domain.XyncPropery;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;

/**
 * 彩种: 重庆幸运农场
 **/
@Component("xyncPropertyGenerate")
public class XYNCPropertyGenerate implements PropertyGenerate{

    public static final Long GAME_ID = 28L;
    public static final int  GDKLSF_LOTTERY_SIZE=8;//开奖号码个数

    public boolean handle(long gameId) {
        return GAME_ID.equals(gameId);
    }

    /**计算重庆幸运农场的属性
     * @param gamePeriod //开奖结果
     * @return XyncProperty //重庆幸运农场属性
     * 规则: 1.总和小于84是小, 85~132是大,84为和,其余为不中奖
     *      2.总和单双
     *      3.尾数大小,尾数大于等于5为大
     *      4.龙虎-->1|8,2|7,3|6...比较,前者大为龙,否则为虎
     */
    public XyncPropery createProperty(GamePeriod gamePeriod) {
        XyncPropery xyncProperty = new XyncPropery();
        String sKey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        //总和为八个开奖号码的总和
        int gySum = getGYSum(gamePeriod);
        //设置重庆幸运农场的属性
        xyncProperty.setsKey(sKey);
        xyncProperty.setiSum(gySum);
        //大小
        if (gySum <=83){
            xyncProperty.setsSumBigSmall("小");
        }else if (gySum>=85&&gySum<=132){
            xyncProperty.setsSumBigSmall("大");
        }else if (gySum == 84){
            xyncProperty.setsSumBigSmall("和");
        }
        //单双
        if (gySum%2==1){
            xyncProperty.setsSumSingleDouble("单");
        }else {
            xyncProperty.setsSumSingleDouble("双");

        }
        //尾数大小
        int thelast = getLastNum(gySum);
        if (thelast>=5){
            xyncProperty.setsSumTailSize("尾大");
        }else {
            xyncProperty.setsSumTailSize("尾小");
        }
        //龙虎
        if (gamePeriod.getIopennum1()>gamePeriod.getIopennum8()){
            xyncProperty.setsDragonTigerNum1("龙");
        }else{
            xyncProperty.setsDragonTigerNum1("虎");
        }
        if (gamePeriod.getIopennum2()>gamePeriod.getIopennum7()){
            xyncProperty.setsDragonTigerNum2("龙");
        }else{
            xyncProperty.setsDragonTigerNum2("虎");
        }
        if (gamePeriod.getIopennum3()>gamePeriod.getIopennum6()){
            xyncProperty.setsDragonTigerNum3("龙");
        }else{
            xyncProperty.setsDragonTigerNum3("虎");
        }
        if (gamePeriod.getIopennum4()>gamePeriod.getIopennum5()){
            xyncProperty.setsDragonTigerNum4("龙");
        }else{
            xyncProperty.setsDragonTigerNum4("虎");
        }
        return xyncProperty;
    }

    /**
     * 计算重庆幸运农场的总和
     * */
    private int getGYSum (GamePeriod gamePeriod){
        int gySum = 0 ;
        for (int i=1; i<=GDKLSF_LOTTERY_SIZE ;i++ ){
            StringBuilder  sb = new StringBuilder("iopennum");
            sb.append(i);
            // System.out.println(sb.toString());
            try {
                //获取1~8号的iopennum的值,并相加
                Short iopennum = (Short) FieldUtils.readField(gamePeriod, sb.toString(), true);
                gySum= gySum+iopennum;
            // System.out.println(iopennum);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return gySum;
    }

    /**
     * 取出重庆幸运农场总和的尾数
     * */
    private int getLastNum(int gySum){
        String sum = gySum+"";
        int length = sum.length();
        String lastOne = sum.substring(length-2,length-1);
        int lastnum = Integer.parseInt(lastOne);
        return lastnum;
    }
}
