package com.hengtianyi.dims.api;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.common.core.util.sequence.IdGenUtil;
import com.hengtianyi.common.core.util.sequence.SystemClock;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.TownshipService;
import com.hengtianyi.dims.service.api.VillageService;
import com.hengtianyi.dims.service.api.YqfkPlaceService;
import com.hengtianyi.dims.service.api.YqfkRegisterService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.YqfkPlaceEntity;
import com.hengtianyi.dims.service.entity.YqfkRegisterEntity;
import com.hengtianyi.dims.utils.WebUtil;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 廉政建议
 *
 * @author LY
 */
@RestController
@RequestMapping(value = "/api/village")
public class VillageApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(VillageApiController.class);

  @Resource
  private VillageService villageService;
  @Resource
  private TownshipService townshipService;



  /**
   * 获取村的列表信息
   *
   * @param areaCode 镇的code
   * @return list
   */
  @ResponseBody
  @GetMapping(value = "/vlist.json", produces = BaseConstant.JSON)
  public String vlist(@RequestParam String areaCode) {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setResult(villageService.townChilds(areaCode));
    return result.toJson();
  }

  /**
   * 获取镇的列表信息
   * @return list
   */
  @ResponseBody
  @GetMapping(value = "/tlist.json", produces = BaseConstant.JSON)
  public String tlist( ) {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setResult(townshipService.areaList());
    return result.toJson();
  }

}
