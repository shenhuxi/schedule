package com.zpself.scheduling.task;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zengpeng
 * @date 2019/7/6
 */
@Configuration
public class MyJobConfig {

    /**
     *JobDetail
     */
    @Bean
    public JobDetail myJobDetail() {
        return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
    }

    /**
     * Trigger
     */
    @Bean
    public Trigger myJobTrigger() {
        //定义具体什么执行时间.  设置一个每3秒执行一次的计划表.
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(3).repeatForever();
        //定义触发器.
        return TriggerBuilder.newTrigger().forJob(myJobDetail()).withIdentity("myJobTrigger").withSchedule(simpleScheduleBuilder).build();
    }
}
