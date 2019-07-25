package com.zpself.scheduling.data.job;

import com.zpself.scheduling.data.util.ApplicationContextUtil;
import com.zpself.scheduling.data.util.JobAutoLoginUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author MingSu
 */

@Slf4j
public  abstract class BaseJob extends QuartzJobBean {

	/**
	 * @param context context
	 */

	@Override
	protected void executeInternal(JobExecutionContext context) {

		Object obj = ApplicationContextUtil.getBean("jobAutoLoginUtil");
		if(obj!=null){
			JobAutoLoginUtil jobAutoLoginUtil = (JobAutoLoginUtil)obj;
			jobAutoLoginUtil.login();
		}
		doExecute(context);
	}

	protected abstract void doExecute(JobExecutionContext context);
}

