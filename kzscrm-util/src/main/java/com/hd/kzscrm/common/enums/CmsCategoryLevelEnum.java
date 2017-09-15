package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 文章分类级别枚举
 * 
 * @author caiwl
 *
 */
public enum CmsCategoryLevelEnum {

    CATEGORY_LEVEL_1("1", 1L),
    CATEGORY_LEVEL_2("2", 2L);

    String code;
    Long name;

    CmsCategoryLevelEnum(String code, Long name) {
        this.code = code;
        this.name = name;
    }

    public static List<CmsCategoryLevelEnum> getStarLevelEnumList() {
        List<CmsCategoryLevelEnum> list = Arrays.asList(CmsCategoryLevelEnum.values());
        Collections.sort(list, new Comparator<CmsCategoryLevelEnum>() {
            public int compare(CmsCategoryLevelEnum obj1, CmsCategoryLevelEnum obj2) {
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

    public static Long getName(String code) {
        for (CmsCategoryLevelEnum articleTypeEnum : CmsCategoryLevelEnum.values()) {
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

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

}
