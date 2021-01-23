package com.hengtianyi.dims.service.dao;

import com.hengtianyi.common.core.base.service.AbstractGenericDao;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.IncorruptAdviceEntity;
import com.hengtianyi.dims.service.entity.YqfkRegisterEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * YqfkRegister数据库读写DAO
 *
 * @author LY
 */
@Mapper
public interface YqfkRegisterDao extends AbstractGenericDao<YqfkRegisterEntity, String> {

  /**
   * 自定义分页
   *
   * @param dto dto
   * @return list
   */
  List<YqfkRegisterEntity> pagelist(@Param("dto") QueryDto dto);

  /**
   * 图标所用数据
   *
   * @param startTime 开始时间
   * @param endTime   结束时间
   * @param areaCode  乡镇
   * @return list
   */
  List<YqfkRegisterEntity> getEchartsData(@Param("startTime") String startTime,
      @Param("endTime") String endTime,
      @Param("areaCode") String areaCode);



  List<YqfkRegisterEntity> getEchartsDataStatus(@Param("startTime") String startTime,
                                          @Param("endTime") String endTime,
                                          @Param("areaCode") String areaCode);


  List<YqfkRegisterEntity> getEchartsDataVehicle(@Param("startTime") String startTime,
                                                @Param("endTime") String endTime,
                                                @Param("areaCode") String areaCode);

  List<YqfkRegisterEntity> getEchartsDataIndustry(@Param("startTime") String startTime,
                                                 @Param("endTime") String endTime,
                                                 @Param("areaCode") String areaCode);

  List<YqfkRegisterEntity> getEchartsDataRisk(@Param("startTime") String startTime,
                                                  @Param("endTime") String endTime,
                                                  @Param("areaCode") String areaCode);

  /**
   * 自定义分页数量
   *
   * @param dto dto
   * @return count
   */
  Integer pagecount(@Param("dto") QueryDto dto);


  List<YqfkRegisterEntity> checkCard(@Param("card") String card);
  /**
   * 中高风险区人数
   *
   * @param userId userId
   * @return count
   */
  Integer getRiskCount(@Param("userId") String userId);


  Integer getExpCount(@Param("entity") YqfkRegisterEntity entity);

  Integer getCount(@Param("entity") YqfkRegisterEntity entity);
}
