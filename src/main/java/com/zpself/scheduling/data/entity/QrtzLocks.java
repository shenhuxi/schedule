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
@Table(name = "QRTZ_LOCKS")
@ApiModel(description = " 存储程序的悲观锁的信息(假如使用了悲观锁)")
@Getter
@Setter
public class QrtzLocks implements Serializable {

  @Id
  @ApiModelProperty(value = "调度器名")
  @Column(name = "SCHED_NAME")
  private String schedName;
  @Id
  @ApiModelProperty(value = "锁名")
  @Column(name = "LOCK_NAME")
  private String lockName;

}
