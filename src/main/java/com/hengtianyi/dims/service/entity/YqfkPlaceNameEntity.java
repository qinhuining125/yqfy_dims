package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * YqfkPlaceNameEntity
 * <p>Table: YqfkPlaceNameEntity</p>
 *
 * @author LY
 */
public class YqfkPlaceNameEntity extends BaseEntity {

  private static final long serialVersionUID = -2842431694883319857L;
  private String a;
  private String b;
  private String c;

  public String getA() {
    return a;
  }

  public void setA(String a) {
    this.a = a;
  }

  public String getB() {
    return b;
  }

  public void setB(String b) {
    this.b = b;
  }

  public String getC() {
    return c;
  }

  public void setC(String c) {
    this.c = c;
  }

  @Override
  public String toString() {
    return "YqfkPlaceNameEntity";
  }
}
