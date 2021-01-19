package com.hengtianyi.dims.service.api.impl;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericServiceImpl;
import com.hengtianyi.common.core.base.service.ResultListCallback;
import com.hengtianyi.dims.service.api.ClueFlowService;
import com.hengtianyi.dims.service.api.RegionService;
import com.hengtianyi.dims.service.dao.ClueFlowDao;
import com.hengtianyi.dims.service.dao.RegionDao;
import com.hengtianyi.dims.service.entity.ClueFlowEntity;
import com.hengtianyi.dims.service.entity.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RegionService接口的实现类
 *
 * @author JYY
 */
@Service
public class RegionServiceImpl extends AbstractGenericServiceImpl<Region, String>
    implements RegionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(RegionServiceImpl.class);

  @Resource
  private RegionDao regionDao;

  /**
   * 实体与数据表字段的映射
   * <p>格式：实体字段 - 数据库字段</p>
   */
  private static final Map<String, String> PROPERTY_COLUMN;

  /**
   * 默认排序的sql，order by后面的部分，例如: id asc,name desc
   */
  private static final String DEFAULT_ORDER_SQL = "pcode asc";

  static {
    PROPERTY_COLUMN = new HashMap<>(6);
    // 格式：实体字段名 - 数据库字段名
    PROPERTY_COLUMN.put("pcode", "PCODE");
    PROPERTY_COLUMN.put("pname", "PNAME");
    PROPERTY_COLUMN.put("plevel", "PLEVEL");
    PROPERTY_COLUMN.put("parent", "PARENT");
    PROPERTY_COLUMN.put("valid", "VALID");
  }

  /**
   * 注入Mybatis的Dao
   *
   * @return clueFlowDao
   */
  @Override
  public RegionDao getDao() {
    return regionDao;
  }

  /**
   * 注入实体与数据库字段的映射
   *
   * @return key：实体属性名，value：数据库字段名
   */
  @Override
  public Map<String, String> getPropertyColumn() {
    return PROPERTY_COLUMN;
  }

  /**
   * 注入排序集合
   *
   * @return 排序集合
   */
  @Override
  public String getDefaultSort() {
    return DEFAULT_ORDER_SQL;
  }

  @Override
  public List<Region> getProvince() {
    return regionDao.getProvince();
  }

  @Override
  public List<Region> getCity(String parent) {
    return regionDao.getCity(parent);
  }

  @Override
  public List<Region> getCounty(String parent) {
    return regionDao.getCounty(parent);
  }
  @Override
  public Region findByCode(String pcode){
    return regionDao.findByCode(pcode);
  }

  @Override
  public Region getDataById(String id) {
    return regionDao.getDataById(id);
  }


}
