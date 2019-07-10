package com.zpself.scheduling.data.entity;

import com.zpself.scheduling.data.id.SchedNameTriggerNameGroupPK;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author  MingSu
 */
@Entity
@Table(name = "QRTZ_SIMPLE_TRIGGERS")
@ApiModel(description = "简单触发器的信息")
@Getter
@Setter
public class QrtzSimpleTriggers implements Serializable {

  @EmbeddedId
  @ApiModelProperty(value = "复合主键")
  private SchedNameTriggerNameGroupPK id;
  @ApiModelProperty(value = "repeat_count")
  @Column(name = "REPEAT_COUNT")
  private long repeatCount;
  @ApiModelProperty(value = "repeat_interval")
  @Column(name = "REPEAT_INTERVAL")
  private long repeatInterval;
  @ApiModelProperty(value = "times_triggered")
  @Column(name = "TIMES_TRIGGERED")
  private long timesTriggered;



}
