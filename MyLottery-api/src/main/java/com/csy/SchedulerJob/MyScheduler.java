package com.csy.SchedulerJob;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {


	/**执行定时任务
	 * @throws SchedulerException
	 */
	public void schedulerJob() throws SchedulerException {
        ApplicationContext annotationContext = SpringUtil.getApplicationContext();
		StdScheduler stdScheduler = (StdScheduler) annotationContext.getBean("mySchedulerFactoryBean");//获得上面创建的bean
		Scheduler myScheduler =stdScheduler;
		startScheduler2(myScheduler);
		myScheduler.start();
	}


	/**Job2定时任务细节
	 * @param scheduler
	 * @throws SchedulerException
	 * corn表达式: "0 * 17 * * ?"//每天5点
	 */
	public void startScheduler2(Scheduler scheduler) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(MySchedulerJob2.class).withIdentity("job2", "jobGroup2").build();
		CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 30 7 * * ?");
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "triggerGroup2")
				.withSchedule(cronScheduleBuilder).build();
		scheduler.scheduleJob(jobDetail, trigger);
 
	}
 
}
