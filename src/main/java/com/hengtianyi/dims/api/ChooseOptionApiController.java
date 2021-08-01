package com.hengtianyi.dims.api;

import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.constant.RoleEnum;
import com.hengtianyi.dims.service.api.ChooseOptionService;
import com.hengtianyi.dims.service.api.TownshipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ChooseOption
 *
 * @author WHY
 */
@RestController
@RequestMapping(value = "/api/chooseOption")
public class ChooseOptionApiController {

  @Resource
  private ChooseOptionService chooseOptionService;


  /**
   * 获取所有的工作单位第1层级
   * 八大行业系统，学校，其他
   * @return
   */
  @ResponseBody
  @GetMapping(value = "/zzDW1list.json", produces = BaseConstant.JSON)
  public String zzDW1list(HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setResult(chooseOptionService.getFirstCategoryByCode("ZZDW"));
    return result.toJson();
  }

  @ResponseBody
  @GetMapping(value = "/getlistByParentId.json", produces = BaseConstant.JSON)
  public String getlistByParentId(@RequestParam String parentId) {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setResult(chooseOptionService.getSecondCategoryByParentId(parentId));
    return result.toJson();
  }

  /**
   * 获取所有的未接种原因第1层级
   * 禁忌症,到接种点后医生建议不接种,不愿意接种
   * @return
   */
  @ResponseBody
  @GetMapping(value = "/noJieZhReason1list.json", produces = BaseConstant.JSON)
  public String noJieZhReason1list(HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setResult(chooseOptionService.getFirstCategoryByCode("NO_JIEZH_REASON"));
    return result.toJson();
  }


  /**
   * 获取所有的政治面貌第1层级
   * 中共党员，群众，其他
   * @return
   */
  @ResponseBody
  @GetMapping(value = "/zzMM1list.json", produces = BaseConstant.JSON)
  public String zzMM1list(HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    result.setSuccess(true);
    result.setResult(chooseOptionService.getFirstCategoryByCode("ZZMM"));
    return result.toJson();
  }



}
