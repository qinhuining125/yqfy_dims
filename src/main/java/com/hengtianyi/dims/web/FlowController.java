package com.hengtianyi.dims.web;

import com.hengtianyi.common.core.constant.BaseConstant;
import com.hengtianyi.common.core.util.TimeUtil;
import com.hengtianyi.dims.service.api.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

/**
 * 流程管理
 *
 * @author LY
 */
@Controller
public class FlowController {

    @Resource
    private TownshipService townshipService;

    @Resource
    private ChooseOptionService chooseOptionService;


    @Resource
    private ClueReportService clueReportService;
    @Resource
    private IncorruptAdviceService incorruptAdviceService;
    @Resource
    private TaskInfoService taskInfoService;
    @Resource
    private RevealInfoService revealInfoService;

    @Resource
    private YqfkRegisterService yqfkRegisterService;

    @Resource
    private YqfkJZRegisterService yqfkJZRegisterService;

    @Resource
    private RegionService regionService;

    /**
     * @param model
     * @return
     */
    @GetMapping(value = "a/flow/clue.html", produces = BaseConstant.HTML)
    public String clue(Model model) {
        return "web/flow/clue_index";
    }

    @GetMapping(value = "a/flow/advice.html", produces = BaseConstant.HTML)
    public String advice(Model model) {
        return "web/flow/advice_index";
    }

    @GetMapping(value = "a/flow/task.html", produces = BaseConstant.HTML)
    public String task(Model model) {
        return "web/flow/task_index";
    }

    @GetMapping(value = "a/flow/yqfkFlow.html", produces = BaseConstant.HTML)
    public String yqfkFlow(Model model) {
        return "web/flow/yqfk_index";
    }

