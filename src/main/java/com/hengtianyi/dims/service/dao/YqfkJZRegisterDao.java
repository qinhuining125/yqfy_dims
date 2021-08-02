package com.hengtianyi.dims.service.dao;

import com.hengtianyi.common.core.base.service.AbstractGenericDao;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.YqfkJZRegisterEntity;
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
public interface YqfkJZRegisterDao extends AbstractGenericDao<YqfkJZRegisterEntity, String> {

    /**
     * 自定义分页
     *
     * @param dto dto
     * @return list
     */
    List<YqfkJZRegisterEntity> pagelist(@Param("dto") QueryDto dto);


    /**
     * 自定义分页数量
     *
     * @param dto dto
     * @return count
     */
    Integer pagecount(@Param("dto") QueryDto dto);


    List<YqfkJZRegisterEntity> checkCard(@Param("card") String card);


    Integer getCount(@Param("entity") YqfkJZRegisterEntity entity);



    List<YqfkJZRegisterEntity> getEchartsDataStatus(@Param("startTime") String startTime,
                                                  @Param("endTime") String endTime,
                                                  @Param("areaCode") String areaCode);


}
