package com.zpself.scheduling.data.service;


import com.github.pagehelper.PageInfo;
import com.zpself.scheduling.web.dto.JobAndTrigger;

import java.util.List;

public interface IJobAndTriggerService {
    /**
     * 分页查询
     * @param pageNum 页号
     * @param pageSize 页面大小
     * @return 返回值
     */
	 PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize);

    /**
     * 添加调度任务
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @param cronExpression cron表达式
     * @throws Exception 异常
     */
    Boolean addJob(String jobClassName, String jobGroupName, String cronExpression)throws Exception;

    /**
     * 暂停调度任务
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @throws Exception 异常
     */
    Boolean jobPause(String jobClassName, String jobGroupName) throws Exception;
    /**
     * 恢复调度任务
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @throws Exception 异常
     */
    Boolean jobresume(String jobClassName, String jobGroupName) throws Exception;
    /**
     * 修改cronExpression
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @param cronExpression  cron表达式
     * @throws Exception 异常
     */
    Boolean jobreschedule(String jobClassName, String jobGroupName, String cronExpression) throws Exception;
    /**
     * 删除调度任务
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @throws Exception 异常
     */
    Boolean jobdelete(String jobClassName, String jobGroupName) throws Exception;

    /**
     * 查询所有的任务类全名
     * @param jobClassDir 任务类目录
     * @return 任务类全名集合
     */
    List<String> findAllJobClassName(String jobClassDir);
}
