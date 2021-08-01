package com.hengtianyi.dims.service.api.impl;

import com.hengtianyi.common.core.base.service.AbstractGenericServiceImpl;
import com.hengtianyi.dims.service.api.ChooseOptionService;
import com.hengtianyi.dims.service.dao.ChooseOptionDao;
import com.hengtianyi.dims.service.entity.ChooseOptionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChooseOption接口的实现类
 *
 * @author WHY
 */
@Service
public class ChooseOptionServiceImpl extends AbstractGenericServiceImpl<ChooseOptionEntity, String>
    implements ChooseOptionService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ChooseOptionServiceImpl.class);

  @Resource
  private ChooseOptionDao chooseOptionDao;

  /**
   * 实体与数据表字段的映射
   * <p>格式：实体字段 - 数据库字段</p>
   */
  private static final Map<String, String> PROPERTY_COLUMN;

  private static final String DEFAULT_ORDER_SQL = "id asc";


  static {
    PROPERTY_COLUMN = new HashMap<>(3);
    // 格式：实体字段名 - 数据库字段名
    PROPERTY_COLUMN.put("code", "CODE");
    PROPERTY_COLUMN.put("id", "ID");
    PROPERTY_COLUMN.put("name", "NAME");
  }

  /**
   * 注入Mybatis的Dao
   *
   * @return villageDao
   */
  @Override
  public ChooseOptionDao getDao() {
    return chooseOptionDao;
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

  @Override
  public String getDefaultSort() {
    return DEFAULT_ORDER_SQL;
  }


  @Override
  public List<ChooseOptionEntity> getFirstCategoryByCode(String code) {
    return chooseOptionDao.getFirstCategoryByCode(code);
  }

  @Override
  public List<ChooseOptionEntity> getSecondCategoryByParentId(String parentId) {
    return chooseOptionDao.getSecondCategoryByParentId(parentId);
  }

}
