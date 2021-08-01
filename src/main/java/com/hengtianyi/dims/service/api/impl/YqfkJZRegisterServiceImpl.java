package com.hengtianyi.dims.service.api.impl;

import com.hengtianyi.common.core.base.CommonEntityDto;
import com.hengtianyi.common.core.base.service.AbstractGenericServiceImpl;
import com.hengtianyi.dims.constant.FrameConstant;
import com.hengtianyi.dims.service.api.YqfkJZRegisterService;
import com.hengtianyi.dims.service.dao.RegionDao;
import com.hengtianyi.dims.service.dao.VillageDao;
import com.hengtianyi.dims.service.dao.YqfkJZRegisterDao;
import com.hengtianyi.dims.service.dao.YqfkRegisterDao;
import com.hengtianyi.dims.service.dto.QueryDto;
import com.hengtianyi.dims.service.entity.YqfkJZRegisterEntity;
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

}
