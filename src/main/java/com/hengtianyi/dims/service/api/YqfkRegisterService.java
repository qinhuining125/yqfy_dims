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
public interface YqfkRegisterService extends
    AbstractGenericService<YqfkRegisterEntity, String> {


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

  String echartsVehicleData(String startTime, String endTime, String areaCode);

  /**
   * 查看建议数量
   *
   * @param dto dto
   * @return
   */
  Integer countAdvice(QueryDto dto);
}
