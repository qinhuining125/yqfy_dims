package com.hengtianyi.dims.service.api;

import com.hengtianyi.common.core.base.service.AbstractGenericService;
import com.hengtianyi.dims.service.entity.ChooseOptionEntity;

import java.util.List;

/**
 * ChooseOption接口类
 *
 * @author WHY
 */
public interface ChooseOptionService extends AbstractGenericService<ChooseOptionEntity, String> {


  /**
   * @param code
   * @return
   */
  List<ChooseOptionEntity> getFirstCategoryByCode(String code);

  List<ChooseOptionEntity> getSecondCategoryByParentId(String parentId);

  List<ChooseOptionEntity> getSecondCategoryByParentName(String name);

}
