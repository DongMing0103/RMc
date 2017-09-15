package com.hd.kzscrm.common.param;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 基础属性值
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class BaseParam implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 8531892087905296967L;
    @JsonInclude(Include.NON_EMPTY)
    private LinkedHashMap<String, SortType> sortMap;

    public enum SortType {
        ASC, DESC
    }

    public LinkedHashMap<String, SortType> getSortMap() {
        return sortMap;
    }

    public void setSortMap(LinkedHashMap<String, SortType> sortMap) {
        this.sortMap = sortMap;
    }

    public void addSort(String order, SortType sort) {
        if (sortMap == null) {
            sortMap = new LinkedHashMap<String, SortType>();
        }
        sortMap.put(order, sort);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
