package com.zpself.scheduling.data.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zpself.scheduling.data.entity.QrtzJobManage;
import com.zpself.scheduling.data.util.ApplicationContextUtil;
import com.zpself.scheduling.data.job.JobDetailInfo;
import com.zpself.scheduling.data.repository.QrtzJobManageRepository;
import com.zpself.scheduling.data.service.IJobAndTriggerService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author MingSu
 * @date 2018-08-29
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JobAndTriggerServiceImpl implements IJobAndTriggerService {

    private final QrtzJobManageRepository qrtzJobManageRepository;
    /**
     * 加入 Qualifier 注解，通过名称注入bean
     */
    private final Scheduler scheduler;

    @Autowired
    public JobAndTriggerServiceImpl(QrtzJobManageRepository qrtzJobManageRepository, @Qualifier("scheduler") Scheduler scheduler) {
        this.qrtzJobManageRepository = qrtzJobManageRepository;
        this.scheduler = scheduler;
    }

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 页面大小
     * @return 返回值
     */
    @Override
    public Page<QrtzJobManage> getJobAndTriggerDetails(int pageNum, int pageSize) {
        Page<Object> objects = PageHelper.startPage(pageNum, pageSize);
        List all = qrtzJobManageRepository.findAll();
       return null;
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
            QrtzJobManage job = new QrtzJobManage();
            job.setFiredTime(System.currentTimeMillis());
            job.setJobName(jobClassName);
            job.setCronExpression(cronExpression);
            job.setJobGroup(jobGroupName);
            job.setState("NORMAL");
            qrtzJobManageRepository.save(job);
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
    public Boolean jobResume(String jobClassName, String jobGroupName) {
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
    public Boolean deleteJob(String jobClassName, String jobGroupName) {
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
     * @return 任务类全名集合
     */
    @Override
    public List<String> findAllJobClassName() {
        Map<String, JobDetailInfo> beansOfType = ApplicationContextUtil.getBeansOfType(JobDetailInfo.class);
        List<String> result=new ArrayList<>();
        if(beansOfType!=null && ! beansOfType.isEmpty()){
            for (Map.Entry<String, JobDetailInfo> entry : beansOfType.entrySet()) {
                result.add(entry.getValue().getClass().getName());
                System.out.println(entry.toString()+":"+entry.getValue().getClass());
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