    @GetMapping(value = "a/analysis/clue.html", produces = BaseConstant.HTML)
    public String analysisClue(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/clue_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/clueData.json", produces = BaseConstant.JSON)
    public String clueData(@RequestParam(required = false) Long startTime,
                           @RequestParam(required = false) Long endTime,
                           @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return clueReportService.echartsData(start, end, areaCode);
    }

    @GetMapping(value = "a/analysis/advice.html", produces = BaseConstant.HTML)
    public String analysisAdvice(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/advice_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/adviceData.json", produces = BaseConstant.JSON)
    public String adviceData(@RequestParam(required = false) Long startTime,
                             @RequestParam(required = false) Long endTime,
                             @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return incorruptAdviceService.echartsData(start, end, areaCode);
    }

    //添加举报统计
    @GetMapping(value = "a/analysis/report.html", produces = BaseConstant.HTML)
    public String analysisReport(Model model) {//取出镇和村列表
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/report_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/reportData.json", produces = BaseConstant.JSON)
    public String reportData(@RequestParam(required = false) Long startTime,
                             @RequestParam(required = false) Long endTime,
                             @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return revealInfoService.echartsData(start, end, areaCode);
    }

    @GetMapping(value = "a/analysis/task.html", produces = BaseConstant.HTML)
    public String analysisTask(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/task_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/taskData.json", produces = BaseConstant.JSON)
    public String taskData(@RequestParam(required = false) Long startTime,
                           @RequestParam(required = false) Long endTime,
                           @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return taskInfoService.echartsData(start, end, areaCode);
    }


    /**
     * 来返寿登记统计分析--按照状态统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/status.html", produces = BaseConstant.HTML)
    public String analysisStatus(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/status_index";
    }


    @ResponseBody
    @PostMapping(value = "a/analysis/statusData.json", produces = BaseConstant.JSON)
    public String statusData(@RequestParam(required = false) Long startTime,
                             @RequestParam(required = false) Long endTime,
                             @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkRegisterService.echartsDataStatus(start, end, areaCode);
    }

    /**
     * 来返寿登记统计分析--按照风险等级来统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/risk.html", produces = BaseConstant.HTML)
    public String analysisRisk(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/risk_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/riskData.json", produces = BaseConstant.JSON)
    public String riskData(@RequestParam(required = false) Long startTime,
                           @RequestParam(required = false) Long endTime,
                           @RequestParam(required = false) String areaCode,
                           @RequestParam(required = false) String returnState) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkRegisterService.echartsDataRisk(start, end, areaCode,returnState);
    }


    /**
     * 来返寿登记统计分析--按照人员类别统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/industry.html", produces = BaseConstant.HTML)
    public String analysisIndustry(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/industry_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/industryData.json", produces = BaseConstant.JSON)
    public String industryData(@RequestParam(required = false) Long startTime,
                               @RequestParam(required = false) Long endTime,
                               @RequestParam(required = false) String areaCode,
                               @RequestParam(required = false) String returnState) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        String df = yqfkRegisterService.echartsDataIndustry(start, end, areaCode,returnState);
        return yqfkRegisterService.echartsDataIndustry(start, end, areaCode,returnState);
    }

    /**
     * 来返寿登记统计分析--按照交通工具
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/vehicle.html", produces = BaseConstant.HTML)
    public String analysisVehicle(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/vehicle_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/vehicleData.json", produces = BaseConstant.JSON)
    public String vehicleData(@RequestParam(required = false) Long startTime,
                              @RequestParam(required = false) Long endTime,
                              @RequestParam(required = false) String areaCode,
                              @RequestParam(required = false) String returnState) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkRegisterService.echartsDataVehicle(start, end, areaCode, returnState);
    }


    /**
     * 来返寿登记统计分析--按照返乡前地址统计
     *
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/before.html", produces = BaseConstant.HTML)
    public String analysisbeforeBefore(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        model.addAttribute("areaList1", regionService.getProvince());
        return "web/analysis/before_area_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/beforeData.json", produces = BaseConstant.JSON)
    public String beforeData(@RequestParam(required = false) Long startTime,
                             @RequestParam(required = false) Long endTime,
                             @RequestParam(required = false) String areaCode,
                             @RequestParam(required = false) String beforeAreaPbm,
                             @RequestParam(required = false) String beforeAreaCbm,
                             @RequestParam(required = false) String beforeAreaXbm) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkRegisterService.echartsDataBefore(start, end, areaCode, beforeAreaPbm,beforeAreaCbm,beforeAreaXbm);
    }


    /**
     * 疫苗接种登记统计分析--按照工作单位统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/yqfkJZzzdw.html", produces = BaseConstant.HTML)
    public String analysisYqfkJZzzdw(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        model.addAttribute("zzDWList", chooseOptionService.getFirstCategoryByCode("ZZDW"));
        return "web/analysis/yqfkJZzzdw_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/yqfkJZzzdwData.json", produces = BaseConstant.JSON)
    public String yqfkJZzzdwData(@RequestParam(required = false) Long startTime,
                             @RequestParam(required = false) Long endTime,
                             @RequestParam(required = false) String areaCode,
                             @RequestParam(required = false) String queryzzDWType1,
                             @RequestParam(required = false) String queryzzDWType2) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkJZRegisterService.echartsDataYqfkJZzzdw(start, end, areaCode, queryzzDWType1,queryzzDWType2);
    }




    /**
     * 疫苗接种登记统计分析--按照政治面貌统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/yqfkJZzzmm.html", produces = BaseConstant.HTML)
    public String analysisYqfkJZzzmm(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/yqfkJZzzmm_index";
    }


    @ResponseBody
    @PostMapping(value = "a/analysis/yqfkJZzzmmData.json", produces = BaseConstant.JSON)
    public String yqfkJZzzmmData(@RequestParam(required = false) Long startTime,
                                 @RequestParam(required = false) Long endTime,
                                 @RequestParam(required = false) String areaCode,
                                 @RequestParam(required = false) String jieZhState) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkJZRegisterService.echartsDataYqfkJZzzmm(start, end, areaCode,jieZhState);
    }


    /**
     * 疫苗接种登记统计分析--按照接种状态统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/yqfkJZstatus.html", produces = BaseConstant.HTML)
    public String analysisYqfkJZStatus(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/yqfkJZstatus_index";
    }


    @ResponseBody
    @PostMapping(value = "a/analysis/yqfkJZstatusData.json", produces = BaseConstant.JSON)
    public String yqfkJZstatusData(@RequestParam(required = false) Long startTime,
                                   @RequestParam(required = false) Long endTime,
                                   @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkJZRegisterService.echartsDataStatus(start, end, areaCode);
    }


    /**
     * 疫苗接种登记统计分析--按照不接种原因统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/yqfkNoJZstatus.html", produces = BaseConstant.HTML)
    public String yqfkNoJZstatus(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/yqfkNoJZstatus_index";
    }

    @ResponseBody
    @PostMapping(value = "a/analysis/yqfkNoJZstatusData.json", produces = BaseConstant.JSON)
    public String yqfkNoJZstatusData(@RequestParam(required = false) Long startTime,
                                     @RequestParam(required = false) Long endTime,
                                     @RequestParam(required = false) String areaCode) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkJZRegisterService.echartsDataNoJZStatus(start, end, areaCode);
    }

    /**
     * 疫苗接种登记统计分析--按照接种状态统计
     * @param model
     * @return
     */
    @GetMapping(value = "a/analysis/yqfkJZplace.html", produces = BaseConstant.HTML)
    public String analysisYqfkJZPlace(Model model) {
        model.addAttribute("areaList", townshipService.areaList());
        return "web/analysis/yqfkJZplace_index";
    }


    @ResponseBody
    @PostMapping(value = "a/analysis/yqfkJZplaceData.json", produces = BaseConstant.JSON)
    public String yqfkJZplaceData(@RequestParam(required = false) Long startTime,
                                   @RequestParam(required = false) Long endTime,
                                   @RequestParam(required = false) String areaCode,
                                  @RequestParam(required = false) String dateFirst,
                                  @RequestParam(required = false) String addressFirst,
                                  @RequestParam(required = false) String dateSecond,
                                  @RequestParam(required = false) String addressSecond,
                                  @RequestParam(required = false) String dateThird,
                                  @RequestParam(required = false) String addressThird) {
        String start = startTime != null ? TimeUtil.format(startTime, BaseConstant.DATE_FORMAT2) : "";
        String end = endTime != null ? TimeUtil.format(endTime, BaseConstant.DATE_FORMAT2) : "";
        return yqfkJZRegisterService.echartsDataPlace(start, end, areaCode,dateFirst,addressFirst,dateSecond,addressSecond,dateThird,addressThird);
    }
}