package com.zpself.scheduling.data.job.impl;

import com.zpself.scheduling.data.job.BaseJob;
import com.zpself.scheduling.data.job.JobDetailInfo;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;

/**
 * spring是将bean放在ApplicationContext中的。而quartz初始化是自己的JobContext，
 *  不同于Spring的ApplicationContext，所以无法直接注入
 * @author MingSu
 */
@Slf4j
@Component
public class TestJobTwo extends BaseJob implements JobDetailInfo {

	@Override
	protected void doExecute(JobExecutionContext context) {
		try{
			//实际业务
			System.out.println("这里输出--任务二的调用.....");

		}catch (Exception e){
			throw new RuntimeException("任务方法异常："+e);
		}
	}

	@Override
	public String getName() {
		return "测试定时器类";
	}
}

