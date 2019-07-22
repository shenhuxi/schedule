package com.zpself.scheduling.web.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.zpself.scheduling.data.entity.QrtzJobManage;
import com.zpself.scheduling.data.service.IJobAndTriggerService;
import com.zpself.scheduling.web.dto.DataArray;
import com.zpself.scheduling.web.dto.JobAndTrigger;
import com.zpself.scheduling.web.dto.SingleData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author MingSu
 */
@Api(tags = {"任务调度管理"})
@RestController
@RequestMapping(value = "/api/job")
public class JobController {

    @Autowired
    private IJobAndTriggerService jobAndTriggerService;


    @ApiOperation(value = "添加调度任务")
    @PostMapping(value = "/addjob")
    public SingleData<Boolean> addjob(
            @ApiParam(value = "任务类全名") @RequestParam(value = "jobClassName") String jobClassName,
            @ApiParam(value = "任务组名") @RequestParam(value = "jobGroupName") String jobGroupName,
            @ApiParam(value = "cron表达式")@RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        return new SingleData<>(jobAndTriggerService.addJob(jobClassName, jobGroupName, cronExpression));
    }


    @ApiOperation(value = "暂停调度任务")
    @PostMapping(value = "/pausejob")
    public SingleData<Boolean> pausejob(
            @ApiParam(value = "任务类全名") @RequestParam(value = "jobClassName") String jobClassName,
            @ApiParam(value = "任务组名") @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        return new SingleData<>(jobAndTriggerService.jobPause(jobClassName, jobGroupName));
    }


    @ApiOperation(value = "恢复调度任务")
    @PostMapping(value = "/resumeJob")
    public SingleData<Boolean> jobResume(
            @ApiParam(value = "任务类全名") @RequestParam(value = "jobClassName") String jobClassName,
            @ApiParam(value = "任务组名") @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        return new SingleData<>(jobAndTriggerService.jobResume(jobClassName, jobGroupName));
    }


    @ApiOperation(value = "修改cronExpression")
    @PostMapping(value = "/rescheduleJob")
    public SingleData<Boolean> rescheduleJob(
            @ApiParam(value = "任务类全名") @RequestParam(value = "jobClassName") String jobClassName,
            @ApiParam(value = "任务组名") @RequestParam(value = "jobGroupName") String jobGroupName,
            @ApiParam(value = "cron表达式") @RequestParam(value = "cronExpression") String cronExpression) throws Exception {
        return new SingleData<>(jobAndTriggerService.jobreschedule(jobClassName, jobGroupName, cronExpression));
    }


    @ApiOperation(value = "删除调度任务")
    @PostMapping(value = "/deleteJob")
    public SingleData<Boolean> deleteJob(
            @ApiParam(value = "任务类全名") @RequestParam(value = "jobClassName") String jobClassName,
            @ApiParam(value = "任务组名")  @RequestParam(value = "jobGroupName") String jobGroupName) throws Exception {
        return new SingleData<>(jobAndTriggerService.deleteJob(jobClassName, jobGroupName));
    }


    @ApiOperation(value = "分页查询调度任务")
    @GetMapping(value = "/queryjob")
    public Page<QrtzJobManage> queryjob(
            @ApiParam(value = "页号") @RequestParam(value = "pageNum") Integer pageNum,
            @ApiParam(value = "页面大小") @RequestParam(value = "pageSize") Integer pageSize) {
        Page<QrtzJobManage> jobAndTrigger = jobAndTriggerService.getJobAndTriggerDetails(pageNum, pageSize);
        return jobAndTrigger;
    }

    @ApiOperation(value = "查询所有的任务类全名")
    @GetMapping(value = "/jobClassNameList")
    public DataArray<String> findAllJobClassName() {
        DataArray<String> result = null;
        List<String> instanceNameList = jobAndTriggerService.findAllJobClassName();
        if (instanceNameList != null && instanceNameList.size() > 0) {
            String[] strs = new String[instanceNameList.size()];
            strs = instanceNameList.toArray(strs);
            result = new DataArray<>(strs);
        }
        return result;

    }

}
