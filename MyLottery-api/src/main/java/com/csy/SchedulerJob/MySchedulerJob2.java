package com.csy.SchedulerJob;

import com.csy.Constants.ConstantsPoll;
import com.csy.common.util.DateUtil;
import com.csy.common.util.RedisUtil;
import com.csy.domain.GamePeriod;
import com.csy.mapper.GamePeriodMapper;
import com.csy.mapper.GameTypeMapper;
import org.apache.commons.lang3.StringUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 每天下午五点,生成所有的开奖空数据
 */
@Component
public class MySchedulerJob2 implements Job {
 
    @Autowired
    private GamePeriodMapper gamePeriodMapper;
    @Autowired
    private GameTypeMapper gameTypeMapper;
    @Autowired
    RedisUtil  redisUtil;


    /**定时任务
     * 生成明天的计划
     * @param context
     * @throws JobExecutionException
     */
    @Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
        Collection<Long> values = ConstantsPoll.code_gameId.values();
        //遍历集合取出igameId , 然后根据igameId查询开奖时间
	    for (Long igameId : values){
            //获取该彩种的每天开奖条数
            Integer totalCount = gameTypeMapper.selectPeriodCount(igameId);
	        //取出该彩种的每期间隔
            Long closeTimeSet = ConstantsPoll.closest_timeSet.get(igameId);
            if (closeTimeSet==null){//避免初始化集合中不存在该彩种
                continue;
            }
            closeTimeSet =ConstantsPoll.closest_timeSet.get(igameId)*1000;
            //取出每天的开始开奖的时间
            String timerStart = ConstantsPoll.OpentimeSet.get(igameId);
            if (timerStart!=null){

            //拼接出正确的当日開始开奖的时间
                Long openTimes = DateUtil.getLotteryTimes(new Date(),timerStart);
                Long endTimes ;
                endTimes = openTimes+(closeTimeSet*(totalCount-1));
                if (totalCount==1){
                    endTimes=openTimes;
                }
                if (igameId==5){//区别于重庆时时彩
                    endTimes = openTimes+(closeTimeSet*72)+(closeTimeSet/2*47);
                }

            //先查询明天是否已经有空数据了,如果没有就生成,已经有了就跳出
            Integer tomorrowCount = gamePeriodMapper.selectTodayPeriodCount(igameId, DateUtil.getDateAfterIDay(
                    DateUtil.getTime(DateUtil.getLotteryTimes(new Date(),"05:00:00")),//大于每天凌晨五点
                    1));
            //查询最近一期的期号;
             GamePeriod maxPeriod = gamePeriodMapper.selectMaxPeriod(igameId);
                String sGamePeriod=null;
            if (maxPeriod !=null){
                sGamePeriod = maxPeriod.getSgameperiod();
                if (StringUtils.isBlank(sGamePeriod)) {//避免该彩种还没有数据
                    sGamePeriod ="0";
                }
             }
            Long maxGamePeriod = Long.parseLong(sGamePeriod);
            //避免开奖期数中间的缺漏
            Long timeGap = 0L;//时间间隔
            if (maxPeriod.getDopentime().getTime()<openTimes){
                timeGap =0L;
            }else if (maxPeriod.getDopentime().getTime()<endTimes){
                 timeGap = endTimes - maxPeriod.getDopentime().getTime(); //今日结束时间与当前时间的间隔
            }
            Integer countGap = null; //今天余下的期数
            Long nextPeriodOpenTime =null;//今天的下一期的开奖时间
            if (timeGap/closeTimeSet >0){
                countGap=Integer.valueOf((timeGap/closeTimeSet+1)+"");
                maxGamePeriod= countGap + maxGamePeriod;//加上今天还没开的期数
                nextPeriodOpenTime=maxPeriod.getDopentime().getTime()+closeTimeSet;
            }
            //生成今天剩余的数据
            if (nextPeriodOpenTime !=null && countGap !=null){
                System.out.println("生成今日数据");
                //清空开始时间的秒数
                Date dateBeginTime = DateUtil.getDateBeginTime(DateUtil.getTime(nextPeriodOpenTime));
                String HHmmssPart = DateUtil.getDatePart(DateUtil.getTime(nextPeriodOpenTime),"HH")+":"+
                        DateUtil.getDatePart(DateUtil.getTime(nextPeriodOpenTime),"mm")+":"+"00";//拼接日期字符串
                nextPeriodOpenTime = DateUtil.getLotteryTimes(DateUtil.getTime(nextPeriodOpenTime), HHmmssPart);
                System.out.println(DateUtil.getTime(nextPeriodOpenTime));

                createAllBlantGamePeriod(countGap,maxGamePeriod-countGap,nextPeriodOpenTime,closeTimeSet,igameId,endTimes,openTimes);
            }
            if (tomorrowCount == 0){
                System.out.println("生成明日數據");
                //如果今天的都还没有开奖
                if (timeGap == 0 && igameId !=9 ){
                    openTimes =DateUtil.getDateAfterIDay(DateUtil.getTime(openTimes),-1).getTime();
                }
                Long tomorrowTimes = DateUtil.getDateAfterIDay(DateUtil.getTime(openTimes),1).getTime() ;
                endTimes = DateUtil.getDateAfterIDay(DateUtil.getTime(endTimes),1).getTime() ;
                //生成明天的
                //先处理明天的期号
                if (igameId  == 38){//某些彩种不需要
                    maxGamePeriod = handleFirstOnePeriod(maxGamePeriod);
                }
                createAllBlantGamePeriod(totalCount, maxGamePeriod,tomorrowTimes,closeTimeSet,igameId,endTimes,tomorrowTimes);
            }
        }
        }
        //生成完畢以後把所有的時間存入redis中
        List<GamePeriod> periods = gamePeriodMapper.selectAllPeriodNotOpen();
	    for (GamePeriod period : periods){
            String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(period.getDopentime());
            redisUtil.setValue("sechdule:"+period.getSkey(),format
                    , RedisUtil.TWO_DAY_IN_SECONDS);
        }

	}

    /**生成明天的第一期期号
     * @param maxGamePeriod
     * @return
     */
    private Long handleFirstOnePeriod(Long maxGamePeriod) {
        String periodString = maxGamePeriod + "";
        char[] chars = periodString.toCharArray();
        System.out.println(chars);//取出char数组
        String beginNum = "";//前几位
        for (int i =0 ; i< chars.length ; i++){
            if (i < chars.length-3){
                beginNum = beginNum + chars[i];
            }
        }
        System.out.println("beginNum"+beginNum);
        Date yyyyMMdd  = null;
        SimpleDateFormat format = null;
        try {
            format = new SimpleDateFormat("yyyyMMdd");
            yyyyMMdd = format.parse(beginNum);
            yyyyMMdd = DateUtil.getDateAfterIDay(yyyyMMdd, 1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //每天开始的期号
        System.out.println(yyyyMMdd.toLocaleString());
        beginNum = format==null ? null : format.format(yyyyMMdd);
        maxGamePeriod = Long.parseLong(beginNum + "000");
        return maxGamePeriod;
    }

    /**时间格式化
     * @return
     */
    private SimpleDateFormat dateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }



    /**用于生成每天的空数据
     * @param totalCount  需要生成的条数
     * @param maxGamePeriod 开始的期号
     * @param openTimes     每天的开奖时间
     * @param closeTimeSet  每期的时间间隔
     * @param igameId       彩种id
     * @param endTimes      每天的彩种结束时间
     * @return
     */
    private Date  createAllBlantGamePeriod(Integer totalCount , Long maxGamePeriod , Long openTimes, Long closeTimeSet,
                                     Long igameId,Long endTimes,Long startTimes){
        Date startTime = DateUtil.getTime(startTimes);
        Date everyPeriodOpenTime =null;
        Date closeTime = DateUtil.getTime(endTimes);
        System.out.println(closeTime.toLocaleString());
        Long tomorrowMaxPeriod =null;
        if (igameId ==5){
            tomorrowMaxPeriod = handleFirstOnePeriod(maxGamePeriod)+1;
        }
        //生成空的数据
        for (int i=1; i <= totalCount ; i++ ){
            maxGamePeriod++;//期号递增
            GamePeriod gamePeriod = new GamePeriod();
            //设置开始时间
            everyPeriodOpenTime = DateUtil.getTime(openTimes);//今日
            gamePeriod.setDopentime(everyPeriodOpenTime);
            //每次循环就增加这个时间,如从早上九点开始,加5分钟,加178次
            if (igameId==5 ){//区别于重庆时时彩
                //如果大于0时,重庆时时彩的期号重置
                if (DateUtil.getDateBeginTime(DateUtil.getDateAfterIDay(DateUtil.getTime(startTimes),1)).getTime()<=openTimes){
                    maxGamePeriod =maxGamePeriod >tomorrowMaxPeriod ? maxGamePeriod : tomorrowMaxPeriod;
                }
                if(DateUtil.getLotteryTimes(DateUtil.getTime(startTimes),"22:00:00")<=openTimes){
                    openTimes = openTimes+(closeTimeSet/2);
                }else {
                    openTimes = openTimes+closeTimeSet;
                }
            }else {
                    openTimes = openTimes+closeTimeSet;
            }
            //先设置dStartTime
            gamePeriod.setDstarttime(startTime);
            //设置空白数据的属性
            gamePeriod.setSkey(igameId+"."+maxGamePeriod);
            gamePeriod.setIgameid(igameId);
            gamePeriod.setSgameperiod(maxGamePeriod+"");
            gamePeriod.setIstatus(new Short("3"));
            gamePeriod.setIopen(new Short("0"));
            gamePeriod.setImanualopen(new Short("0"));
            gamePeriod.setDclosetime(closeTime);
            gamePeriodMapper.saveOrUpdate(gamePeriod);
        }
        return everyPeriodOpenTime;

    }

}
