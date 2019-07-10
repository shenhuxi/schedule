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
@Table(name = "QRTZ_SCHEDULER_STATE")
@ApiModel(description = "调度器状态")
@Getter
@Setter
public class QrtzSchedulerState implements Serializable {

  @Id
  @ApiModelProperty(value = "调度器名")
  @Column(name = "SCHED_NAME")
  private String schedName;
  @Id
  @ApiModelProperty(value = "instance_name")
  @Column(name = "INSTANCE_NAME")
  private String instanceName;
  @ApiModelProperty(value = "last_checkin_time")
  @Column(name = "LAST_CHECKIN_TIME")
  private long lastCheckinTime;
  @ApiModelProperty(value = "checkin_interval")
  @Column(name = "CHECKIN_INTERVAL")
  private long checkinInterval;

}
