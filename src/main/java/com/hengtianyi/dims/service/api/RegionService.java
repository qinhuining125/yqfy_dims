package com.hengtianyi.dims.service.api;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.Region;
import com.hengtianyi.dims.service.entity.YqfkRegisterEntity;

import java.util.List;

/**
 * RegionService接口类
 *
 * @author JYY
 */
public interface RegionService extends AbstractGenericService<Region, String> {

  List<Region> getProvince();

  List<Region> getCity(String pcode);

  List<Region> getCounty(String pcode);

  Region findByCode(String pcode);

  Region getDataById(String id);
}
