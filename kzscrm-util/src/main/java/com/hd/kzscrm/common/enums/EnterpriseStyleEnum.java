package com.hd.kzscrm.common.enums;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 单位类型
 * @author jyt 2017年4月5日 下午6:51:50
 *
 */
public enum EnterpriseStyleEnum {
    COMPANY("0", "公司/企业/工厂"), 
    SCHOOL("1", "学校"), 
    GOVERNMENT("2", "政府机构"), 
    INSTITUTION("3", "事业单位"), 
    OTHER("4", "其它");

    String code; // 状态编号
    String name; //状态名称

    EnterpriseStyleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public static List<EnterpriseStyleEnum> getEnumList(){
        List<EnterpriseStyleEnum> list = Arrays.asList(EnterpriseStyleEnum.values());
        Collections.sort(list,new Comparator<EnterpriseStyleEnum>(){
            public int compare(EnterpriseStyleEnum obj1, EnterpriseStyleEnum obj2) {
                //比较每个ArrayList的第二个元素
                int code1= Integer.parseInt(obj1.getCode());
                int code2= Integer.parseInt(obj2.getCode());
                if (code1 == code2){
                    return 0;
                } else if(code1 < code2){
                    return -1;
                } else{
                    return 1;
                }
            }
        });
        return list;
    }


    public static String getName(String code) {
        for (EnterpriseStyleEnum statusEnum : EnterpriseStyleEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
    public Integer getIntCode() {
        return Integer.valueOf(code);
    }
    public void setCode(String code) {
        this.code = code;
    }
    public Short getShortCode() {
        return Short.valueOf(code);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
