package com.hengtianyi.dims.service.api.impl;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericServiceImpl;
import com.hengtianyi.common.core.feature.ServiceResult;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.YqfkJZRegisterService;
import com.hengtianyi.dims.service.dao.RegionDao;
import com.hengtianyi.dims.service.dao.VillageDao;
import com.hengtianyi.dims.service.dao.YqfkJZRegisterDao;
import com.hengtianyi.dims.service.dao.YqfkRegisterDao;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.VillageEntity;
import com.hengtianyi.dims.service.entity.YqfkJZRegisterEntity;
import com.hengtianyi.dims.service.entity.YqfkRegisterEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * YqfkJZRegister接口的实现类
 *
 * @author LY
 */
@Service
public class YqfkJZRegisterServiceImpl extends
        AbstractGenericServiceImpl<YqfkJZRegisterEntity, String>
        implements YqfkJZRegisterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(YqfkJZRegisterServiceImpl.class);

    @Resource
    private YqfkJZRegisterDao yqfkJZRegisterDao;

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


    @Override
    public YqfkJZRegisterDao getDao() {
        return yqfkJZRegisterDao;
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
    public CommonEntityDto<YqfkJZRegisterEntity> pagelist(QueryDto dto) {
        Integer count = yqfkJZRegisterDao.pagecount(dto);
        List<YqfkJZRegisterEntity> list = yqfkJZRegisterDao.pagelist(dto);
        CommonEntityDto<YqfkJZRegisterEntity> result = new CommonEntityDto<>(list);
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
    public List<YqfkJZRegisterEntity> listData(QueryDto dto) {
        dto.setFirst((dto.getCurrentPage() - 1) * FrameConstant.PAGE_SIZE);
        dto.setEnd(dto.getCurrentPage() * FrameConstant.PAGE_SIZE);
        return yqfkJZRegisterDao.pagelist(dto);
    }



    @Override
    public List<YqfkJZRegisterEntity> checkCard(String card) {
        return yqfkJZRegisterDao.checkCard(card);
    }


    @Override
    public Integer getCount(YqfkJZRegisterEntity entity) {
        return yqfkJZRegisterDao.getCount(entity);
    }

    @Override
    public List<YqfkJZRegisterEntity> searchAllData(YqfkJZRegisterEntity dto) {
        return yqfkJZRegisterDao.searchAllData(dto);
    }


    /**
     * 工作单位统计
     */

    @Override
    public String echartsDataYqfkJZzzdw(String startTime, String endTime, String areaCode, String queryzzDWType1, String queryzzDWType2) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkJZRegisterEntity> yqfkJZRegisterEntityList = yqfkJZRegisterDao.getEchartsDataYqfkJZzzdw(startTime, endTime, areaCode, queryzzDWType1,queryzzDWType2);
            Map<String, Object> map = countStatus(yqfkJZRegisterEntityList);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];
            String[] beenhomes = new String[villageList.size()];
            String[] planhomes = new String[villageList.size()];
            String[] nohomes = new String[villageList.size()];
            String[] sums = new String[villageList.size()];

            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkJZRegisterEntityList = yqfkJZRegisterDao.getEchartsDataYqfkJZzzdw(startTime, endTime, village.getAreaCode(), queryzzDWType1,queryzzDWType2);
                Map<String, Object> dataMap = countStatus(yqfkJZRegisterEntityList);
                villageNames[i] = village.getAreaName();
                beenhomes[i] = dataMap.get("beenhome").toString();
                planhomes[i] = dataMap.get("planhome").toString();
                nohomes[i] = dataMap.get("nohome").toString();
                sums[i] = dataMap.get("sum").toString();
            }

            map.put("villageNames", villageNames);
            map.put("beenhomes", beenhomes);
            map.put("planhomes", planhomes);
            map.put("nohomes", nohomes);
            map.put("sum", sums);
            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataYqfkJZzzdw]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }


    /**
     * 政治面貌统计
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param areaCode  乡镇编号
     * @return json
     */
    @Override
    public String echartsDataYqfkJZzzmm(String startTime, String endTime, String areaCode, String jieZhState) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkJZRegisterEntity> yqfkJZRegisterEntityList = yqfkJZRegisterDao.getEchartsDataYqfkJZzzmm(startTime, endTime, areaCode, jieZhState);
            Map<String, Object> map = countYqfkJZzzmm(yqfkJZRegisterEntityList);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];

            String[] zgdys = new String[villageList.size()];
            String[] qunzhs = new String[villageList.size()];
            String[] others = new String[villageList.size()];
            String[] sum = new String[villageList.size()];
            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkJZRegisterEntityList = yqfkJZRegisterDao.getEchartsDataYqfkJZzzmm(startTime, endTime, village.getAreaCode(), jieZhState);
                Map<String, Object> dataMap = countYqfkJZzzmm(yqfkJZRegisterEntityList);
                villageNames[i] = village.getAreaName();

                zgdys[i] = dataMap.get("zgdy").toString();
                qunzhs[i] = dataMap.get("qunzh").toString();
                others[i] = dataMap.get("other").toString();
                sum[i] = dataMap.get("sum").toString();
            }
            map.put("villageNames", villageNames);
            map.put("zgdys", zgdys);
            map.put("qunzhs", qunzhs);
            map.put("others", others);
            map.put("sum", sum);

            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataYqfkJZzzmm]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }

    private Map<String, Object> countYqfkJZzzmm(List<YqfkJZRegisterEntity> list) {
        Map<String, Object> map = new HashMap<>();
        Integer zgdy = 0;
        Integer qunzh = 0;
        Integer other = 0;
        Integer sum = 0;


        for (YqfkJZRegisterEntity entity : list) {

            if (entity.getZzMM() != null && entity.getZzMM().equals("中共党员")) {
                zgdy += 1;
            } else if (entity.getZzMM() != null && entity.getZzMM().equals("群众")) {
                qunzh += 1;
            } else if (entity.getZzMM() != null && entity.getZzMM().equals("其他")) {
                other += 1;
            }
            sum += 1;
        }
        map.put("zgdy", zgdy);
        map.put("qunzh", qunzh);
        map.put("other", other);
        map.put("sum", sum);
        return map;
    }






    //不接种类原因统计

    @Override
    public String echartsDataNoJZStatus(String startTime, String endTime, String areaCode) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkJZRegisterEntity> yqfkJZRegisterEntityList = yqfkJZRegisterDao.getEchartsDataStatus(startTime, endTime, areaCode);
            Map<String, Object> map = countNoJZStatus(yqfkJZRegisterEntityList);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];
            String[] beenhomes = new String[villageList.size()];
            String[] planhomes = new String[villageList.size()];
            String[] nohomes = new String[villageList.size()];
            String[] beengelis = new String[villageList.size()];
            String[] readygos = new String[villageList.size()];

            String[] sums = new String[villageList.size()];

            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkJZRegisterEntityList = yqfkJZRegisterDao
                        .getEchartsDataStatus(startTime, endTime, village.getAreaCode());
                Map<String, Object> dataMap = countNoJZStatus(yqfkJZRegisterEntityList);
                villageNames[i] = village.getAreaName();
                beenhomes[i] = dataMap.get("beenhome").toString();
                planhomes[i] = dataMap.get("planhome").toString();
                nohomes[i] = dataMap.get("nohome").toString();
                beengelis[i] = dataMap.get("beengeli").toString();
                readygos[i] = dataMap.get("readygo").toString();

                sums[i] = dataMap.get("sum").toString();
            }

            map.put("villageNames", villageNames);
            map.put("beenhomes", beenhomes);
            map.put("planhomes", planhomes);
            map.put("nohomes", nohomes);
            map.put("beengelis", beengelis);
            map.put("readygos", readygos);
            map.put("sum", sums);
            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataNoJZStatus]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }



    private Map<String, Object> countNoJZStatus(List<YqfkJZRegisterEntity> list) {
        Map<String, Object> map = new HashMap<>();
        Integer beenhome = 0;
        Integer planhome = 0;
        Integer nohome = 0;
        Integer beengeli = 0;
        Integer readygo = 0;
        Integer sum = 0;

        for (YqfkJZRegisterEntity entity : list) {
            if (entity.getNoJieZhReasonType() == null) {
                continue;
            }
            if (entity.getNoJieZhReasonType().equals("禁忌症")) {
                beenhome += 1;
            } else if (entity.getNoJieZhReasonType().equals("到接种点后医生建议不接种")) {
                planhome += 1;
            } else if (entity.getNoJieZhReasonType().equals("不愿意接种")) {
                nohome += 1;
            } else if (entity.getNoJieZhReasonType().equals("正在隔离期")) {
                beengeli += 1;
            } else if (entity.getNoJieZhReasonType().equals("准备接种")) {
                readygo += 1;
            }
            sum += 1;
        }
        map.put("beenhome", beenhome);
        map.put("planhome", planhome);
        map.put("nohome", nohome);
        map.put("beengeli", beengeli);
        map.put("readygo", readygo);
        map.put("sum", sum);
        return map;
    }


    @Override
    public String echartsDataStatus(String startTime, String endTime, String areaCode) {
        ServiceResult<Object> result = new ServiceResult();
        try {
            List<YqfkJZRegisterEntity> yqfkJZRegisterEntityList = yqfkJZRegisterDao.getEchartsDataStatus(startTime, endTime, areaCode);
            Map<String, Object> map = countStatus(yqfkJZRegisterEntityList);
            List<VillageEntity> villageList = villageDao.areaList(areaCode);
            String[] villageNames = new String[villageList.size()];
            String[] beenhomes = new String[villageList.size()];
            String[] planhomes = new String[villageList.size()];
            String[] nohomes = new String[villageList.size()];
            String[] sums = new String[villageList.size()];

            for (int i = 0; i < villageList.size(); i++) {
                VillageEntity village = villageList.get(i);
                yqfkJZRegisterEntityList = yqfkJZRegisterDao
                        .getEchartsDataStatus(startTime, endTime, village.getAreaCode());
                Map<String, Object> dataMap = countStatus(yqfkJZRegisterEntityList);
                villageNames[i] = village.getAreaName();
                beenhomes[i] = dataMap.get("beenhome").toString();
                planhomes[i] = dataMap.get("planhome").toString();
                nohomes[i] = dataMap.get("nohome").toString();
                sums[i] = dataMap.get("sum").toString();
            }

            map.put("villageNames", villageNames);
            map.put("beenhomes", beenhomes);
            map.put("planhomes", planhomes);
            map.put("nohomes", nohomes);
            map.put("sum", sums);
            result.setSuccess(true);
            result.setResult(map);
        } catch (Exception e) {
            LOGGER.error("[echartsDataStatus]出错,{}", e.getMessage(), e);
            result.setError("false");
        }
        return result.toJson();
    }



    private Map<String, Object> countStatus(List<YqfkJZRegisterEntity> list) {
        Map<String, Object> map = new HashMap<>();
        Integer beenhome = 0;
        Integer planhome = 0;
        Integer nohome = 0;
        Integer sum = 0;

        for (YqfkJZRegisterEntity entity : list) {
            if (entity.getJieZhState() == null) {
                continue;
            }
            if (entity.getJieZhState().equals("未接种")) {
                beenhome += 1;
            } else if (entity.getJieZhState().equals("接种进行中")) {
                planhome += 1;
            } else if (entity.getJieZhState().equals("接种已完成")) {
                nohome += 1;
            }
            sum += 1;
        }
        map.put("beenhome", beenhome);
        map.put("planhome", planhome);
        map.put("nohome", nohome);
        map.put("sum", sum);
        return map;
    }

}
