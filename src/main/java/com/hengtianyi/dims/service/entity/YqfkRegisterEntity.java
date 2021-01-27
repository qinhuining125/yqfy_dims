package com.hengtianyi.dims.service.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.hengtianyi.common.core.base.BaseEntity;
import org.springframework.security.core.Transient;

import java.util.Date;
import java.util.List;

/**
 * YqfkRegisterEntity实体类
 * <p>Table: YqfkRegisterEntity</p>
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

  private String age;
  //身份证
  private String card;
  //户籍省编码
  private String hjPbm;
  //户籍市编码
  private String hjCbm;
  //户籍县编码
  private String hjXbm;
  //户籍(全部信息汉字)
  private String hj;
  // 是否常驻
  private String sfcz;
  //  与户主关系1:父母 2:子女3:配偶 4:其他
  private String relation;
  //  联系电话
  private String phone;
  //  工作单位
  private String workSchool;
  //  行业1:冷链从业人员2:商业从业人员3:货运物流4：学生5:机关事业单位6:其它7:无业
  private String industray;
  //  是否返乡 0：已返乡 1:拟返乡
  private String returnState;
  //  返乡日期
  @JSONField(format = "yyyy-MM-dd")
  private Date returnTime;
  // 返乡方式1:自驾 2:飞机 3:火车 4:客车 5:网约车
  private String returnWay;
  //  返乡车牌号
  private String returnCarnum;
  //  拟返乡日期
  @JSONField(format = "yyyy-MM-dd")
  private Date expReturnTime;
  //   拟返乡方式1:自驾 2:飞机 3:火车 4:客车 5:网约车
  private String expReturnWay;
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
  private String localState;
  //  核酸检测日期
  @JSONField(format = "yyyy-MM-dd")
  private Date natTime;
  //  核酸检测结果 0:阴性 1:阳性
  private String natResult;
  //  是否接触。0:否 1:是
  private String touchState;
  //  是否居家隔离。0:否 1:是
  private String isLateStete;
  //  隔离开始日期
  @JSONField(format = "yyyy-MM-dd")
  private Date isLateStateTime;
  //  当前健康状态是否异常。0:否 1:是
  private String healthState;
  //  异常说明
  private String remark;
  // 录入信息的网格员账号
  private String createAccount;
  //添加风险等级
  private String riskLevel;
  //添加生日
  private Date birthday;

  public String getAreaCode() {
    return areaCode;
  }

  public void setAreaCode(String areaCode) {
    this.areaCode = areaCode;
  }

  //判断管理员所属
  private String areaCode;

  //创建者所属乡镇
  private String createBelZhbm;
  //创建者所属村
  private String createBelCubm;

  public String getCreateBelZhbm() {
    return createBelZhbm;
  }

  public void setCreateBelZhbm(String createBelZhbm) {
    this.createBelZhbm = createBelZhbm;
  }

  public String getCreateBelCubm() {
    return createBelCubm;
  }

  public void setCreateBelCubm(String createBelCubm) {
    this.createBelCubm = createBelCubm;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public String getRiskLevel() {
    return riskLevel;
  }

  public void setRiskLevel(String riskLevel) {
    this.riskLevel = riskLevel;
  }

  public String getCreateAccount() {
    return createAccount;
  }

  public void setCreateAccount(String createAccount) {
    this.createAccount = createAccount;
  }

  // 创建时间
  private Date createTime;

  @JSONField(format = "yyyy-MM-dd HH:mm:ss" )
  private Date createTimeUnix;

  public Date getCreateTimeUnix() {
    return createTimeUnix;
  }

  public void setCreateTimeUnix(Date createTimeUnix) {
    this.createTimeUnix = createTimeUnix;
  }

  //  更新时间
  private Date updateTime;
  //  更改信息的网格员账号
  private String updateAccount;
  //  查询开始时间
  private String startTime;

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  //  查询结束时间
  private String endTime;

  //添加地方的列表
  private List<YqfkPlaceEntity> places;
  //添加地方的名字
  private List<YqfkPlaceNameEntity> ch_14places;

  public List<YqfkPlaceNameEntity> getCh_14places() {
    return ch_14places;
  }

  public void setCh_14places(List<YqfkPlaceNameEntity> ch_14places) {
    this.ch_14places = ch_14places;
  }

  public List<YqfkPlaceEntity> getPlaces() {
    return places;
  }

  public void setPlaces(List<YqfkPlaceEntity> places) {
    this.places = places;
  }

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

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getCard() {
    return card;
  }

  public void setCard(String card) {
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

  public String getSfcz() {
    return sfcz;
  }

  public void setSfcz(String sfcz) {
    this.sfcz = sfcz;
  }

  public String getRelation() {
    return relation;
  }

  public void setRelation(String relation) {
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

  public String getIndustray() {
    return industray;
  }

  public void setIndustray(String industray) {
    this.industray = industray;
  }

  public String getReturnState() {
    return returnState;
  }

  public void setReturnState(String returnState) {
    this.returnState = returnState;
  }

  public Date getReturnTime() {
    return returnTime;
  }

  public void setReturnTime(Date returnTime) {
    this.returnTime = returnTime;
  }

  public String getReturnWay() {
    return returnWay;
  }

  public void setReturnWay(String returnWay) {
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

  public String getExpReturnWay() {
    return expReturnWay;
  }

  public void setExpReturnWay(String expReturnWay) {
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

  public String getLocalState() {
    return localState;
  }

  public void setLocalState(String localState) {
    this.localState = localState;
  }

  public Date getNatTime() {
    return natTime;
  }

  public void setNatTime(Date natTime) {
    this.natTime = natTime;
  }

  public String getNatResult() {
    return natResult;
  }

  public void setNatResult(String natResult) {
    this.natResult = natResult;
  }

  public String getTouchState() {
    return touchState;
  }

  public void setTouchState(String touchState) {
    this.touchState = touchState;
  }

  public String getIsLateStete() {
    return isLateStete;
  }

  public void setIsLateStete(String isLateStete) {
    this.isLateStete = isLateStete;
  }

  public Date getIsLateStateTime() {
    return isLateStateTime;
  }

  public void setIsLateStateTime(Date isLateStateTime) {
    this.isLateStateTime = isLateStateTime;
  }

  public String getHealthState() {
    return healthState;
  }

  public void setHealthState(String healthState) {
    this.healthState = healthState;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
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

  //14天去过的地方名称
  private String placeNames;

  public String getPlaceNames() {
    return placeNames;
  }

  public void setPlaceNames(String placeNames) {
    this.placeNames = placeNames;
  }

  @Override
  public String toString() {
    return "YqfkRegisterEntity";
  }
}
