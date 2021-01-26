package com.hengtianyi.dims.service.api.impl;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericServiceImpl;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.constant.RoleEnum;
import com.hengtianyi.dims.service.api.IncorruptAdviceService;
import com.hengtianyi.dims.service.api.TownshipService;
import com.hengtianyi.dims.service.api.YqfkRegisterService;
import com.hengtianyi.dims.service.dao.IncorruptAdviceDao;
import com.hengtianyi.dims.service.dao.RegionDao;
import com.hengtianyi.dims.service.dao.VillageDao;
import com.hengtianyi.dims.service.dao.YqfkRegisterDao;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * IncorruptAdvice接口的实现类
 *
 * @author LY
 */
@Service
public class YqfkRegisterServiceImpl extends
        AbstractGenericServiceImpl<YqfkRegisterEntity, String>
        implements YqfkRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YqfkRegisterServiceImpl.class);

    @Resource
    private YqfkRegisterDao yqfkRegisterDao;

    @Resource
    private VillageDao villageDao;
    @Resource
    private RegionDao regionDao;
    /**
     * 实体与数据表字段的映射
     * <p>格式：实体字段 - 数据库字段</p>
     */
    private static final Map<String, String> PROPERTY_COLUMN;

    /**
     * 默认排序的sql，order by后面的部分，例如: id asc,name desc
     */
    private static final String DEFAULT_ORDER_SQL = "id asc";

    static {
        PROPERTY_COLUMN = new HashMap<>(3);
        // 格式：实体字段名 - 数据库字段名
        PROPERTY_COLUMN.put("id", "ID");
        PROPERTY_COLUMN.put("name", "NAME");
        PROPERTY_COLUMN.put("sex", "SEX");
        /* PROPERTY_COLUMN.put("createTime", "CREATE_TIME");*/
    }

    /**
     * 注入Mybatis的Dao
     *
     * @return incorruptAdviceDao
     */
    @Override
    public YqfkRegisterDao getDao() {
        return yqfkRegisterDao;
    }

    /**
     * 注入实体与数据库字段的映射
     *
     * @return key：实体属性名，value：数据库字段名
     */
    @Override
    public Map<String, String> getPropertyColumn() {
        return PROPERTY_COLUMN;
    }

    /**
     * 注入排序集合
     *
     * @return 排序集合
     */
    @Override
    public String getDefaultSort() {
        return DEFAULT_ORDER_SQL;
    }

    /**
     * 自定义分页
     *
     * @param dto dto
     * @return
     */
    @Override
    public CommonEntityDto<YqfkRegisterEntity> pagelist(QueryDto dto) {
        Integer count = yqfkRegisterDao.pagecount(dto);
        List<YqfkRegisterEntity> list = yqfkRegisterDao.pagelist(dto);
        CommonEntityDto<YqfkRegisterEntity> result = new CommonEntityDto<>(list);
        result.setCurrentPage(dto.getCurrentPage());
        result.setSize(FrameConstant.PAGE_SIZE);
        result.setTotal(count);
        return result;
    }

    /**
     * @param dto
     * @return
     */
    @Override
    public List<YqfkRegisterEntity> listData(QueryDto dto) {
        dto.setFirst((dto.getCurrentPage() - 1) * FrameConstant.PAGE_SIZE);
        dto.setEnd(dto.getCurrentPage() * FrameConstant.PAGE_SIZE);
        return yqfkRegisterDao.pagelist(dto);
    }


    @Override
    public String echartsData(String startTime, String endTime, String areaCode) {
        return "";
    }

    /**
     * echartsDataStatus
     * 状态
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param areaCode  乡镇编号
     * @return json
     */
    @Override
    public String echartsDataStatus(String startTime, String endTime, String areaCode) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkRegisterEntity> yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataStatus(startTime, endTime, areaCode);
            Map<String, Object> map = countStatus(yqfkRegisterEntityList);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];
            String[] beenhomes = new String[villageList.size()];
            String[] planhomes = new String[villageList.size()];
            String[] nohomes = new String[villageList.size()];
            String[] reports = new String[villageList.size()];
            String[] sums = new String[villageList.size()];

            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkRegisterEntityList = yqfkRegisterDao
                        .getEchartsDataStatus(startTime, endTime, village.getAreaCode());
                Map<String, Object> dataMap = countStatus(yqfkRegisterEntityList);
                villageNames[i] = village.getAreaName();
                beenhomes[i] = dataMap.get("beenhome").toString();
                planhomes[i] = dataMap.get("planhome").toString();
                nohomes[i] = dataMap.get("nohome").toString();
                reports[i] = dataMap.get("report").toString();
                sums[i] = dataMap.get("sum").toString();
            }

            map.put("villageNames", villageNames);
            map.put("beenhomes", beenhomes);
            map.put("planhomes", planhomes);
            map.put("nohomes", nohomes);
            map.put("reports", reports);
            map.put("sum", sums);
            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataStatus]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }

    private Map<String, Object> countStatus(List<YqfkRegisterEntity> list) {
        Map<String, Object> map = new HashMap<>();
        Integer beenhome = 0;
        Integer planhome = 0;
        Integer nohome = 0;
        Integer report = 0;
        Integer sum = 0;

        for (YqfkRegisterEntity entity : list) {
            if (entity.getReturnState() == null) {
                continue;
            }
            if (entity.getReturnState().equals("已返乡")) {
                beenhome += 1;
            } else if (entity.getReturnState().equals("拟返乡")) {
                planhome += 1;
            } else if (entity.getReturnState().equals("不返乡")) {
                nohome += 1;
            } else if (entity.getReturnState().equals("已上报社区")) {
                report += 1;
            }
            if (entity.getReturnState().equals("已返乡") || entity.getReturnState().equals("拟返乡") || entity.getReturnState().equals("不返乡") || entity.getReturnState().equals("已上报社区")) {
                sum += 1;
            }
        }
        map.put("beenhome", beenhome);
        map.put("planhome", planhome);
        map.put("nohome", nohome);
        map.put("report", report);
        map.put("sum", sum);
        return map;
    }


    /**
     * echartsDataVehicle
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param areaCode  乡镇编号
     * @return json
     */
    @Override
    public String echartsDataVehicle(String startTime, String endTime, String areaCode, String retrunState) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkRegisterEntity> yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataVehicle(startTime, endTime, areaCode, retrunState);
            Map<String, Object> map = countVehicle(yqfkRegisterEntityList, retrunState);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];
            String[] zjs = new String[villageList.size()];
            String[] planejs = new String[villageList.size()];
            String[] trains = new String[villageList.size()];
            String[] buss = new String[villageList.size()];
            String[] wybuss = new String[villageList.size()];
            String[] sum = new String[villageList.size()];
            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkRegisterEntityList = yqfkRegisterDao
                        .getEchartsDataVehicle(startTime, endTime, village.getAreaCode(), retrunState);
                Map<String, Object> dataMap = countVehicle(yqfkRegisterEntityList, retrunState);
                villageNames[i] = village.getAreaName();
                zjs[i] = dataMap.get("zj").toString();
                planejs[i] = dataMap.get("planej").toString();
                trains[i] = dataMap.get("train").toString();
                buss[i] = dataMap.get("bus").toString();
                wybuss[i] = dataMap.get("wybus").toString();
                sum[i] = dataMap.get("sum").toString();
            }

            map.put("villageNames", villageNames);
            map.put("zjs", zjs);
            map.put("planejs", planejs);
            map.put("trains", trains);
            map.put("buss", buss);
            map.put("wybuss", wybuss);
            map.put("sum", sum);
            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataVehicle]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }

    private Map<String, Object> countVehicle(List<YqfkRegisterEntity> list, String returnState) {
        Map<String, Object> map = new HashMap<>();
        Integer zj = 0;
        Integer planej = 0;
        Integer train = 0;
        Integer bus = 0;
        Integer wybus = 0;
        Integer sum = 0;

        for (YqfkRegisterEntity entity : list) {
            if (entity.getExpReturnWay() != null && !"".equals(entity.getExpReturnWay()) && entity.getReturnState().equals("拟返乡")) {
                if (entity.getExpReturnWay() != null && !"".equals(entity.getExpReturnWay()) && entity.getExpReturnWay().contains("自驾")) {
                    zj += 1;
                } else if (entity.getExpReturnWay() != null && !"".equals(entity.getExpReturnWay()) && entity.getExpReturnWay().contains("飞机")) {
                    planej += 1;
                } else if (entity.getExpReturnWay() != null && !"".equals(entity.getExpReturnWay()) && entity.getExpReturnWay().contains("火车")) {
                    train += 1;
                } else if (entity.getExpReturnWay() != null && !"".equals(entity.getExpReturnWay()) && entity.getExpReturnWay().contains("客车")) {
                    bus += 1;
                } else if (entity.getExpReturnWay() != null && !"".equals(entity.getExpReturnWay()) && entity.getExpReturnWay().contains("网约车")) {
                    wybus += 1;
                }
            } else {
                if (entity.getReturnWay() != null && !"".equals(entity.getReturnWay()) && entity.getReturnWay().contains("自驾")) {
                    zj += 1;
                } else if (entity.getReturnWay() != null && !"".equals(entity.getReturnWay()) && entity.getReturnWay().contains("飞机")) {
                    planej += 1;
                } else if (entity.getReturnWay() != null && !"".equals(entity.getReturnWay()) && entity.getReturnWay().contains("火车")) {
                    train += 1;
                } else if (entity.getReturnWay() != null && !"".equals(entity.getReturnWay()) && entity.getReturnWay().contains("客车")) {
                    bus += 1;
                } else if (entity.getReturnWay() != null && !"".equals(entity.getReturnWay()) && entity.getReturnWay().contains("网约车")) {
                    wybus += 1;
                }
            }
            sum += 1;
        }

        map.put("zj", zj);
        map.put("planej", planej);
        map.put("train", train);
        map.put("bus", bus);
        map.put("wybus", wybus);
        map.put("sum", sum);
        return map;
    }


    @Override
    public String echartsDataBefore(String startTime, String endTime, String areaCode, String beforeAreaPbm, String beforeAreaCbm, String beforeAreaXbm) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkRegisterEntity> yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataBefore(startTime, endTime, areaCode, beforeAreaPbm, beforeAreaCbm, beforeAreaXbm);
            Map<String, Object> map = countBefore(yqfkRegisterEntityList);
            List<Region> beforeAreaList = new ArrayList<Region>();
            System.out.printf(String.valueOf(beforeAreaList));
            if (beforeAreaPbm.length() == 0) {
                beforeAreaList = regionDao.getProvince();
            } else {
                if (beforeAreaCbm.length() == 0) {
                    Region reg = regionDao.findByCode(beforeAreaPbm);
                    beforeAreaList = regionDao.getListByParent(reg.getId().replace(" ", ""));
                } else {
                    Region reg = regionDao.findByCode(beforeAreaCbm);
                    beforeAreaList = regionDao.getListByParent(reg.getId().replace(" ", ""));
                }
            }
            String[] beforeAreaNames = new String[beforeAreaList.size()];
            String[] beenhomes = new String[beforeAreaList.size()];
            String[] planhomes = new String[beforeAreaList.size()];
            String[] nohomes = new String[beforeAreaList.size()];
            String[] reports = new String[beforeAreaList.size()];
            String[] sum = new String[beforeAreaList.size()];
            for (int i = 0; i < beforeAreaList.size(); i++) {
                beforeAreaNames[i] = beforeAreaList.get(i).getPname();
            }

            for (int i = 0; i < beforeAreaList.size(); i++) {
                Region region = beforeAreaList.get(i);
                if (region.getPlevel() == 1) {
                    yqfkRegisterEntityList = yqfkRegisterDao
                            .getEchartsDataBefore(startTime, endTime, areaCode, region.getPcode(), beforeAreaCbm, beforeAreaXbm);
                }
                if (region.getPlevel() == 2) {
                    yqfkRegisterEntityList = yqfkRegisterDao
                            .getEchartsDataBefore(startTime, endTime, areaCode, beforeAreaPbm, region.getPcode(), beforeAreaXbm);
                }
                if (region.getPlevel() == 3) {
                    yqfkRegisterEntityList = yqfkRegisterDao
                            .getEchartsDataBefore(startTime, endTime, areaCode, beforeAreaPbm, beforeAreaCbm, region.getPcode());
                }

                Map<String, Object> dataMap = countBefore(yqfkRegisterEntityList);
                beforeAreaNames[i] = region.getPname();
                beenhomes[i] = dataMap.get("beenhome").toString();
                planhomes[i] = dataMap.get("planhome").toString();
                nohomes[i] = dataMap.get("nohome").toString();
                reports[i] = dataMap.get("report").toString();
                sum[i] = dataMap.get("sum").toString();
            }

            map.put("beforeAreaNames", beforeAreaNames);
            map.put("beenhomes", beenhomes);
            map.put("planhomes", planhomes);
            map.put("nohomes", nohomes);
            map.put("reports", reports);
            map.put("sum", sum);
            result.setSuccess(true);
            result.setResult(map);

        } catch (Exception e) {
            LOGGER.error("[echartsDataBefore]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }

    private Map<String, Object> countBefore(List<YqfkRegisterEntity> list) {

        Map<String, Object> map = new HashMap<>();
        Integer beenhome = 0;
        Integer planhome = 0;
        Integer nohome = 0;
        Integer report = 0;
        Integer sum = 0;
        for (YqfkRegisterEntity entity : list) {
            if (entity.getReturnState() == null) {
                continue;
            }
            if (entity.getReturnState().equals("已返乡")) {
                beenhome += 1;
            } else if (entity.getReturnState().equals("拟返乡")) {
                planhome += 1;
            } else if (entity.getReturnState().equals("不返乡")) {
                nohome += 1;
            } else if (entity.getReturnState().equals("已上报社区")) {
                report += 1;
            }
            if (entity.getReturnState().equals("已返乡") || entity.getReturnState().equals("拟返乡") || entity.getReturnState().equals("不返乡") || entity.getReturnState().equals("已上报社区")) {
                sum += 1;
            }
        }
        map.put("beenhome", beenhome);
        map.put("planhome", planhome);
        map.put("nohome", nohome);
        map.put("report", report);
        map.put("sum", sum);
        return map;
    }


    /**
     * echartsDataIndustry
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param areaCode  乡镇编号
     * @return json
     */
    @Override
    public String echartsDataIndustry(String startTime, String endTime, String areaCode, String returnState) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkRegisterEntity> yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataIndustry(startTime, endTime, areaCode, returnState);
            Map<String, Object> map = countIndustry(yqfkRegisterEntityList);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];

            String[] lenglians = new String[villageList.size()];
            String[] businesss = new String[villageList.size()];
            String[] huoyuns = new String[villageList.size()];
            String[] jiguans = new String[villageList.size()];
            String[] kouans = new String[villageList.size()];
            String[] gelis = new String[villageList.size()];
            String[] students = new String[villageList.size()];
            String[] wuyes = new String[villageList.size()];
            String[] others = new String[villageList.size()];
            String[] sum = new String[villageList.size()];
            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataIndustry(startTime, endTime, village.getAreaCode(), returnState);
                Map<String, Object> dataMap = countIndustry(yqfkRegisterEntityList);
                villageNames[i] = village.getAreaName();

                lenglians[i] = dataMap.get("lenglian").toString();
                businesss[i] = dataMap.get("business").toString();
                huoyuns[i] = dataMap.get("huoyun").toString();
                jiguans[i] = dataMap.get("jiguan").toString();
                kouans[i] = dataMap.get("kouan").toString();
                gelis[i] = dataMap.get("geli").toString();
                students[i] = dataMap.get("student").toString();
                wuyes[i] = dataMap.get("wuye").toString();
                others[i] = dataMap.get("other").toString();
                sum[i] = dataMap.get("sum").toString();
            }
            map.put("villageNames", villageNames);
            map.put("lenglians", lenglians);
            map.put("businesss", businesss);
            map.put("huoyuns", huoyuns);
            map.put("jiguans", jiguans);
            map.put("kouans", kouans);
            map.put("gelis", gelis);
            map.put("students", students);
            map.put("wuyes", wuyes);
            map.put("others", others);
            map.put("sum", sum);

            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataIndustry]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }

    private Map<String, Object> countIndustry(List<YqfkRegisterEntity> list) {
        Map<String, Object> map = new HashMap<>();
        Integer lenglian = 0;
        Integer business = 0;
        Integer huoyun = 0;
        Integer jiguan = 0;
        Integer kouan = 0;
        Integer geli = 0;
        Integer student = 0;
        Integer wuye = 0;
        Integer other = 0;
        Integer sum = 0;

        for (YqfkRegisterEntity entity : list) {

            if (entity.getIndustray() != null && entity.getIndustray().equals("冷链从业人员")) {
                lenglian += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("商业从业人员")) {
                business += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("交通运输工具从业人员")) {
                huoyun += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("机关事业单位")) {
                jiguan += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("学生")) {
                student += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("口岸直接接触进口货物从业人员")) {
                kouan += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("隔离场所工作人员")) {
                geli += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("无业")) {
                wuye += 1;
            } else if (entity.getIndustray() != null && entity.getIndustray().equals("其它")) {
                other += 1;
            }
            sum += 1;
        }
        map.put("lenglian", lenglian);
        map.put("business", business);
        map.put("huoyun", huoyun);
        map.put("jiguan", jiguan);
        map.put("kouan", kouan);
        map.put("geli", geli);
        map.put("student", student);
        map.put("wuye", wuye);
        map.put("other", other);
        map.put("sum", sum);
        return map;
    }

    private Map<String, Object> countRisk(List<YqfkRegisterEntity> list, String returnState) {
        Map<String, Object> map = new HashMap<>();
        Integer wfx = 0;
        Integer dfx = 0;
        Integer zfx = 0;
        Integer gfx = 0;
        Integer sum = 0;

        for (YqfkRegisterEntity entity : list) {
            if (entity.getRiskLevel() != null && entity.getRiskLevel().equals("0")) {
                wfx += 1;
            } else if (entity.getRiskLevel() != null && entity.getRiskLevel().equals("1")) {
                dfx += 1;
            } else if (entity.getRiskLevel() != null && entity.getRiskLevel().equals("2")) {
                zfx += 1;
            } else if (entity.getRiskLevel() != null && entity.getRiskLevel().equals("3")) {
                gfx += 1;
            }
            sum += 1;
        }
        map.put("wfx", wfx);
        map.put("dfx", dfx);
        map.put("zfx", zfx);
        map.put("gfx", gfx);
        map.put("sum", sum);
        return map;
    }


    /**
     * echartsDataRisk
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param areaCode  乡镇编号
     * @return json
     */
    @Override
    public String echartsDataRisk(String startTime, String endTime, String areaCode, String returnState) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkRegisterEntity> yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataRisk(startTime, endTime, areaCode, returnState);
            Map<String, Object> map = countRisk(yqfkRegisterEntityList, returnState);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];

            String[] wfxs = new String[villageList.size()];
            String[] dfxs = new String[villageList.size()];
            String[] zfxs = new String[villageList.size()];
            String[] gfxs = new String[villageList.size()];
            String[] sum = new String[villageList.size()];
            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkRegisterEntityList = yqfkRegisterDao.getEchartsDataRisk(startTime, endTime, village.getAreaCode(), returnState);
                Map<String, Object> dataMap = countRisk(yqfkRegisterEntityList, returnState);
                villageNames[i] = village.getAreaName();

                wfxs[i] = dataMap.get("wfx").toString();
                dfxs[i] = dataMap.get("dfx").toString();
                zfxs[i] = dataMap.get("zfx").toString();
                gfxs[i] = dataMap.get("gfx").toString();
                sum[i] = dataMap.get("sum").toString();
            }
            map.put("villageNames", villageNames);
            map.put("wfxs", wfxs);
            map.put("dfxs", dfxs);
            map.put("zfxs", zfxs);
            map.put("gfxs", gfxs);
            map.put("sum", sum);
            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataRisk]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }


    @Override
    public Integer countAdvice(QueryDto dto) {
        return yqfkRegisterDao.pagecount(dto);
    }

    @Override
    public List<YqfkRegisterEntity> checkCard(String card) {
        return yqfkRegisterDao.checkCard(card);
    }

    @Override
    public Integer getRiskCount(String userId) {
        return yqfkRegisterDao.getRiskCount(userId);
    }

    @Override
    public Integer getExpCount(YqfkRegisterEntity entity) {
        return yqfkRegisterDao.getExpCount(entity);
    }

    @Override
    public Integer getCount(YqfkRegisterEntity entity) {
        return yqfkRegisterDao.getCount(entity);
    }

    @Override
    public List<YqfkRegisterEntity> searchAllData(YqfkRegisterEntity dto) {
        return yqfkRegisterDao.searchAllData(dto);
    }

}
