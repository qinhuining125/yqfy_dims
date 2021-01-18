package com.hengtianyi.dims.service.dto;

import com.hengtianyi.common.core.base.BaseBean;

/**
 * 省市县前端接口
 *
 * @author LY
 */
public class RegionDto extends BaseBean {

  private static final long serialVersionUID = -117237146431434827L;
  /**
   * 省
   */
  private String pcode;

  /**
   * 市
   */
  private String ccode;

  /**
   * 县
   */
  private String xcode;

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
}
