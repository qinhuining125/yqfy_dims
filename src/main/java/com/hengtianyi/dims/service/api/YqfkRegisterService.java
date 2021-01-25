package com.hengtianyi.dims.service.api;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.IncorruptAdviceEntity;
import com.hengtianyi.dims.service.entity.YqfkRegisterEntity;

import java.util.List;

/**
 * YqfkRegister接口类
 *
 * @author LY
 */
public interface YqfkRegisterService extends AbstractGenericService<YqfkRegisterEntity, String> {


  /**
   * 自定义分页
   *
   * @param dto dto
   * @return
   */
  CommonEntityDto<YqfkRegisterEntity> pagelist(QueryDto dto);

  /**
   * @param dto
   * @return
   */
  List<YqfkRegisterEntity> listData(QueryDto dto);

  /**
   * echart数据
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param areaCode  乡镇编号
   * @return json
   */
  String echartsData(String startTime, String endTime, String areaCode);

  String echartsDataStatus(String startTime, String endTime, String areaCode);

  String echartsDataVehicle(String startTime, String endTime, String areaCode, String retrunState);

  String echartsDataIndustry(String startTime, String endTime, String areaCode, String returnState);

  String echartsDataRisk(String startTime, String endTime, String areaCode, String returnState);


  String echartsDataBefore(String startTime, String endTime, String areaCode, String beforeAreaPbm, String beforeAreaCbm, String beforeAreaXbm);




  /**
   * 查看建议数量
   *
   * @param dto dto
   * @return
   */
  Integer countAdvice(QueryDto dto);


  List<YqfkRegisterEntity> checkCard(String card);

  /**
   * 获取本账号下的处于中高风险地区的人数
   * @param userId 登录账号的ID
   * */
  Integer  getRiskCount(String userId);


  Integer getExpCount(YqfkRegisterEntity entity);

  Integer getCount(YqfkRegisterEntity entity);

  /**
   * 获取导出数据列表
   * @param dto 前端的查询条件
   * */
  List<YqfkRegisterEntity> searchAllData(YqfkRegisterEntity dto);
}
