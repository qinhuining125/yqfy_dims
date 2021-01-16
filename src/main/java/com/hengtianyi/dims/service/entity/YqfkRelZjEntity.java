package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;

/**
 * TaskInfo实体类
 * <p>Table: YQFK</p>
 *
 * @author LY
 */
public class YqfkRelZjEntity extends BaseEntity {

    private static final long serialVersionUID = -7528738187238993843L;
    private String id;
    //  省编码
    private String Pcode;
    //  市编码
    private String Ccode;
    //  县编码
    private String Xcode;
    //YQFK表中的ID
    private String YQID;
    //  创建时间
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPcode() {
        return Pcode;
    }

    public void setPcode(String pcode) {
        Pcode = pcode;
    }

    public String getCcode() {
        return Ccode;
    }

    public void setCcode(String ccode) {
        Ccode = ccode;
    }

    public String getXcode() {
        return Xcode;
    }

    public void setXcode(String xcode) {
        Xcode = xcode;
    }

    public String getYQID() {
        return YQID;
    }

    public void setYQID(String YQID) {
        this.YQID = YQID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
