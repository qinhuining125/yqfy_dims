package com.hengtianyi.dims.service.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.hengtianyi.common.core.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * YqfkJZRegisterEntity实体类
 * <p>Table: YQ_YQFK_JZ</p>
 *
 * @author LY
 */
public class YqfkJZRegisterEntity extends BaseEntity {

    private static final long serialVersionUID = -2842431694883319857L;

    /**
     * 基本信息
     */
    //id
    private String id;
    //姓名
    private String name;
    //性别
    private String sex;
    //年龄
    private String age;
    //身份证
    private String card;
    //生日
    private Date birthday;
    //联系电话
    private String phone;

    //现住址
    //省编码--默认 山西省
    private String nowPbm;
    //市编码--默认 晋中市
    private String nowCbm;
    //县编码--默认 寿阳县
    private String nowXbm;

    //镇编码--选择
    private String nowZhbm;
    //村编码--选择
    private String nowCubm;

    //现住址详细（填写）(包含门牌号码 汉字+字母门牌号)
    private String nowAddress;

    //居住类型（单选） 1:常驻 2:租户
    private String juzhuType;

    //政治面貌1:中共党员 2:群众 3:其他
    private String zzMM;

    //工作单位（分层级，第1层级 选择大类； 第2层级 选择具体的类别， 第三层级 填写具体的信息）
    //工作大类：1:八大行业系统 2: 学校 3: 其他
    private String zzDWType1;

    //其他（备注单位名称或职业情况）
    //1:八大行业系统（煤炭工矿、三产服务、党政机关事业单位、电力能源、经济技术开发区、市政重点工程领域、农业畜牧、交通运输）
    //2:学校 (幼儿园、小学、初中、高中、大学自填学校名称）
    //3:其他（备注单位名称或职业情）
    private String zzDWType2;

    //具体名称（或者备注）
    private String zzDWType3;

    /**
     * 接种情况信息
     */
    //接种类型（单选） 1:已接种 2:未接种
    private String jieZhType;

    //接种针剂类型（1: 灭活两针次疫苗  2: 重组三针次疫苗  3: 一针）
    private String vaccineType;

    //第1针
    //  接种时间
    @JSONField(format = "yyyy-MM-dd")
    private Date dateFirst;

    //  接种地点(用户自己输入)
    private String addressFirst;


    //第2针
    //  接种时间
    @JSONField(format = "yyyy-MM-dd")
    private Date dateSecond;

    //  接种地点(用户自己输入)
    private String addressSecond;

    //第3针
    //  接种时间
    @JSONField(format = "yyyy-MM-dd")
    private Date dateThird;

    //  接种地点(用户自己输入)
    private String addressThird;


    //未接种原因（1: 禁忌症  2: 到接种点后医生建议不接种  3: 不愿意接种）
    private String noJieZhReasonType;

    //1: 禁忌症（多选）(存储时，用逗号隔开)
    private String noJieZhReasonDetails;


    //是否接种完毕（接种针剂全部打完， 1-1， 2-2，3-3 ）
    //例如，2 针只打了1针，则没有接种完毕；  3 针只打了1针或2针，则没有接种完毕
    //0:否 1:是
    private String jieZhState;

    //总针剂数（1：1， 2：2， 3：3）
    private int ttlNum;

    //接种剂次（用于记录接种了几次 剂次了，例如，3针的，接种了2次，那么这个字段值就是2，如果已经接种3次，那么这个字段值就是3）
    private int number;

    // 其他说明
    private String remark;

    // 录入信息的网格员账号
    private String createAccount;

    // 创建时间
    private Date createTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss" )
    private Date createTimeUnix;

    //  更新时间
    private Date updateTime;
    //  更改信息的网格员账号
    private String updateAccount;

    //判断管理员所属
    private String areaCode;

    //创建者所属乡镇
    private String createBelZhbm;
    //创建者所属村
    private String createBelCubm;

    //  查询开始时间
    private String startTime;

