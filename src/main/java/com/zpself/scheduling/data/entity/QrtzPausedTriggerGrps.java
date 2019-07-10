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
@Table(name = "QRTZ_PAUSED_TRIGGER_GRPS")
@ApiModel(description = "存放暂停掉的触发器")
@Getter
@Setter
public class QrtzPausedTriggerGrps implements Serializable {

  @Id
  @ApiModelProperty(value = "调度器名")
  @Column(name = "SCHED_NAME")
  private String schedName;
  @Id
  @ApiModelProperty(value = "触发器组")
  @Column(name = "TRIGGER_GROUP")
  private String triggerGroup;

}
