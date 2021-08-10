package com.hengtianyi.dims.web;

import com.hengtianyi.common.core.base.CommonPageDto;
import com.hengtianyi.common.core.base.CommonStringDto;
import com.hengtianyi.common.core.base.service.AbstractBaseController;
import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.common.core.util.JsonUtil;
import com.hengtianyi.common.core.util.StringUtil;
import com.hengtianyi.common.core.util.sequence.IdGenUtil;
import com.hengtianyi.dims.exception.ErrorEnum;
import com.hengtianyi.dims.exception.WebException;
import com.hengtianyi.dims.service.api.*;
import com.hengtianyi.dims.service.entity.*;
import com.hengtianyi.dims.utils.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * YqfkJZRegister - Controller
 *
 * @author JYY
 */
@Controller
@RequestMapping(value = YqfkJZRegisterController.MAPPING)
public class YqfkJZRegisterController extends
        AbstractBaseController<YqfkJZRegisterEntity, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(YqfkJZRegisterController.class);
    public static final String MAPPING = "a/yqfkJZRegister";

    @Resource
    private YqfkJZRegisterService yqfkJZRegisterService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private RegionService regionService;

    @Resource
    private TownshipService townshipService;

    @Resource
    private ChooseOptionService chooseOptionService;

    @Override
    public YqfkJZRegisterService getService() {
        return yqfkJZRegisterService;
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
        model.addAttribute("zzDWList", chooseOptionService.getFirstCategoryByCode("ZZDW"));

        return "web/yqfkJZRegister/yqfkJZRegister_index";
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
        YqfkJZRegisterEntity entity = null;
        if (StringUtil.isNoneBlank(id)) {
            entity = this.getDataByIdCommon(id);
            if (entity == null) {
                throw new WebException(ErrorEnum.NO_DATA);
            }
        }
        model.addAttribute("mapping", MAPPING);
        model.addAttribute("entity", entity);
        return "web/yqfkJZRegister/yqfkJZRegister_edit";
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
                              @ModelAttribute YqfkJZRegisterEntity dto) {
        String temp=" 08:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (dto.getDateFirstStr()!=null&&dto.getDateFirstStr()!=""){
            try {
                dto.setDateFirst(simpleDateFormat.parse(dto.getDateFirstStr()+temp));
            } catch (Exception e) {
                dto.setDateFirst(null);
            }
        }
        if (dto.getDateSecondStr()!=null&&dto.getDateSecondStr()!=""){
            try {
                dto.setDateSecond(simpleDateFormat.parse(dto.getDateSecondStr()+temp));
            } catch (Exception e) {
                dto.setDateSecond(null);
            }
        }
        if (dto.getDateThirdStr()!=null&&dto.getDateThirdStr()!=""){
            try {
                dto.setDateThird( simpleDateFormat.parse(dto.getDateThirdStr()+temp));
            } catch (Exception e) {
                dto.setDateThird(null);
            }
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
    public String getDataList1(@RequestBody YqfkJZRegisterEntity dto,HttpServletRequest request) {
        ServiceResult<Object> result = new ServiceResult<>();

        SysUserEntity user = WebUtil.getUser(request);
        if (user.getRoleId()==3000){
            dto.setAreaCode(user.getAreaCode());
        }
        List<YqfkJZRegisterEntity> listData = yqfkJZRegisterService.searchAllData(dto);
        result.setResult(listData);
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
        ServiceResult<YqfkJZRegisterEntity> result = new ServiceResult<>();
        YqfkJZRegisterEntity one = this.getDataByIdCommon(id);
        if (null != one) {

            //设置创建者和更新着名字
            one.setCreateAccount(sysUserService.getNameById(one.getCreateAccount()));
            one.setUpdateAccount(sysUserService.getNameById(one.getUpdateAccount()));
            this.clearEntity(one);
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
    public String saveData(@ModelAttribute YqfkJZRegisterEntity entity,
                           @RequestParam(value = BaseConstant.PAGE_ADD_SIGN, required = false) String n) {
        boolean isNew = StringUtil.isBlank(n);
        if (isNew) {
            // 如果没有在表单指定主键，这里需要生成一个
            String id = entity.getId();
            if (StringUtil.isBlank(id)) {
                entity.setId(IdGenUtil.uuid32());
            }
            return this.insertDataCommon(entity);
        } else {
            return this.updateDataCommon(entity);
        }
    }



    @ResponseBody
    @GetMapping(value = "/listZZDW2.json", produces = BaseConstant.JSON)
    public String saveTree(@RequestParam String zzDWType1) {
        ServiceResult<Object> result = new ServiceResult<>();
        result.setSuccess(true);
        result.setResult(chooseOptionService.getSecondCategoryByParentName(zzDWType1));
        return result.toJson();
    }

    @Override
    public void clearEntity(YqfkJZRegisterEntity entity) {
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
