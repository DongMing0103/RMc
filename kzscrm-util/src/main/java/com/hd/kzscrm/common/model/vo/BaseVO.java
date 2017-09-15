package com.hd.kzscrm.common.model.vo;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

import java.io.Serializable;

/**
 * 基础属性值
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class BaseVO implements Serializable {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -6834703654510591498L;

    public String keywords;


    /**
     * 开始时间
     * 结束时间
     */
    public String startDate;

    public String endDate;


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
