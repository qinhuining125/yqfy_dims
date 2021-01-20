package com.hengtianyi.dims.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.common.core.util.StringUtil;
import com.hengtianyi.common.core.util.sequence.IdGenUtil;
import com.hengtianyi.common.core.util.sequence.SystemClock;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.SysUserService;
import com.hengtianyi.dims.service.api.YqfkPlaceService;
import com.hengtianyi.dims.service.api.YqfkRegisterService;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.*;

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
  @Resource
  private SysUserService sysUserService;

  /**
   * 分页查询
   *
   * @param dto 分页
   * @return list
   */
  @PostMapping(value = "/pagelist.json", produces = BaseConstant.JSON)
  public String pagelist(@RequestBody CommonEntityDto<YqfkRegisterEntity> dto,HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {

      QueryDto queryDto = new QueryDto();
      queryDto.setFirst((dto.getCurrentPage() - 1) * FrameConstant.PAGE_SIZE);
      queryDto.setEnd(dto.getCurrentPage() * FrameConstant.PAGE_SIZE);
      //控制了登陆的账号自己只能看自己的
      SysUserEntity userEntity = sysUserService.searchDataById(WebUtil.getUserIdByToken(request));
      queryDto.setRoleId(userEntity.getRoleId());
      queryDto.setUserId(userEntity.getId());
      queryDto.setAreaCode(userEntity.getAreaCode());

      if (dto.getQuery() != null) { //进行返回类型的判定
        if (dto.getQuery().getReturnState() != null) {
          queryDto.setReturnState(dto.getQuery().getReturnState());
        }
      }
      //待开发，中高风险区人员
      if (dto.getQuery() != null) { //进行返回类型的判定
        if (dto.getQuery().getRiskLevel() != null) {
          queryDto.setRiskLevel(dto.getQuery().getRiskLevel());
        }
      }
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
      entity.setRiskLevel("0");//初始化默认为无风险
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
    try {
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
    } catch (Exception e) {
      LOGGER.error("[查询身份证是否登记过]出错,{}", e.getMessage(), e);
      result.setError("查询身份证是否登记过出错");
    }
      return result.toJson();
  }


  /**
   * 更新数据
   *
   * @param entity 对象实体
   * @return JSON
   */
  @PostMapping(value = "/updateData.json", produces = BaseConstant.JSON)
  public String updateData(@RequestBody YqfkRegisterEntity entity) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      String id = entity.getId();
      //需要添加查询语句，判定数据库中是否有数据
      if (StringUtil.isBlank(id)) {
        //返回错误，表示没有该参数
        result.setResult(false);
        result.setSuccess(false);
      } else {
        YqfkRegisterEntity yre=yqfkRegisterService.searchDataById(id);
        if(yre!=null){
          int ct= yqfkRegisterService.updateData(entity);
          if(ct>0){
            if(entity.getPlaces()!=null){
              //先进行原有数据的清除掉
              List<YqfkPlaceEntity> list=yqfkPlaceService.getListByYQID(id);
              for (YqfkPlaceEntity o : list) {
                yqfkPlaceService.deleteData(o);
              }
              List<YqfkPlaceEntity> places=entity.getPlaces();
              List<YqfkPlaceNameEntity> ch_14places=entity.getCh_14places();
              for(int i=0;i<places.size();i++){
                YqfkPlaceEntity yfp = places.get(i);
                yfp.setId(IdGenUtil.uuid32());
                yfp.setYqid(id);
                yfp.setCreateTime(SystemClock.nowDate());
                YqfkPlaceNameEntity ypn=ch_14places.get(i);
                yfp.setName(ypn.getA()+ypn.getB()+ypn.getC());
                int m=yqfkPlaceService.insertData(yfp);
              }
            }
          }
          result.setResult( ct > 0);
          result.setSuccess( ct > 0);
        }else{
          result.setResult(false);
          result.setSuccess(false);
        }
      }
    } catch (Exception e) {
      LOGGER.error("[更新登记信息]出错,{}", e.getMessage(), e);
      result.setError("更新登记信息");
    }
    return result.toJson();
  }

  /**
   * 登陆上来去验证此操作员是否有中高风险提醒
   *
   * @return json
   */
  @GetMapping(value = "/riskCount.json", produces = BaseConstant.JSON)
  public String riskCount(HttpServletRequest request) {

    ServiceResult<Object> result = new ServiceResult<>();
    String userId = WebUtil.getUserIdByToken(request);
    result.setResult(true);
    if (userId != null) {
     /* Integer ct = yqfkRegisterService.getRiskCount(userId);
      if (ct > 0) {//表示有高风险人群，提醒他去上报
        result.setResult(false);
      }*/
    }
    result.setSuccess(Boolean.TRUE);
    return result.toJson();
  }
}
