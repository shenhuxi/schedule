package com.zpself.scheduling.data.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zpself.scheduling.data.repository.QrtzTriggersRepository;
import com.zpself.scheduling.data.service.IJobAndTriggerService;
import com.zpself.scheduling.data.util.ClassScanner;
import com.zpself.scheduling.web.dto.JobAndTrigger;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author MingSu
 * @date 2018-08-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobAndTriggerServiceImpl implements IJobAndTriggerService {

    @Autowired
    private QrtzTriggersRepository qrtzTriggersRepository;
    /**
     * 加入Qulifier注解，通过名称注入bean
     */
    @Autowired
    @Qualifier("scheduler")
    private Scheduler scheduler;

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 页面大小
     * @return 返回值
     */
    @Override
    public PageInfo<JobAndTrigger> getJobAndTriggerDetails(int pageNum, int pageSize) {
        List<JobAndTrigger> list = new ArrayList<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Object[]> result = qrtzTriggersRepository.getJobAndTriggerDetails();
        for (Object[] obj : result) {
            JobAndTrigger jobAndTrigger = new JobAndTrigger();
            jobAndTrigger.setJobName(String.valueOf(obj[0]));
            jobAndTrigger.setJobGroup(String.valueOf(obj[1]));
            jobAndTrigger.setJobClassName(String.valueOf(obj[2]));
            jobAndTrigger.setCronExpression(String.valueOf(obj[3]));
            jobAndTrigger.setTriggerName(String.valueOf(obj[4]));
            jobAndTrigger.setTriggerGroup(String.valueOf(obj[5]));
            jobAndTrigger.setTriggerState(String.valueOf(obj[6]));
            list.add(jobAndTrigger);
        }

        return new PageInfo<>(list);
    }

    /**
     * 添加调度任务
     *
     * @param jobClassName   任务类名
     * @param jobGroupName   任务组名
     * @param cronExpression cron表达式
     * @return Boolean
     */
    @Override
    public Boolean addJob(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            // 启动调度器
            scheduler.start();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(jobClassName).getClass()).withIdentity(jobClassName, jobGroupName).build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 暂停调度任务
     *
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @return Boolean
     */
    @Override
    public Boolean jobPause(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobClassName, jobGroupName));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 恢复调度任务
     *
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @return Boolean
     */
    @Override
    public Boolean jobresume(String jobClassName, String jobGroupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName, jobGroupName));
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 修改cronExpression
     *
     * @param jobClassName   任务类名
     * @param jobGroupName   任务组名
     * @param cronExpression cron表达式
     * @return Boolean
     */
    @Override
    public Boolean jobreschedule(String jobClassName, String jobGroupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName, jobGroupName);
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 删除调度任务
     *
     * @param jobClassName 任务类名
     * @param jobGroupName 任务组名
     * @return Boolean
     */
    @Override
    public Boolean jobdelete(String jobClassName, String jobGroupName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(jobClassName, jobGroupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(jobClassName, jobGroupName));
            return scheduler.deleteJob(JobKey.jobKey(jobClassName, jobGroupName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询所有的任务类全名
     * @param jobClassDir 任务类目录
     * @return 任务类全名集合
     */
    @Override
    public List<String> findAllJobClassName(String jobClassDir) {
       /* String path ="";
        if(StringUtils.isNotBlank(jobClassDir)){
            path=jobClassDir.replace(".","/");
            path=this.getClass().getResource("/").getPath()+path;
        }
        return this.getFileNameList(jobClassDir,path);*/
       List<String> result=new ArrayList<>();
        ClassScanner classScanner=new ClassScanner();
        Set<Class<?>> classes = classScanner.doScan(jobClassDir);
        if(classes!=null&&classes.size()>0){
            for(Class clazz: classes){
                String name = clazz.getName();
                if(StringUtils.isNotBlank(name)){
                    result.add(name);
                }
            }
        }
        return result;
    }


    /**
     * 根据job类名创建job类对象
     * @param classname  job类名
     * @return BaseJob
     */
    private static QuartzJobBean getClass(String classname) {
        try {
            Class<?> class1 = Class.forName(classname);
            return (QuartzJobBean) class1.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 输出指定文件夹内的所有文件
     * @param path 路径
     */
    private  ArrayList<String> getFileNameList(String jobClassDir,String path){
        ArrayList<String> result=new ArrayList<>();
        // 获得指定文件对象
        File file = new File(path);
        if(file.exists()&&file.isDirectory()){
            // 获得该文件夹内的所有文件
            File[] array = file.listFiles();
            if(array!=null&& array.length>0){
                for(int i=0;i<array.length;i++){
                    //如果是文件
                    if(array[i].isFile()) {
                        // 只输出文件名字
                        System.out.println( array[i].getName());
                        result.add(jobClassDir+"."+array[i].getName().replace(".class",""));
                    }
                }
            }
        }
        return result;
    }

}