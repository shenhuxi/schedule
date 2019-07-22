package com.zpself.scheduling.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author  MingSu
 */
@Entity
@Table(name = "QRTZ_FIRED_TRIGGERS")
@ApiModel(description = "存放已触发的触发器")
@Getter
@Setter
public class QrtzFiredTriggers implements Serializable {

  @Id
  @ApiModelProperty(value = "调度器名")
  @Column(name = "SCHED_NAME")
  private String schedName;

  @Id
  @ApiModelProperty(value = "entry_id")
  @Column(name = "ENTRY_ID")
  private String entryId;

  @ApiModelProperty(value = "触发器名")
  @Column(name = "TRIGGER_NAME")
  private String triggerName;

  @ApiModelProperty(value = "触发器组")
  @Column(name = "TRIGGER_GROUP")
  private String triggerGroup;

  @ApiModelProperty(value = "instance_name")
  @Column(name = "INSTANCE_NAME")
  private String instanceName;

  @ApiModelProperty(value = "fired_time")
  @Column(name = "FIRED_TIME")
  private long firedTime;

  @ApiModelProperty(value = "sched_time")
  @Column(name = "SCHED_TIME")
  private long schedTime;

  @ApiModelProperty(value = "priority")
  @Column(name = "PRIORITY")
  private long priority;

  @ApiModelProperty(value = "状态")
  @Column(name = "STATE")
  private String state;

  @ApiModelProperty(value = "job名称")
  @Column(name = "JOB_NAME")
  private String jobName;

  @ApiModelProperty(value = "job组")
  @Column(name = "JOB_GROUP")
  private String jobGroup;

  @ApiModelProperty(value = "is_nonconcurrent")
  @Column(name = "IS_NONCONCURRENT")
  private String isNonconcurrent;

  @ApiModelProperty(value = "requests_recovery")
  @Column(name = "REQUESTS_RECOVERY")
  private String requestsRecovery;


}
