package com.hengtianyi.dims.web;

import com.hengtianyi.common.core.base.CommonPageDto;
import com.hengtianyi.common.core.base.CommonStringDto;
import com.hengtianyi.common.core.base.service.AbstractBaseController;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.common.core.util.JsonUtil;
import com.hengtianyi.common.core.util.StringUtil;
import com.hengtianyi.common.core.util.sequence.IdGenUtil;
import com.hengtianyi.common.core.util.sequence.SystemClock;
import com.hengtianyi.dims.exception.ErrorEnum;
import com.hengtianyi.dims.exception.WebException;
import com.hengtianyi.dims.service.api.*;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.*;
import com.hengtianyi.dims.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * YqfkRegister - Controller
 *
 * @author JYY
 */
@Controller
@RequestMapping(value = YqfkRegisterController.MAPPING)
public class YqfkRegisterController extends
        AbstractBaseController<YqfkRegisterEntity, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(YqfkRegisterController.class);
    public static final String MAPPING = "a/yqfkRegister";

    @Resource
    private YqfkRegisterService yqfkRegisterService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private RegionService regionService;
    @Resource
    private YqfkPlaceService yqfkPlaceService;
    @Resource
    private TownshipService townshipService;

    @Override
    public YqfkRegisterService getService() {
        return yqfkRegisterService;
    }

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    /**
     * 模块首页[视图]
     *
     * @param model Model
     * @return 视图
     */
    @GetMapping(value = "/index.html", produces = BaseConstant.HTML)
    public String index(Model model) {
      List<Region> ddd=  regionService.getProvince();
        model.addAttribute("mapping", MAPPING);
        model.addAttribute("areaList", regionService.getProvince());
        model.addAttribute("areaListF", townshipService.areaList());
        return "web/yqfkRegister/yqfkRegister_index";
    }

    /**
     * 模块编辑页[视图]
     *
     * @param model Model
     * @param id    主键ID
     * @return 视图
     */
    @GetMapping(value = "/edit.html", produces = BaseConstant.HTML)
    public String edit(Model model, @RequestParam(value = "id", required = false) String id) {
        YqfkRegisterEntity entity = null;
        if (StringUtil.isNoneBlank(id)) {
            entity = this.getDataByIdCommon(id);
            if (entity == null) {
                throw new WebException(ErrorEnum.NO_DATA);
            }
        }
        model.addAttribute("mapping", MAPPING);
        model.addAttribute("entity", entity);
        return "web/yqfkRegister/yqfkRegister_edit";
    }

    /**
     * 通过AJAX获取列表信息[JSON]
     *
     * @param pageDto 通用DTO
     * @param dto     查询DTO
     * @return JSON
     */
    @ResponseBody
    @PostMapping(value = "/getDataList.json", produces = BaseConstant.JSON)
    public String getDataList(HttpServletRequest request, @ModelAttribute CommonPageDto pageDto,
                              @ModelAttribute YqfkRegisterEntity dto) {
        if (dto.getBeforeReturnPbm() != null) {
            dto.setBeforeReturnPbm(dto.getBeforeReturnPbm().replace(" ", ""));
        }
        if (dto.getBeforeReturnCbm() != null) {
            dto.setBeforeReturnCbm(dto.getBeforeReturnCbm().replace(" ", ""));
        }
        if (dto.getBeforeReturnXbm() != null) {
            dto.setBeforeReturnXbm(dto.getBeforeReturnXbm().replace(" ", ""));
        }

        SysUserEntity user = WebUtil.getUser(request);
        if (user.getRoleId()==3000){
            dto.setAreaCode(user.getAreaCode());
        }
        return this.getDataListCommon(pageDto, dto);

    }

    /**
     * 通过AJAX获取列表信息,进行导出
     * @return JSON
     */
    @ResponseBody
    @PostMapping(value = "/getDataList1.json", produces = BaseConstant.JSON)
    public String getDataList1(@RequestBody YqfkRegisterEntity dto,HttpServletRequest request) {
        ServiceResult<Object> result = new ServiceResult<>();
        if (dto.getBeforeReturnPbm() != null) {
            dto.setBeforeReturnPbm(dto.getBeforeReturnPbm().replace(" ", ""));
        }
        if (dto.getBeforeReturnCbm() != null) {
            dto.setBeforeReturnCbm(dto.getBeforeReturnCbm().replace(" ", ""));
        }
        if (dto.getBeforeReturnXbm() != null) {
            dto.setBeforeReturnXbm(dto.getBeforeReturnXbm().replace(" ", ""));
        }
        SysUserEntity user = WebUtil.getUser(request);
        if (user.getRoleId()==3000){
            dto.setAreaCode(user.getAreaCode());
        }
        List<YqfkRegisterEntity> listData = yqfkRegisterService.searchAllData(dto);
        List<YqfkRegisterEntity> resultDate=new ArrayList<YqfkRegisterEntity>();
        if(listData!=null&&listData.size()!=0){
            for(int i=0;i<listData.size();i++) {
                YqfkRegisterEntity one = listData.get(i);
                List<YqfkPlaceEntity> places=yqfkPlaceService.getListByYQID(one.getId());
                StringBuilder sb=new StringBuilder();
                if(places!=null&&places.size()!=0){
                     for(int m=0;m<places.size();m++){
                         sb.append(places.get(m).getName()) ;
                         if(m!=places.size()-1){
                             sb.append(";") ;
                         }
                     }
                }
                one.setPlaceNames(sb.toString());
                resultDate.add(one);
            }
        }
        result.setResult(resultDate);
        result.setSuccess(true);
        return result.toJson();
    }
    /**
     * 通过AJAX获取单条信息[JSON]
     *
     * @param id 主键ID
     * @return JSON
     */
    @ResponseBody
    @PostMapping(value = "/getDataInfo.json", produces = BaseConstant.JSON)
    public String getDataInfo(@RequestParam String id) {
        ServiceResult<YqfkRegisterEntity> result = new ServiceResult<>();
        YqfkRegisterEntity one = this.getDataByIdCommon(id);
        if (null != one) {

            //设置创建者和更新着名字
            one.setCreateAccount(sysUserService.getNameById(one.getCreateAccount()));
            one.setUpdateAccount(sysUserService.getNameById(one.getUpdateAccount()));
            //设置返乡前、返乡后和户籍地全地址
           /* String beforeReturnAddress = this.getPname(one.getBeforeReturnPbm()) +
                    this.getPname(one.getBeforeReturnCbm()) +
                    this.getPname(one.getBeforeReturnXbm()) +
                    this.getAddress(one.getBeforeReturnAddress());
            one.setBeforeReturnAddress(beforeReturnAddress);
            String afterReturnAddress = this.getPname(one.getAfterReturnPbm()) +
                    this.getPname(one.getAfterReturnCbm()) +
                    this.getPname(one.getAfterReturnXbm()) +
                    this.getPname(one.getAfterReturnZhbm()) +
                    this.getPname(one.getAfterReturnCubm()) +
                    this.getAddress(one.getAfterReturnAddress());
            one.setAfterReturnAddress(afterReturnAddress);
            String hj = this.getPname(one.getHjPbm()) +
                    this.getPname(one.getHjCbm()) +
                    this.getPname(one.getHjXbm()) +
                    this.getAddress(one.getHj());
            one.setHj(hj);
*/
            one.setPlaces(yqfkPlaceService.getListByYQID(one.getId()));
            this.clearEntity(one);
      /*SysUserEntity userEntity = sysUserService.searchDataById(one.getUserId());
      if (userEntity != null) {
        one.setUserName(userEntity.getUserName());
      }*/
            result.setSuccess(true);
            result.setResult(one);
        } else {
            result.setError(BaseConstant.ERROR_READ);
        }
        return JsonUtil.toJson(result);
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


    /**
     * 通过AJAX删除[JSON]
     *
     * @param idsDto 主键集合DTO
     * @return JSON
     */
    @ResponseBody
    @PostMapping(value = "/deleteData.json", produces = BaseConstant.JSON)
    public String deleteData(@ModelAttribute CommonStringDto idsDto) {
        return deleteDataCommon(idsDto);
    }

    /**
     * 通过AJAX保存[JSON]
     *
     * @param entity 对象实体
     * @param n      这个可以是任意非空字符，用来确定是否添加
     * @return JSON
     */
    @ResponseBody
    @PostMapping(value = "/saveData.json", produces = BaseConstant.JSON)
    public String saveData(@ModelAttribute YqfkRegisterEntity entity,
                           @RequestParam(value = BaseConstant.PAGE_ADD_SIGN, required = false) String n) {
        boolean isNew = StringUtil.isBlank(n);
        if (isNew) {
            // 如果没有在表单指定主键，这里需要生成一个
            String id = entity.getId();
            if (StringUtil.isBlank(id)) {
                entity.setId(IdGenUtil.uuid32());
                // entity.setUserId(WebUtil.getUserId());
            }
            // entity.setCreateTime(SystemClock.nowDate());
            return this.insertDataCommon(entity);
        } else {
            return this.updateDataCommon(entity);
        }
    }

    @Override
    public void clearEntity(YqfkRegisterEntity entity) {
        if (null != entity) {
            entity.clean();
        }
    }

    /**
     * 通过AJAX获取市列表
     *
     * @param pcode 对象实体
     * @return JSON
     */
    @ResponseBody
    @GetMapping(value = "/listS.json", produces = BaseConstant.JSON)
    public String getCity(@RequestParam String pcode) {
        pcode = pcode.replace(" ", "");
        List<Region> aa = regionService.getCity(pcode);
        ServiceResult<Object> result = new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(regionService.getCity(pcode));
        return result.toJson();
    }

    /**
     * 通过AJAX获取市列表
     *
     * @param pcode 对象实体
     * @return JSON
     */
    @ResponseBody
    @GetMapping(value = "/listX.json", produces = BaseConstant.JSON)
    public String getCounty(@RequestParam String pcode) {
        pcode = pcode.replace(" ", "");
        List<Region> aa = regionService.getCity(pcode);
        ServiceResult<Object> result = new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(regionService.getCounty(pcode));
        return result.toJson();
    }
}
