package com.hengtianyi.dims.api;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.common.core.util.StringUtil;
import com.hengtianyi.common.core.util.sequence.IdGenUtil;
import com.hengtianyi.common.core.util.sequence.SystemClock;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.*;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.*;
import com.hengtianyi.dims.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 疫苗接种登记表单
 *
 * @author LY
 */
@RestController
@RequestMapping(value = "/api/yqfkJZRegister")
public class YqfkJZRegisterApiController {

  private static final Logger LOGGER = LoggerFactory.getLogger(YqfkJZRegisterApiController.class);

  @Resource
  private YqfkJZRegisterService yqfkJZRegisterService;

  @Resource
  private SysUserService sysUserService;
  @Resource
  private RegionService regionService;

  @Resource
  private TownshipService townshipService;

  @Resource
  private VillageService villageService;

  /**
   * 分页查询
   *
   * @param dto 分页
   * @return list
   */
  @PostMapping(value = "/pagelist.json", produces = BaseConstant.JSON)
  public String pagelist(@RequestBody CommonEntityDto<YqfkJZRegisterEntity> dto,HttpServletRequest request) {
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

      if (dto.getQuery() != null) {
        if (dto.getQuery().getJieZhState() != null && !dto.getQuery().getJieZhState().equals("") && !(dto.getQuery().getJieZhState().equals("全部"))) {
          queryDto.setJieZhState(dto.getQuery().getJieZhState());
        }
      }

      CommonEntityDto<YqfkJZRegisterEntity> cpDto = yqfkJZRegisterService.pagelist(queryDto);
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
  public String saveData(@RequestBody YqfkJZRegisterEntity entity, HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      String ids = IdGenUtil.uuid32();
      entity.setId(ids);
      entity.setCreateTime(SystemClock.nowDate());
      entity.setUpdateTime(SystemClock.nowDate());
      entity.setCreateAccount(WebUtil.getUserIdByToken(request));
      entity.setUpdateAccount(WebUtil.getUserIdByToken(request));

//      entity.setNowPbm(entity.getNowPbm());
//      entity.setNowCbm(entity.getNowCbm());
//      entity.setNowXbm(entity.getNowXbm());

      //默认为 山西省 晋中市，寿阳县
      entity.setNowPbm("140000");
      entity.setNowCbm("140700");
      entity.setNowXbm("140725");

      String nowAddress = this.getPname(entity.getNowPbm()) +
              this.getPname(entity.getNowCbm()) +
              this.getPname(entity.getNowXbm()) +
              this.getPnameByTownshipCode(entity.getNowZhbm()) +
              this.getPnameByVillageCode(entity.getNowCubm()) +
              this.getAddress(entity.getNowAddress());
      entity.setNowAddress(nowAddress);

      int ct = yqfkJZRegisterService.insertData(entity);

      result.setSuccess(ct > 0);
      result.setResult(ct > 0);

    } catch (Exception e) {
      LOGGER.error("[saveData]接种信息上报出错,{}", e.getMessage(), e);
      result.setError("接种信息上报出错");
    }
    return result.toJson();
  }


