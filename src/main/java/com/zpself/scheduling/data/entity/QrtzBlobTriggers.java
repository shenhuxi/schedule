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
@Table(name = "QRTZ_BLOB_TRIGGERS")
@ApiModel(description = "以Blob 类型存储的触发器")
@Getter
@Setter
public class QrtzBlobTriggers implements Serializable {

  @EmbeddedId
  @ApiModelProperty(value = "复合主键")
  private SchedNameTriggerNameGroupPK id;
  @ApiModelProperty(value = "BLOB_DATA")
  @Lob
  @Basic(fetch = FetchType.LAZY)
  @Column(name = "BLOB_DATA",columnDefinition="BLOB")
  private String blobData;


}
