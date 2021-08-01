package com.hengtianyi.dims.service.api;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.YqfkJZRegisterEntity;

import java.util.List;

/**
 * YqfkJZRegister接口类
 *
 * @author LY
 */
public interface YqfkJZRegisterService extends AbstractGenericService<YqfkJZRegisterEntity, String> {


  /**
   * 自定义分页
   *
   * @param dto dto
   * @return
   */
  CommonEntityDto<YqfkJZRegisterEntity> pagelist(QueryDto dto);

  /**
   * @param dto
   * @return
   */
  List<YqfkJZRegisterEntity> listData(QueryDto dto);


  List<YqfkJZRegisterEntity> checkCard(String card);



  Integer getCount(YqfkJZRegisterEntity entity);

  /**
   * 获取导出数据列表
   * @param dto 前端的查询条件
   * */
  List<YqfkJZRegisterEntity> searchAllData(YqfkJZRegisterEntity dto);
}
