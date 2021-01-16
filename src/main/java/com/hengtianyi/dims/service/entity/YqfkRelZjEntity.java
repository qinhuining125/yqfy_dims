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
    //  区域编码，具体到县
    private String areacode;
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

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
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
