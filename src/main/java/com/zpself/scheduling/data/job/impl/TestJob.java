package com.zpself.scheduling.data.job.impl;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * spring是将bean放在ApplicationContext中的。而quartz初始化是自己的JobContext，
 *  不同于Spring的ApplicationContext，所以无法直接注入
 * @author MingSu
 */
@Slf4j
public class TestJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context) {
		try{
			//实际业务
			System.out.println("*(``)*=======啦啦，这是实际业务");

		}catch (Exception e){
			throw new RuntimeException("任务方法异常："+e);
		}
	}
}

