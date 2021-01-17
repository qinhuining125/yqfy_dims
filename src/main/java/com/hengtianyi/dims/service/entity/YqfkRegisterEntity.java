package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;

/**
 * IncorruptAdvice实体类
 * <p>Table: incorrupt_advice</p>
 *
 * @author LY
 */
public class YqfkRegisterEntity extends BaseEntity {

  private static final long serialVersionUID = -2842431694883319857L;
  private String id;

  /**
   * 用户Id
   */
  private String name;

  /**
   * 建议内容
   */
  private String sex;

  private String crateAccount;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getCrateAccount() {
    return crateAccount;
  }

  public void setCrateAccount(String crateAccount) {
    this.crateAccount = crateAccount;
  }

  @Override
  public String toString() {
    return "YqfkRegisterEntity";
  }
}