    //  查询结束时间
    private String endTime;


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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNowPbm() {
        return nowPbm;
    }

    public void setNowPbm(String nowPbm) {
        this.nowPbm = nowPbm;
    }

    public String getNowCbm() {
        return nowCbm;
    }

    public void setNowCbm(String nowCbm) {
        this.nowCbm = nowCbm;
    }

    public String getNowXbm() {
        return nowXbm;
    }

    public void setNowXbm(String nowXbm) {
        this.nowXbm = nowXbm;
    }

    public String getNowZhbm() {
        return nowZhbm;
    }

    public void setNowZhbm(String nowZhbm) {
        this.nowZhbm = nowZhbm;
    }

    public String getNowCubm() {
        return nowCubm;
    }

    public void setNowCubm(String nowCubm) {
        this.nowCubm = nowCubm;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getJuzhuType() {
        return juzhuType;
    }

    public void setJuzhuType(String juzhuType) {
        this.juzhuType = juzhuType;
    }

    public String getZzMM() {
        return zzMM;
    }

    public void setZzMM(String zzMM) {
        this.zzMM = zzMM;
    }

    public String getZzDWType1() {
        return zzDWType1;
    }

    public void setZzDWType1(String zzDWType1) {
        this.zzDWType1 = zzDWType1;
    }

    public String getZzDWType2() {
        return zzDWType2;
    }

    public void setZzDWType2(String zzDWType2) {
        this.zzDWType2 = zzDWType2;
    }

    public String getZzDWType3() {
        return zzDWType3;
    }

    public void setZzDWType3(String zzDWType3) {
        this.zzDWType3 = zzDWType3;
    }

    public String getJieZhType() {
        return jieZhType;
    }

    public void setJieZhType(String jieZhType) {
        this.jieZhType = jieZhType;
    }


    public String getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(String vaccineType) {
        this.vaccineType = vaccineType;
    }

    public Date getDateFirst() {
        return dateFirst;
    }

    public void setDateFirst(Date dateFirst) {
        this.dateFirst = dateFirst;
    }

    public String getAddressFirst() {
        return addressFirst;
    }

    public void setAddressFirst(String addressFirst) {
        this.addressFirst = addressFirst;
    }

    public Date getDateSecond() {
        return dateSecond;
    }

    public void setDateSecond(Date dateSecond) {
        this.dateSecond = dateSecond;
    }

    public String getAddressSecond() {
        return addressSecond;
    }

    public void setAddressSecond(String addressSecond) {
        this.addressSecond = addressSecond;
    }

    public Date getDateThird() {
        return dateThird;
    }

    public void setDateThird(Date dateThird) {
        this.dateThird = dateThird;
    }

    public String getAddressThird() {
        return addressThird;
    }

    public void setAddressThird(String addressThird) {
        this.addressThird = addressThird;
    }

    public String getNoJieZhReasonType() {
        return noJieZhReasonType;
    }

    public void setNoJieZhReasonType(String noJieZhReasonType) {
        this.noJieZhReasonType = noJieZhReasonType;
    }

    public String getNoJieZhReasonDetails() {
        return noJieZhReasonDetails;
    }

    public void setNoJieZhReasonDetails(String noJieZhReasonDetails) {
        this.noJieZhReasonDetails = noJieZhReasonDetails;
    }

    public String getJieZhState() {
        return jieZhState;
    }

    public void setJieZhState(String jieZhState) {
        this.jieZhState = jieZhState;
    }

    public int getTtlNum() {
        return ttlNum;
    }

    public void setTtlNum(int ttlNum) {
        this.ttlNum = ttlNum;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

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

    public Date getCreateTimeUnix() {
        return createTimeUnix;
    }

    public void setCreateTimeUnix(Date createTimeUnix) {
        this.createTimeUnix = createTimeUnix;
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

    @Override
    public String toString() {
        return "YqfkJzRegisterEntity";
    }
}
