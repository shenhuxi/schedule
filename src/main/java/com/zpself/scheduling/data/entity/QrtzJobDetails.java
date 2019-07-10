package com.zpself.scheduling.data.entity;

import com.zpself.scheduling.data.id.SchedNameJobNameGroupPK;
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
@Table(name = "QRTZ_JOB_DETAILS")
@ApiModel(description = "存放一个jobDetail信息")
@Getter
@Setter
public class QrtzJobDetails implements Serializable {

  @EmbeddedId
  @ApiModelProperty(value = "复合主键")
  private SchedNameJobNameGroupPK id;
  @ApiModelProperty(value = "描述")
  @Column(name = "DESCRIPTION")
  private String description;
  @ApiModelProperty(value = "job类名")
  @Column(name = "JOB_CLASS_NAME")
  private String jobClassName;
  @ApiModelProperty(value = "is_urable")
  @Column(name = "IS_DURABLE")
  private String isDurable;
  @ApiModelProperty(value = "is_nonconcurrent")
  @Column(name = "IS_NONCONCURRENT")
  private String isNonconcurrent;
  @ApiModelProperty(value = "is_updateData")
  @Column(name = "IS_UPDATE_DATA")
  private String isUpdateData;
  @ApiModelProperty(value = "requests_recovery")
  @Column(name = "REQUESTS_RECOVERY")
  private String requestsRecovery;
  @ApiModelProperty(value = "job_data")
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "JOB_DATA",columnDefinition="BLOB")
  private String jobData;

}
