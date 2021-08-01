package com.hengtianyi.dims.service.dao;

import com.hengtianyi.common.core.base.service.AbstractGenericDao;
import com.hengtianyi.dims.service.entity.ChooseOptionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ChooseOption数据库读写DAO
 *
 * @author WHY
 */
@Mapper
public interface ChooseOptionDao extends AbstractGenericDao<ChooseOptionEntity, String> {


  List<ChooseOptionEntity> getFirstCategoryByCode(@Param("code") String code);

  List<ChooseOptionEntity> getSecondCategoryByParentId(@Param("parentId") String parentId);



}
