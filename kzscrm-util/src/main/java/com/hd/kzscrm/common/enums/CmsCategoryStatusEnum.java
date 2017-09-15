package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文章分类状态枚举
 * 
 * @author caiwl
 *
 */
public enum CmsCategoryStatusEnum {

    CATEGORY_STATUS_0("0", "未启用"),
    CATEGORY_STATUS_1("1", "启用"),
    CATEGORY_STATUS_8("8", "停用");

    String code;
    String name;

    CmsCategoryStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<CmsCategoryStatusEnum> getStarLevelEnumList() {
        List<CmsCategoryStatusEnum> list = Arrays.asList(CmsCategoryStatusEnum.values());
        Collections.sort(list, new Comparator<CmsCategoryStatusEnum>() {
            public int compare(CmsCategoryStatusEnum obj1, CmsCategoryStatusEnum obj2) {
                Integer code1 = Integer.valueOf(obj1.getCode());
                Integer code2 = Integer.valueOf(obj2.getCode());
                if (code1 == code2) {
                    return 0;
                } else if (code1 > code2) {
//                // 按code大小倒序
//                    return -1;
//                } else {
//                    return 1;
//                }
                // 按code大小顺序
                    return 1;
                } else {
                    return -1;
                }
            }
        });
        return list;
    }

    public static String getName(String code) {
        for (CmsCategoryStatusEnum articleTypeEnum : CmsCategoryStatusEnum.values()) {
            if (articleTypeEnum.getCode().equals(code)) {
                return articleTypeEnum.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
