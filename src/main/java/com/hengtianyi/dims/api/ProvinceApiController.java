package com.hengtianyi.dims.api;

import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.service.api.RegionService;
import com.hengtianyi.dims.service.dto.RegionDto;
import com.hengtianyi.dims.service.dto.TaskInfoDto;
import com.hengtianyi.dims.service.entity.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 省市县接口
 *
 * @author JYY
 */
@RestController
@RequestMapping(value = "/api/province")
public class ProvinceApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(IncorruptAdviceApiController.class);

  @Resource
  private RegionService regionService;
  /**
   * 省市县列表
   *
   * @return
   */
  @GetMapping(value = "/getName.json", produces = BaseConstant.JSON)
  public String list(HttpServletRequest request) {

    ServiceResult<Object> result = new ServiceResult<>();
    try {
      RegionDto dto=new RegionDto();
      String pcode=request.getParameter("pcode");
      if(pcode!=null){
        Region pregion=regionService.findByCode(pcode);
        dto.setPcode(pregion.getPname());
      }
      String ccode=request.getParameter("ccode");
      if(ccode!=null){
        Region cregion=regionService.findByCode(ccode);
        dto.setCcode(cregion.getPname());
      }
      String xcode=request.getParameter("xcode");
      if(xcode!=null){
        Region xregion=regionService.findByCode(xcode);
        dto.setXcode(xregion.getPname());
      }



     /* Region rg=regionService.findByCode();
      List<Region> regionList = regionService.getProvince();
      List<Region> city = regionService.getCity("4");
      List<Region> village = regionService.getCounty("288");*/
      /*for (int i = 0; i < regionList.size(); i++) {
        String parent = regionList.get(i).getId();
        regionList.get(i).setChildren(regionList);
        List<Region> city = regionService.getCity(parent);
        // 将城市信息插入省份子列表
        regionList.get(i).setChildren(city);
        for (int j = 0; j < city.size(); j++) {
          String parents = String.valueOf(city.get(j).getId());
          List<Region> county = regionService.getCounty(parents);
          // 将区县信息插入城市子列表
          city.get(j).setChildren(county);
        }
      }*/
      result.setResult(dto);
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[查询城市列表出错],{}", e.getMessage(), e);
      result.setError("查询城市列表出错");
    }
    return result.toJson();
  }
}