  /*
   * 疫情防控详情
   *
   * @param id id
   * @return json
   */
  @GetMapping(value = "/detail.json", produces = BaseConstant.JSON)
  public String detail(@RequestParam String id) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      YqfkJZRegisterEntity entity=yqfkJZRegisterService.searchDataById(id);
//      List<YqfkPlaceEntity> list=yqfkPlaceService.getListByYQID(id);
//      entity.setPlaces(list);
      result.setResult(entity);
      result.setSuccess(true);
    } catch (Exception e) {
      LOGGER.error("[detail]出错,{}", e.getMessage(), e);
      result.setError("疫情防控的详情页出错");
    }
    return result.toJson();
  }

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
        List<YqfkJZRegisterEntity> entity=yqfkJZRegisterService.checkCard(card);
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
  public String updateData(@RequestBody YqfkJZRegisterEntity entity, HttpServletRequest request) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      String id = entity.getId();
      //需要添加查询语句，判定数据库中是否有数据
      if (StringUtil.isBlank(id)) {
        //返回错误，表示没有该参数
        result.setResult(false);
        result.setSuccess(false);
      } else {
        YqfkJZRegisterEntity yre=yqfkJZRegisterService.searchDataById(id);
        if(yre!=null){
          entity.setUpdateTime(SystemClock.nowDate());
          entity.setUpdateAccount(WebUtil.getUserIdByToken(request));
          int ct= yqfkJZRegisterService.updateData(entity);
          result.setResult( ct > 0);
          result.setSuccess( ct > 0);
        }else{
          result.setResult(false);
          result.setSuccess(false);
        }
      }
    } catch (Exception e) {
      LOGGER.error("[更新疫苗接种登记信息]出错,{}", e.getMessage(), e);
      result.setError("更新疫苗接种登记信息");
    }
    return result.toJson();
  }



  /**
   * 登陆上来 未完全接种人数
   *
   * @return json
   */
  @GetMapping(value = "/count.json", produces = BaseConstant.JSON)
  public String count(HttpServletRequest request) {

    ServiceResult<Object> result = new ServiceResult<>();
    try{
      String userId = WebUtil.getUserIdByToken(request);
      YqfkJZRegisterEntity entity=new YqfkJZRegisterEntity();
      entity.setCreateAccount(WebUtil.getUserIdByToken(request));
      result.setResult(0);
      if (userId != null) {
        Integer ct = yqfkJZRegisterService.getCount(entity);
        if (ct > 0) {//表示有 没有完成疫苗接种的，提醒他去上报
          result.setResult(ct);
        }
      }
      result.setSuccess(Boolean.TRUE);
    }catch (Exception e) {
      LOGGER.error("[查询待办数量]出错,{}", e.getMessage(), e);
      result.setError("查询待办数量出错");
    }
    return result.toJson();
  }


  /**
   * 删除
   *
   * @param id
   * @return
   */
  @GetMapping(value = "/delete.json", produces = BaseConstant.JSON)
  public String close(@RequestParam String id) {
    ServiceResult<Object> result = new ServiceResult<>();
    try {
      if(StringUtil.isEmpty(id)){
        result.setError("没有待删除的数据");
        result.setResult(false);
      }else{
        YqfkJZRegisterEntity yre=yqfkJZRegisterService.searchDataById(id);
        if(yre!=null){
          YqfkJZRegisterEntity obj=new YqfkJZRegisterEntity();
          obj.setId(id);
          int ct=yqfkJZRegisterService.deleteData(obj);
          result.setResult(ct>0);
          result.setSuccess(ct>0);
        } else {
          result.setResult(false);
          result.setError("库中没有该id对应的数据");
        }

      }
    } catch (Exception e) {
      LOGGER.error("删除接种信息出错,{}", e.getMessage());
      result.setError("删除接种信息出错");
    }
    return result.toJson();
  }

  //获取区域名称

  public String getPname(String pcode) {
    String pname = "";
    if (pcode == null) {
      pname = "";
    } else {
      Region regin = regionService.findByCode(pcode);
      if (regin != null) {
        pname += regionService.findByCode(pcode).getPname();
      }
    }
    return pname;
  }

  //获取乡镇名称 TownshipDao
  public String getPnameByTownshipCode(String townshipCode) {

    String pname = "";
    if (townshipCode == null) {
      pname = "";
    } else {
      TownshipEntity town = townshipService.findByCode(townshipCode);
      if (town != null) {
        pname += townshipService.findByCode(townshipCode).getAreaName();
      }
    }
    return pname;
  }

  //获取村名称 Village
  public String getPnameByVillageCode(String villageCode) {

    String pname = "";
    if (villageCode == null) {
      pname = "";
    } else {
      VillageEntity village = villageService.findByCode(villageCode);
      if (village != null) {
        pname += villageService.findByCode(villageCode).getAreaName();
      }
    }
    return pname;
  }

  //为null时转为”“
  public String getAddress(String address) {
    String addressT = "";
    if (address == null) {
      addressT = "";
    } else {
      addressT = address;
    }
    return addressT;
  }
}
