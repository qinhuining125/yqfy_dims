package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * YqfkPlace实体类
 * <p>Table: YqfkPlace</p>
 *
 * @author LY
 */
public class YqfkPlaceEntity extends BaseEntity {


  private static final long serialVersionUID = -7528738187238993843L;

  private String id;
  private String pcode;
  private String ccode;
  private String xcode;
  private String yqid;
  private Date createTime;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPcode() {
    return pcode;
  }

  public void setPcode(String pcode) {
    this.pcode = pcode;
  }

  public String getCcode() {
    return ccode;
  }

  public void setCcode(String ccode) {
    this.ccode = ccode;
  }

  public String getXcode() {
    return xcode;
  }

  public void setXcode(String xcode) {
    this.xcode = xcode;
  }

  public String getYqid() {
    return yqid;
  }

  public void setYqid(String yqid) {
    this.yqid = yqid;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public YqfkPlaceEntity() {
  }

  @Override
  public String toString() {
    return "YqfkPlaceEntity";
  }
}
