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
@Table(name = "QRTZ_CRON_TRIGGERS")
@ApiModel(description = "存放cron类型的触发器")
@Getter
@Setter
public class QrtzCronTriggers implements Serializable {

  @EmbeddedId
  @ApiModelProperty(value = "复合主键")
  private SchedNameTriggerNameGroupPK id;
  @ApiModelProperty(value = "cron表达式")
  @Column(name = "CRON_EXPRESSION")
  private String cronExpression;
  @ApiModelProperty(value = "time_zone_id")
  @Column(name = "TIME_ZONE_ID")
  private String timeZoneId;


}
