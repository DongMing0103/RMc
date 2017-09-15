package com.hd.kzscrm.common.param;

/**
 * Created by jiangjianwei
 * DATE: 2016/7/13.
 */
public class CommonParam {

    /**分类名称*/
    private String classify;

    /**数据字典编码*/
    private String code;

    /**数据字典父节点编码*/
    private String parentCode;

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
