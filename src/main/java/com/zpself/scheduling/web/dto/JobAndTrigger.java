package com.zpself.scheduling.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(description = "任务和触发器")
@Getter
@Setter
public class JobAndTrigger {
	@ApiModelProperty(value = "任务名")
	private String jobName;
	@ApiModelProperty(value = "任务组")
	private String jobGroup;
	@ApiModelProperty(value = "任务类名")
	private String jobClassName;
	@ApiModelProperty(value = "触发器名")
	private String triggerName;
	@ApiModelProperty(value = "触发器组")
	private String triggerGroup;
	@ApiModelProperty(value = "触发器状态")
	private String triggerState;
	@ApiModelProperty(value = "cron表达式")
	private String cronExpression;

}
