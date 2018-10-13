package com.csy.generator;

import com.alibaba.fastjson.JSON;
import com.csy.domain.Bjkl8Property;
import com.csy.domain.GamePeriod;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 彩种:北京快乐8
 *
 **/
@Component("bjkl8PropertyGenerate")
public class BJKL8PropertyGenerate implements PropertyGenerate{
    public static final Long GAME_ID = 23L;


    public boolean handle(long gameId) {
        return GAME_ID.equals(gameId);
    }

    /**计算北京快乐8的属性值
     * @param gamePeriod
     * @return
     */
    public Bjkl8Property createProperty(GamePeriod gamePeriod) {
        Bjkl8Property bjkl8Property = new Bjkl8Property();
        bjkl8Property.setsFrisbeeNumber("0");//飞盘号暂时没有要求,统一设置为0
        String skey = gamePeriod.getIgameid() + "." +gamePeriod.getSgameperiod();
        bjkl8Property.setsKey(skey);
        Map<String, Integer> openNums = getOpenNums(gamePeriod.getSopennum(), bjkl8Property.getResultMap());
        int sSum = getGYSum(openNums);
        bjkl8Property.setsSum(sSum);
        //设置开奖结果
        bjkl8Property.setsOpenNums(JSON.toJSONString(openNums));

        //大小
        if (sSum > 810) {
            bjkl8Property.setsSumBigSmall("大");
        } else if(sSum<810){
            bjkl8Property.setsSumBigSmall("小");
        }else{
            bjkl8Property.setsSumBigSmall("和");
        }
        //单双
        if (sSum % 2 == 1) {
            bjkl8Property.setsSumSingleDouble("单");
        } else {
            bjkl8Property.setsSumSingleDouble("双");
        }
        //上下盘
        Map<String, Integer> result = getTopAndButtom(openNums);
        if (result.get("top")>result.get("bottom")){
            bjkl8Property.setsTopAndBottom("上");
        }else if (result.get("top")<result.get("bottom")){
            bjkl8Property.setsTopAndBottom("下");
        }else{
            bjkl8Property.setsTopAndBottom("和");
        }
        //五行
        if (sSum>=210&&sSum<=695){
            bjkl8Property.setsSumFiveElements("金");
        }else if (sSum>=696&&sSum<=763){
            bjkl8Property.setsSumFiveElements("木");
        }else if (sSum>=764&&sSum<=855){
            bjkl8Property.setsSumFiveElements("水");
        }else if (sSum>=856&&sSum<=923){
            bjkl8Property.setsSumFiveElements("火");
        }else if (sSum>=924&&sSum<=1410){
            bjkl8Property.setsSumFiveElements("土");
        }
        return bjkl8Property;
    }


    /**计算北京快乐8开奖数据的总和
     * @param resultMap
     * @return
     */
    private int getGYSum (Map<String ,Integer> resultMap){
        int gySum = 0 ;
        Collection<Integer> resultSet = resultMap.values();
        for (Integer result: resultSet){
                //获取1~20号的iopennum的值,并相加
                gySum= gySum+result;
        }
        return gySum;
    }

    /**计算每个元素上下盘的数量
     * @param resultMap
     * @return Map<String , Integer>
     *     Map中的key有 top: 上盘的数量
     *                 bottom:下盘的数量
     */
    private Map<String , Integer> getTopAndButtom(Map<String ,Integer> resultMap){

        //初始化map集合,用于封装上下盘数量
        Map<String,Integer> result = new HashMap<>();
        //用于记录上下盘结果
        Integer top = 0;
        Integer bottom = 0;
        //取出结果集
        Collection<Integer> resultSet =  resultMap.values();
        for (Integer temp :resultSet){
                //获取1~20号的iopennum的值,并相加
                if (temp>=1&&temp<=40){
                    top = ++top;
                }else if(temp >=41 && temp<=80){
                    bottom =++bottom;
                }
        }
        result.put("top",top);
        result.put("bottom",bottom);
        return result;
    }


    /**取出所有的开奖结果
     * @param open//开奖结果的字符串sOpenNum
     * @return Map<String , Integer></>//开奖结果集合
     */
    private Map<String ,Integer> getOpenNums(String open,Map<String ,Integer> map){
        List<String> sOpenNums = Lists.newArrayList(StringUtils.split(open, "|"));
        for (int i=1; i<sOpenNums.size() ; i++){
        String title = "iopenNum"+i;
            map.put(title, Integer.valueOf(sOpenNums.get(i-1)));
        }
        return map;
    }

}
