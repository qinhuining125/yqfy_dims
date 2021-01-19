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
import com.hengtianyi.dims.service.entity.Region;
import com.hengtianyi.dims.service.entity.TaskInfoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * YqfkRegister - Controller
 *
 * @author JYY
 */
@Controller
@RequestMapping(value = YqfkPcxController.MAPPING)
public class YqfkPcxController extends
        AbstractBaseController<Region, String> {

    private static final Logger LOGGER = LoggerFactory.getLogger(YqfkPcxController.class);
    public static final String MAPPING = "a/yqfkPcx";

    @Resource
    private RegionService regionService;

    @Override
    public RegionService getService() {
        return regionService;
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
        model.addAttribute("mapping", MAPPING);
        model.addAttribute("areaList", regionService.getProvince());
        return "web/yqfkPcx/yqfkPcx_index";
    }

    /**
     * 模块编辑页[视图]
     *
     * @param model Model
     * @param id    主键ID
     * @return 视图
     */
    @GetMapping(value = "/edit.html", produces = BaseConstant.HTML)
    public String edit(Model model, @RequestParam(value = "id", required = false) String id,
                       HttpServletRequest request) {
        Region entity = null;
        if (StringUtil.isNoneBlank(id)) {
            entity = regionService.findByCode(id);
            if (entity == null) {
                throw new WebException(ErrorEnum.NO_DATA);
            }
        }
        model.addAttribute("mapping", MAPPING);
        model.addAttribute("entity", entity);
        return "web/yqfkPcx/yqfkPcx_edit";
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
    public String getDataList(@ModelAttribute CommonPageDto pageDto,
                              @ModelAttribute Region dto) {
        return this.getDataListCommon(pageDto, dto);
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
        ServiceResult<Region> result = new ServiceResult<>();
        Region one = regionService.findByCode(id);
        if (null != one) {
            this.clearEntity(one);

            result.setSuccess(true);
            result.setResult(one);
        } else {
            result.setError(BaseConstant.ERROR_READ);
        }
        return JsonUtil.toJson(result);
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
    public String saveData(@ModelAttribute Region entity,
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
    public void clearEntity(Region entity) {
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
