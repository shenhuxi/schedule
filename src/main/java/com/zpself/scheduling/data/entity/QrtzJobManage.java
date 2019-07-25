package com.zpself.scheduling.data.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author  zengpeng
 */
@Entity
@Table(name = "QRTZ_JOB_MANAGE")
@ApiModel(description = "存放触发器")
@Getter
@Setter
public class QrtzJobManage implements Serializable {

  /**
   * 主键，新增时应当为null，受限javascript的long型数据精度问题，DTO中需转换为字符串类型
   */
  @Id
  @GeneratedValue(generator = "snowflake")
  @GenericGenerator(name = "snowflake", strategy = "com.matech.framework.spring.jpa.SnowflakeIdGenerator")
  private Long id;



  @ApiModelProperty(value = "触发时间")
  @Column(name = "FIRED_TIME")
  private long firedTime;

  @ApiModelProperty(value = "job名称")
  @Column(name = "JOB_NAME")
  private String jobName;

  @ApiModelProperty(value = "job组")
  @Column(name = "JOB_GROUP")
  private String jobGroup;

  @ApiModelProperty(value = "cron表达式;")
  @Column(name = "CRON_EXPRESSION")
  private String cronExpression;

  @ApiModelProperty(value = "状态")
  @Column(name = "STATE")
  private String state;

}
