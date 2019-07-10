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
@Table(name = "QRTZ_SIMPROP_TRIGGERS")
@ApiModel(description = "批量附件模板")
@Getter
@Setter
public class QrtzSimpropTriggers implements Serializable {

  @EmbeddedId
  @ApiModelProperty(value = "复合主键")
  private SchedNameTriggerNameGroupPK id;
  @ApiModelProperty(value = "str_prop_1")
  @Column(name = "STR_PROP_1")
  private String strProp1;
  @ApiModelProperty(value = "str_prop_2")
  @Column(name = "STR_PROP_2")
  private String strProp2;
  @ApiModelProperty(value = "str_prop_3")
  @Column(name = "STR_PROP_3")
  private String strProp3;
  @ApiModelProperty(value = "int_prop_1")
  @Column(name = "INT_PROP_1")
  private long intProp1;
  @ApiModelProperty(value = "int_prop_2")
  @Column(name = "INT_PROP_2")
  private long intProp2;
  @ApiModelProperty(value = "long_prop_1")
  @Column(name = "LONG_PROP_1")
  private long longProp1;
  @ApiModelProperty(value = "long_prop_2")
  @Column(name = "LONG_PROP_2")
  private long longProp2;
  @ApiModelProperty(value = "dec_prop_1")
  @Column(name = "DEC_PROP_1")
  private double decProp1;
  @ApiModelProperty(value = "dec_prop_2")
  @Column(name = "DEC_PROP_2")
  private double decProp2;
  @ApiModelProperty(value = "bool_prop_1")
  @Column(name = "BOOL_PROP_1")
  private String boolProp1;
  @ApiModelProperty(value = "bool_prop_2")
  @Column(name = "BOOL_PROP_2")
  private String boolProp2;


}
