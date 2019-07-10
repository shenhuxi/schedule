package com.zpself.scheduling.data.entity;

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
@Table(name = "QRTZ_CALENDARS")
@ApiModel(description = "存放日历信息， quartz可配置一个日历来指定一个时间范围")
@Getter
@Setter
public class QrtzCalendars implements Serializable {

  @Id
  @ApiModelProperty(value = "调度器名")
  @Column(name = "SCHED_NAME")
  private String schedName;
  @Id
  @ApiModelProperty(value = "calendar_name")
  @Column(name = "CALENDAR_NAME")
  private String calendarName;
  @ApiModelProperty(value = "CALENDAR")
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "CALENDAR",columnDefinition="BLOB")
  private String calendar;


}
