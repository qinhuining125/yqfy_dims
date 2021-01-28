package com.hengtianyi.dims.service.entity;

import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * TaskInfo实体类
 * <p>Table: YQFK</p>
 *
 * @author LY
 */
public class YqfkEntity extends BaseEntity {

    private static final long serialVersionUID = -7528738187238993843L;
    private String id;
    //   X姓名
    private String name;
    //性别
    private String sex;
    //  年龄
    private int age;
    //身份证
    private int card;
    //户籍省编码
    private String hjPbm;
    //户籍市编码
    private String hjCbm;
    //户籍县编码
    private String hjXbm;
    //  户籍(全部信息汉字)
    private String hj;
    //  是否常驻
    private byte sfcz;
    //  与户主关系1:父母 2:子女3:配偶 4:其他
    private byte relation;
    //  联系电话
    private String phone;
    //  工作单位
    private String workSchool;
    //  人员类别1:冷链从业人员2:商业从业人员3:货运物流4：学生5:机关事业单位6:其它7:无业
    private byte industray;
    //  是否返乡 0：已返乡 1:拟返乡
    private byte returnState;
    //  返乡日期
    private Date returnTime;
    // 返乡方式1:自驾 2:飞机 3:火车 4:客车 5:网约车
    private byte returnWay;
    //  返乡车牌号
    private String returnCarnum;
    //  拟返乡日期
    private Date expReturnTime;
    //   拟返乡方式1:自驾 2:飞机 3:火车 4:客车 5:网约车
    private byte expReturnWay;
    //  拟返乡车牌号
    private String expReturnCarnum;
    //  返乡前省编码
    private String beforeReturnPbm;
    //  返乡前市编码
    private String beforeReturnCbm;
    //  返乡前县编码
    private String beforeReturnXbm;
    //  返乡前住址，全信息
    private String beforeReturnAddress;
    //  返乡后省编码
    private String afterReturnPbm;
    //  返乡后市编码
    private String afterReturnCbm;
    //  返乡后县编码
    private String afterReturnXbm;
    //  返乡后镇编码
    private String afterReturnZhbm;
    //  返乡后村编码
    private String afterReturnCubm;
    //  返乡后住址，全信息
    private String afterReturnAddress;
    //  是否本地租户 0:否 1:是
    private byte localState;
    //  核酸检测日期
    private Date natTime;
    //  核酸检测结果 0:阴性 1:阳性
    private byte natResult;
    //  是否接触。0:否 1:是
    private byte touchState;
    //  是否居家隔离。0:否 1:是
    private byte isLateStete;
    //  隔离开始日期
    private Date isLateStateTime;
    //  当前健康状态是否异常。0:否 1:是
    private byte healthState;
    //  异常说明
    private String remark;
    // 录入信息的网格员账号
    private String createAccount;
    // 创建时间
    private Date createTime;
    //  更新时间
    private Date updateTime;
    //  更改信息的网格员账号
    private String updateAccount;

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }

    public String getHjPbm() {
        return hjPbm;
    }

    public void setHjPbm(String hjPbm) {
        this.hjPbm = hjPbm;
    }

    public String getHjCbm() {
        return hjCbm;
    }

    public void setHjCbm(String hjCbm) {
        this.hjCbm = hjCbm;
    }

    public String getHjXbm() {
        return hjXbm;
    }

    public void setHjXbm(String hjXbm) {
        this.hjXbm = hjXbm;
    }

    public String getHj() {
        return hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public byte getSfcz() {
        return sfcz;
    }

    public void setSfcz(byte sfcz) {
        this.sfcz = sfcz;
    }

    public byte getRelation() {
        return relation;
    }

    public void setRelation(byte relation) {
        this.relation = relation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWorkSchool() {
        return workSchool;
    }

    public void setWorkSchool(String workSchool) {
        this.workSchool = workSchool;
    }

    public byte getIndustray() {
        return industray;
    }

    public void setIndustray(byte industray) {
        this.industray = industray;
    }

    public byte getReturnState() {
        return returnState;
    }

    public void setReturnState(byte returnState) {
        this.returnState = returnState;
    }

    public Date getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    public byte getReturnWay() {
        return returnWay;
    }

    public void setReturnWay(byte returnWay) {
        this.returnWay = returnWay;
    }

    public String getReturnCarnum() {
        return returnCarnum;
    }

    public void setReturnCarnum(String returnCarnum) {
        this.returnCarnum = returnCarnum;
    }

    public Date getExpReturnTime() {
        return expReturnTime;
    }

    public void setExpReturnTime(Date expReturnTime) {
        this.expReturnTime = expReturnTime;
    }

    public byte getExpReturnWay() {
        return expReturnWay;
    }

    public void setExpReturnWay(byte expReturnWay) {
        this.expReturnWay = expReturnWay;
    }

    public String getExpReturnCarnum() {
        return expReturnCarnum;
    }

    public void setExpReturnCarnum(String expReturnCarnum) {
        this.expReturnCarnum = expReturnCarnum;
    }

    public String getBeforeReturnPbm() {
        return beforeReturnPbm;
    }

    public void setBeforeReturnPbm(String beforeReturnPbm) {
        this.beforeReturnPbm = beforeReturnPbm;
    }

    public String getBeforeReturnCbm() {
        return beforeReturnCbm;
    }

    public void setBeforeReturnCbm(String beforeReturnCbm) {
        this.beforeReturnCbm = beforeReturnCbm;
    }

    public String getBeforeReturnXbm() {
        return beforeReturnXbm;
    }

    public void setBeforeReturnXbm(String beforeReturnXbm) {
        this.beforeReturnXbm = beforeReturnXbm;
    }

    public String getBeforeReturnAddress() {
        return beforeReturnAddress;
    }

    public void setBeforeReturnAddress(String beforeReturnAddress) {
        this.beforeReturnAddress = beforeReturnAddress;
    }

    public String getAfterReturnPbm() {
        return afterReturnPbm;
    }

    public void setAfterReturnPbm(String afterReturnPbm) {
        this.afterReturnPbm = afterReturnPbm;
    }

    public String getAfterReturnCbm() {
        return afterReturnCbm;
    }

    public void setAfterReturnCbm(String afterReturnCbm) {
        this.afterReturnCbm = afterReturnCbm;
    }

    public String getAfterReturnXbm() {
        return afterReturnXbm;
    }

    public void setAfterReturnXbm(String afterReturnXbm) {
        this.afterReturnXbm = afterReturnXbm;
    }

    public String getAfterReturnZhbm() {
        return afterReturnZhbm;
    }

    public void setAfterReturnZhbm(String afterReturnZhbm) {
        this.afterReturnZhbm = afterReturnZhbm;
    }

    public String getAfterReturnCubm() {
        return afterReturnCubm;
    }

    public void setAfterReturnCubm(String afterReturnCubm) {
        this.afterReturnCubm = afterReturnCubm;
    }

    public String getAfterReturnAddress() {
        return afterReturnAddress;
    }

    public void setAfterReturnAddress(String afterReturnAddress) {
        this.afterReturnAddress = afterReturnAddress;
    }

    public byte getLocalState() {
        return localState;
    }

    public void setLocalState(byte localState) {
        this.localState = localState;
    }

    public Date getNatTime() {
        return natTime;
    }

    public void setNatTime(Date natTime) {
        this.natTime = natTime;
    }

    public byte getNatResult() {
        return natResult;
    }

    public void setNatResult(byte natResult) {
        this.natResult = natResult;
    }

    public byte getTouchState() {
        return touchState;
    }

    public void setTouchState(byte touchState) {
        this.touchState = touchState;
    }

    public byte getIsLateStete() {
        return isLateStete;
    }

    public void setIsLateStete(byte isLateStete) {
        this.isLateStete = isLateStete;
    }

    public Date getIsLateStateTime() {
        return isLateStateTime;
    }

    public void setIsLateStateTime(Date isLateStateTime) {
        this.isLateStateTime = isLateStateTime;
    }

    public byte getHealthState() {
        return healthState;
    }

    public void setHealthState(byte healthState) {
        this.healthState = healthState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(String updateAccount) {
        this.updateAccount = updateAccount;
    }
}
