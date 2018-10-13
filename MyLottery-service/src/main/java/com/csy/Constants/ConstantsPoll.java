package com.csy.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: MyLottery
 * @description:
 * @author: Mr.chen
 * @create: 2018-09-21 11:51
 **/
public class ConstantsPoll {
    public static final String TotalPeriodCount = "TotalPeriodCount:{0}";
    public static final String NewGamePeriod = "NewGamePeriod:{0}";
    public static final String FIRST_PERIOD ="FIRST_PERIOD:{0}";//第一条数据的期号

    //初始化彩种数据
    public static Map<String, Long> code_gameId= new HashMap<>();//彩种编码对应彩种id
    public static Map<Long,Long> closest_timeSet = new HashMap<>();//彩种时间间隔集合
    public static Map<Long,String> OpentimeSet = new HashMap<>();//每天开始时间
    public static Map<Long, Integer > totalCountSet = new HashMap<>();//每个彩种的总条数
    public static Map<Long, Integer> gameIdTimeInterval = new HashMap<Long, Integer>();//时间间隔
    public static Map<Long, String> gameSetInRedis = new HashMap<Long, String>();//redis中彩种对应的key


    static {
//        code_gameId.put("fc3d", 1L);		//福彩3d
//        code_gameId.put("pl3", 2L);		    //排列3
//        code_gameId.put("shssl", 3L);		//上海时时乐
//        code_gameId.put("tjssc", 4L);		//天津时时彩
        code_gameId.put("cqssc", 5L);		//重庆时时彩
//        code_gameId.put("xjssc", 7L);		//新疆时时彩
        code_gameId.put("bjpk10", 9L);		//北京pk10
//        code_gameId.put("jsk3", 10L);		//江苏快3
//        code_gameId.put("ahk3", 11L);		//安徽快3
//        code_gameId.put("jx11v5", 14L);		//江西11选5
//        code_gameId.put("gd11x5", 15L);		//廣東11选5
//        code_gameId.put("gxk3", 17L);		//廣西快3
//        code_gameId.put("bjkl8", 23L);		//北京快乐8
//        code_gameId.put("gdklsf", 27L);		//广东快乐十分
//        code_gameId.put("xync", 28L);		//重庆幸运农场
//        code_gameId.put("azxy10", 33L);		//澳洲幸运10
//        code_gameId.put("jssc", 37L);		//极速赛车
        code_gameId.put("mlaft", 38L);		//幸运飞艇
//        code_gameId.put("jsft", 41L);		//极速飞艇
//        code_gameId.put("pcdd", 42L);		//pc蛋蛋


        //每个彩种的时间间隔(秒)
        closest_timeSet.put(5l,600L);
        closest_timeSet.put(9l,300L);
        closest_timeSet.put(38l,300L);
//		closest_timeSet.put(41l,75L);
//		closest_timeSet.put(42l,75L);
        //每个彩种的每天的开奖时间
        OpentimeSet.put(5l,"10:00:00");
        OpentimeSet.put(9l,"09:07:00");
        OpentimeSet.put(38l,"13:09:00");
//        OpentimeSet.put(41l,"03:30:00");
//        OpentimeSet.put(42l,"03:30:00");

        //每个彩种的总条数
        totalCountSet.put(5l,120);
        totalCountSet.put(9l,179);
        totalCountSet.put(38l,180);

        gameIdTimeInterval.put(9L, 300000);//北京赛车（毫秒）
        gameIdTimeInterval.put(5L, 600000);//重庆时时彩
        gameIdTimeInterval.put(38L,300000);//幸运飞艇
        gameIdTimeInterval.put(41L,75000);//极速飞艇

        gameSetInRedis.put(5l,"CqsscNext");
        gameSetInRedis.put(9l,"pk10Next");
        gameSetInRedis.put(38l,"XyftNext");
        gameSetInRedis.put(41l,"JsftNext");
    }
}
