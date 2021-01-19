package com.hengtianyi.dims.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.common.core.util.sequence.IdGenUtil;
import com.hengtianyi.common.core.util.sequence.SystemClock;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.YqfkPlaceService;
import com.hengtianyi.dims.service.api.YqfkRegisterService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.PatrolInfoEntity;
import com.hengtianyi.dims.service.entity.YqfkPlaceEntity;
import com.hengtianyi.dims.service.entity.YqfkPlaceNameEntity;
import com.hengtianyi.dims.service.entity.YqfkRegisterEntity;

import com.hengtianyi.dims.utils.WebUtil;
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
@RequestMapping(value = "/api/yqfkRegister")
public class YqfkRegisterApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(YqfkRegisterApiController.class);

  @Resource
  private YqfkRegisterService yqfkRegisterService;
  @Resource
  private YqfkPlaceService yqfkPlaceService;

  /**
   * 分页查询
   *
   * @param dto 分页
   * @return list
   */
  @PostMapping(value = "/pagelist.json", produces = BaseConstant.JSON)
  public String pagelist(@RequestBody CommonEntityDto<YqfkRegisterEntity> dto) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      QueryDto queryDto = new QueryDto();
      queryDto.setFirst((dto.getCurrentPage() - 1) * FrameConstant.PAGE_SIZE);
      queryDto.setEnd(dto.getCurrentPage() * FrameConstant.PAGE_SIZE);
      CommonEntityDto<YqfkRegisterEntity> cpDto = yqfkRegisterService.pagelist(queryDto);
      result.setResult(cpDto);
      result.setSuccess(true);
    } catch (Exception ex) {
      result.setError("error");
      LOGGER.error("[pagelist]{}, pageDto = {}, dto = {}", ex.getMessage(), ex);
    }
    return result.toJson();
  }

  /**
   * @param entity
   * @return
   */
  @PostMapping(value = "/saveData.json", produces = BaseConstant.JSON)
  public String saveData(@RequestBody YqfkRegisterEntity entity, HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      String ids=IdGenUtil.uuid32();
      entity.setId(ids);
      entity.setCreateTime(SystemClock.nowDate());
      entity.setUpdateTime(SystemClock.nowDate());
      entity.setCreateAccount(WebUtil.getUserIdByToken(request));
      entity.setUpdateAccount(WebUtil.getUserIdByToken(request));
      int ct = yqfkRegisterService.insertData(entity);
      if(ct > 0){
        if(entity.getPlaces()!=null){
          List<YqfkPlaceEntity> places=entity.getPlaces();
          List<YqfkPlaceNameEntity> ch_14places=entity.getCh_14places();
          for(int i=0;i<places.size();i++){
            YqfkPlaceEntity yfp = places.get(i);
            yfp.setId(IdGenUtil.uuid32());
            yfp.setYqid(ids);
            yfp.setCreateTime(SystemClock.nowDate());
            YqfkPlaceNameEntity ypn=ch_14places.get(i);
            yfp.setName(ypn.getA()+ypn.getB()+ypn.getC());
            int m=yqfkPlaceService.insertData(yfp);
          }
        }
      }
      result.setSuccess(ct > 0);
      result.setResult(ct > 0);
    } catch (Exception e) {
      LOGGER.error("[saveData]疫情防控信息上报出错,{}", e.getMessage(), e);
      result.setError("疫情防控信息上报出错");
    }
    return result.toJson();
  }

/*  *//**
   * 上报详情
   *
   * @param id id
   * @return json
   *//*
  @GetMapping(value = "/detail.json", produces = BaseConstant.JSON)
  public String detail(@RequestParam String id) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      IncorruptAdviceEntity entity = incorruptAdviceService.searchDataById(id);
      SysUserEntity userEntity = sysUserService.searchDataById(entity.getUserId());
      entity.setUserName(userEntity.getUserName());
      entity.setRoleId(userEntity.getRoleId());
      entity.setRoleName(RoleEnum.getNameByRoleId(userEntity.getRoleId()));
      result.setResult(entity);
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[detail]出错,{}", e.getMessage(), e);
      result.setError("详情页出错");
    }
    return result.toJson();
  }*/

  /**
   * 验证此身份证号是否填写过
   *
   * @return json
   */
  @GetMapping(value = "/isHave.json", produces = BaseConstant.JSON)
  public String isHaveProcess(HttpServletRequest request,@RequestParam("card") String card) {

    ServiceResult<Object> result = new ServiceResult<>();
    if(card!=null){
      List<YqfkRegisterEntity> entity=yqfkRegisterService.checkCard(card);
      if (entity.size()!=0) {//表示没有这个时间段的
        result.setResult(false);
      } else {//表示有正在进行的信息
        result.setResult(true);
      }
    }else{
      result.setResult("card为空，无法查询");
    }
    result.setSuccess(Boolean.TRUE);
    return result.toJson();
  }
}
