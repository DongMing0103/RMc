package com.hd.kzscrm.common.enums;


/**
 * 文章类型特殊类型枚举
 *
 * @author: kzs admin
 * @since:
 */
public enum ArticleTypeEnum {

    ARTICLE_TYPE_454(454L, "新手指南"), ARTICLE_TYPE_455(455L, "关于筷子说"), ARTICLE_TYPE_456(456L, "关于我们");

    Long code; // 状态编号
    String name; //状态名称

    ArticleTypeEnum(Long code, String name) {
        this.code = code;
        this.name = name;
    }

    public static String getName(Long code) {
        for (ArticleTypeEnum articleTypeEnum : ArticleTypeEnum.values()) {
            if (articleTypeEnum.getCode().equals(code)) {
                return articleTypeEnum.getName();
            }
        }
        return null;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
