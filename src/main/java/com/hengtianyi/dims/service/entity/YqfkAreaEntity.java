package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;

/**
 * TaskInfo实体类
 * <p>Table: YQFK</p>
 *
 * @author LY
 */
public class YqfkAreaEntity extends BaseEntity {

    private static final long serialVersionUID = -7528738187238993843L;

    //  区域
    private String areacode;
    //  区域名称
    private String area;
    //父CODE
    private String parent;
    //  级别
    private String level;
    //    是否高风险，0:否。1:是
    private byte highflage;

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public byte getHighflage() {
        return highflage;
    }

    public void setHighflage(byte highflage) {
        this.highflage = highflage;
    }
}
