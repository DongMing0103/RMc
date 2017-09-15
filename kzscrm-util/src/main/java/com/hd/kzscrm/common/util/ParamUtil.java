package com.hd.kzscrm.common.util;

import java.util.Map;

import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.util.ParamMap;
import com.hd.kzscrm.common.param.BaseParam;
import com.hd.kzscrm.common.param.PageQueryParam;

/**
 * 参数类型转换类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class ParamUtil {

    /**
     * 功能说明：获取分页参数
     *
     * @param entityType
     * @param pageQueryParam
     * @return Page<T>
     */
    public static <T> Page<T> getPage(Class<T> entityType, PageQueryParam pageQueryParam) {
        Page<T> page = new Page<T>();
        page.setPageSize(pageQueryParam.getLimit());
        page.setCurrentResult(pageQueryParam.getStart() - 1);
        return page;
    }

    /**
     * 功能说明：从页面参数中获取查询参数
     *
     * @param entity
     * @return ParamMap
     */
    public static ParamMap getParamMap(Object entity) {
        ParamMap paramMap = new ParamMap();
        Map<?, ?> map = BeanConvertor.convertBean(entity, Map.class);
        paramMap.putAll(map);
        if (entity instanceof BaseParam) {
            BaseParam sortQueryParam = (BaseParam) entity;
            setOrderMap(paramMap, sortQueryParam);
        }
        return paramMap;
    }

    /**
     * 设置查询排序
     *
     * @param paramMap
     * @param baseParam
     */
    public static void setOrderMap(ParamMap paramMap, BaseParam baseParam) {
        if (null == baseParam || null == baseParam.getSortMap()) return;
        for (Map.Entry<String, BaseParam.SortType> entry : baseParam.getSortMap().entrySet()) {
            paramMap.addOrder(entry.getKey(), entry.getValue().name());
        }
    }
}
