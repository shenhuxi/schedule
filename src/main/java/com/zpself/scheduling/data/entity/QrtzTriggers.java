package com.zpself.scheduling.data.entity;

import com.zpself.scheduling.data.id.SchedNameTriggerNameGroupPK;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author  MingSu
 */
@Entity
@Table(name = "QRTZ_TRIGGERS")
@ApiModel(description = "触发器的基本信息")
@Getter
@Setter
public class QrtzTriggers implements Serializable {
  @EmbeddedId
  @ApiModelProperty(value = "复合主键")
  private SchedNameTriggerNameGroupPK id;
  @ApiModelProperty(value = "job名")
  @Column(name = "JOB_NAME")
  private String jobName;
  @ApiModelProperty(value = "job组")
  @Column(name = "JOB_GROUP")
  private String jobGroup;
  @ApiModelProperty(value = "描述")
  @Column(name = "DESCRIPTION")
  private String description;
  @ApiModelProperty(value = "next_fire_time")
  @Column(name = "NEXT_FIRE_TIME")
  private long nextFireTime;
  @ApiModelProperty(value = "prev_fire_time")
  @Column(name = "PREV_FIRE_TIME")
  private long prevFireTime;
  @ApiModelProperty(value = "priority")
  @Column(name = "PRIORITY")
  private Integer priority;
  @ApiModelProperty(value = "trigger_state")
  @Column(name = "TRIGGER_STATE")
  private String triggerState;
  @ApiModelProperty(value = "trigger_type")
  @Column(name = "TRIGGER_TYPE")
  private String triggerType;
  @ApiModelProperty(value = "start_time")
  @Column(name = "START_TIME")
  private long startTime;
  @ApiModelProperty(value = "end_time")
  @Column(name = "END_TIME")
  private long endTime;
  @ApiModelProperty(value = "calendar_name")
  @Column(name = "CALENDAR_NAME")
  private String calendarName;
  @ApiModelProperty(value = "misfire_instr")
  @Column(name = "MISFIRE_INSTR")
  private short misfireInstr;
  @ApiModelProperty(value = "job_data")
  @Column(name = "JOB_DATA",columnDefinition="BLOB")
  @Lob
  @Basic(fetch = FetchType.LAZY)
  private String jobData;



}
