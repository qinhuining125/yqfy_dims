package com.hengtianyi.dims.service.api;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.YqfkPlaceEntity;

import java.util.List;

/**
 * YqfkPlace接口类
 *
 * @author LY
 */
public interface YqfkPlaceService extends
    AbstractGenericService<YqfkPlaceEntity, String> {


  /**
   * 自定义分页
   *
   * @param dto dto
   * @return
   */
  CommonEntityDto<YqfkPlaceEntity> pagelist(QueryDto dto);

  /**
   * @param dto
   * @return
   */
  List<YqfkPlaceEntity> listData(QueryDto dto);

  /**
   * echart数据
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param areaCode  乡镇编号
   * @return json
   */
  String echartsData(String startTime, String endTime, String areaCode);

  /**
   * 查看建议数量
   *
   * @param dto dto
   * @return
   */
  Integer countAdvice(QueryDto dto);
  /**
   * 获取14天去过地方列表
   *
   * @return
   */
  List<YqfkPlaceEntity> getListByYQID(String yqid);
}
