package com.hd.kzscrm.common.param;

import com.hd.kzscrm.common.enums.DataListSetEnum;

/**
 *@author  lichangchao
 * 参数设置
 */
public class DataListSetParam    {
    public DataListSetEnum dataListSetEnum;
    public String inName;
    public String outName;

    public String getInName() {
        return inName;
    }

    public void setInName(String inName) {
        this.inName = inName;
    }

    public String getOutName() {
        return outName;
    }

    public void setOutName(String outName) {
        this.outName = outName;
    }

    public DataListSetEnum getDataListSetEnum() {
        return dataListSetEnum;
    }

    public void setDataListSetEnum(DataListSetEnum dataListSetEnum) {
        this.dataListSetEnum = dataListSetEnum;
    }
}
