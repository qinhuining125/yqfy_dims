package com.hengtianyi.dims.service.dao;

import com.hengtianyi.common.core.base.service.AbstractGenericDao;
import com.hengtianyi.dims.service.entity.ClueFlowEntity;
import com.hengtianyi.dims.service.entity.Region;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClueFlow数据库读写DAO
 *
 * @author LY
 */
@Mapper
public interface RegionDao extends AbstractGenericDao<Region, String> {

  public List<Region> getProvince();
  public List<Region> getCity(@Param("pcode") String pcode);
  public List<Region> getCounty(@Param("pcode") String pcode) ;
}
