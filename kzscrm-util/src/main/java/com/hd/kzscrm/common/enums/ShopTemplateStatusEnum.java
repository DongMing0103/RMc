package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 店铺模板状态枚举
 *
 * @author: wuying
 * @since:
 */
public enum ShopTemplateStatusEnum {

	TEMPLATE_TYPE_0_DTRAFT("0", "未启用"), 
	TEMPLATE_TYPE_1_START("1", "启用"), 
	TEMPLATE_TYPE_7_STOP_PROCESS("7", "停用处理中"),
	TEMPLATE_TYPE_8_STOP("8", "停用");

	String code; // 状态编号
	String name; // 状态名称

	ShopTemplateStatusEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public static List<ShopTemplateStatusEnum> getStarLevelEnumList() {
		List<ShopTemplateStatusEnum> list = Arrays.asList(ShopTemplateStatusEnum.values());
		Collections.sort(list, new Comparator<ShopTemplateStatusEnum>() {
			public int compare(ShopTemplateStatusEnum obj1, ShopTemplateStatusEnum obj2) {
				// 比较每个ArrayList的第二个元素
				Integer code1 = Integer.valueOf(obj1.getCode());
				Integer code2 = Integer.valueOf(obj2.getCode());
				if (code1 == code2) {
					return 0;
				} else if (code1 > code2) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		return list;
	}

	public static String getName(String code) {
		for (ShopTemplateStatusEnum articleTypeEnum : ShopTemplateStatusEnum.values()) {
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
