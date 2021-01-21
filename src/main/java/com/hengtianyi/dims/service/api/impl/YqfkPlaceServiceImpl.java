package com.hengtianyi.dims.service.api.impl;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericServiceImpl;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.YqfkPlaceService;
import com.hengtianyi.dims.service.dao.YqfkPlaceDao;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.IncorruptAdviceEntity;
import com.hengtianyi.dims.service.entity.YqfkPlaceEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IncorruptAdvice接口的实现类
 *
 * @author LY
 */
@Service
public class YqfkPlaceServiceImpl extends
    AbstractGenericServiceImpl<YqfkPlaceEntity, String>
    implements YqfkPlaceService {

  private static final Logger LOGGER = LoggerFactory.getLogger(YqfkPlaceServiceImpl.class);

  @Resource
  private YqfkPlaceDao yqfkPlaceDao;

  /**
   * 实体与数据表字段的映射
   * <p>格式：实体字段 - 数据库字段</p>
   */
  private static final Map<String, String> PROPERTY_COLUMN;

  /**
   * 默认排序的sql，order by后面的部分，例如: id asc,name desc
   */
  private static final String DEFAULT_ORDER_SQL = "id asc";

  static {
    PROPERTY_COLUMN = new HashMap<>(6);
    // 格式：实体字段名 - 数据库字段名
    PROPERTY_COLUMN.put("id", "ID");
    PROPERTY_COLUMN.put("pcode", "PCODE");
    PROPERTY_COLUMN.put("ccode", "CCODE");
    PROPERTY_COLUMN.put("xcode", "XCODE");
    PROPERTY_COLUMN.put("yqid", "YQID");
    PROPERTY_COLUMN.put("createTime", "CREATE_TIME");
  }

  /**
   * 注入Mybatis的Dao
   *
   * @return incorruptAdviceDao
   */
  @Override
  public YqfkPlaceDao getDao() {
    return yqfkPlaceDao;
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

  /**
   * 自定义分页
   *
   * @param dto dto
   * @return
   */
  @Override
  public CommonEntityDto<YqfkPlaceEntity> pagelist(QueryDto dto) {
    Integer count = yqfkPlaceDao.searchDataCount(new YqfkPlaceEntity());
    List<YqfkPlaceEntity> list = yqfkPlaceDao.pagelist(dto);
    /*for (IncorruptAdviceEntity clueReportEntity : list) {
      clueReportEntity.setRoleName(RoleEnum.getNameByRoleId(clueReportEntity.getRoleId()));
    }*/
    CommonEntityDto<YqfkPlaceEntity> result = new CommonEntityDto<>(list);
    result.setCurrentPage(dto.getCurrentPage());
    result.setSize(FrameConstant.PAGE_SIZE);
    result.setTotal(count);
    return result;
  }

  /**
   * @param dto
   * @return
   */
  @Override
  public List<YqfkPlaceEntity> listData(QueryDto dto) {
    dto.setFirst((dto.getCurrentPage() - 1) * FrameConstant.PAGE_SIZE);
    dto.setEnd(dto.getCurrentPage() * FrameConstant.PAGE_SIZE);
    return yqfkPlaceDao.pagelist(dto);
  }

  /**
   * echart数据
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param areaCode  乡镇编号
   * @return json
   */
  @Override
  public String echartsData(String startTime, String endTime, String areaCode) {
    ServiceResult<Object> result = new ServiceResult();
   /* try {
      List<IncorruptAdviceEntity> adviceEntityList = incorruptAdviceDao
          .getEchartsData(startTime, endTime, areaCode);
      Map<String, Object> map = count(adviceEntityList);
      List<VillageEntity> villageList = villageDao.areaList(areaCode);
      String[] villageNames = new String[villageList.size()];
      String[] wanggeyuans = new String[villageList.size()];
      String[] lianluoyuans = new String[villageList.size()];
      String[] xiangzhens = new String[villageList.size()];
      for (int i = 0; i < villageList.size(); i++) {
        VillageEntity village = villageList.get(i);
        adviceEntityList = incorruptAdviceDao
            .getEchartsData(startTime, endTime, village.getAreaCode());
        Map<String, Object> dataMap = count(adviceEntityList);
        villageNames[i] = village.getAreaName();
        wanggeyuans[i] = dataMap.get("wanggeyuan").toString();
        lianluoyuans[i] = dataMap.get("lianluoyuan").toString();
        xiangzhens[i] = dataMap.get("xiangzhen").toString();
      }
      map.put("villageNames", villageNames);
      map.put("wanggeyuans", wanggeyuans);
      map.put("lianluoyuans", lianluoyuans);
      map.put("xiangzhens", xiangzhens);
      result.setSuccess(true);
      result.setResult(map);
    } catch (Exception e) {
      LOGGER.error("[echartsData]出错,{}", e.getMessage(), e);
      result.setError("false");
    }*/
    return result.toJson();
  }

  private Map<String, Object> count(List<IncorruptAdviceEntity> list) {
    Map<String, Object> map = new HashMap<>();
    /*Integer wanggeyuan = 0;
    Integer lianluoyuan = 0;
    Integer xiangzhen = 0;
    for (IncorruptAdviceEntity entity : list) {
      if (entity.getRoleId() == 1001) {
        wanggeyuan += 1;
      } else if (entity.getRoleId() == 1002) {
        lianluoyuan += 1;
      } else if (entity.getRoleId() == 1003) {
        xiangzhen += 1;
      }
    }
    map.put("wanggeyuan", wanggeyuan);
    map.put("lianluoyuan", lianluoyuan);
    map.put("xiangzhen", xiangzhen);*/
    return map;
  }

  @Override
  public Integer countAdvice(QueryDto dto) {
    return yqfkPlaceDao.pagecount(dto);
  }

  @Override
  public List<YqfkPlaceEntity> getListByYQID(String yqid) {
    return yqfkPlaceDao.getListByYQID(yqid);
  }

  @Override
  public List<YqfkPlaceEntity> getListByYQID14(String yqid) {
    return yqfkPlaceDao.getListByYQID14(yqid);
  }

}
