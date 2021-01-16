package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;

/**
 * TaskInfo实体类
 * <p>Table: YQFK</p>
 *
 * @author LY
 */
public class YqfkVillageEntity extends BaseEntity {

    private static final long serialVersionUID = -7528738187238993843L;
//    镇村id
    private String id;
    //  镇村code
    private String areacode;
    //镇村名称
    private String area;
    //  父code
    private String parent;
    //  级别
    private String level;

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
}
