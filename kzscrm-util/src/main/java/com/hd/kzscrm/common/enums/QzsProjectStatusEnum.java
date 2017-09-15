package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 解决方案文章状态枚举值
 * 
 * @author wing5
 *
 */
public enum QzsProjectStatusEnum {

	TYPE_0_DTRAFT(Short.valueOf("0"), "启用"), TYPE_8_STOP(Short.valueOf("8"), "停用");

	Short value; // 状态编号

	String name; // 状态名称

	QzsProjectStatusEnum(Short value, String name) {
		this.value = value;
		this.name = name;
	}

	public static List<QzsProjectStatusEnum> getStarLevelEnumList() {
		List<QzsProjectStatusEnum> list = Arrays
				.asList(QzsProjectStatusEnum.values());
		Collections.sort(list, new Comparator<QzsProjectStatusEnum>() {
			public int compare(QzsProjectStatusEnum obj1,
					QzsProjectStatusEnum obj2) {
				// 比较每个ArrayList的第二个元素
				Integer code1 = Integer.valueOf(obj1.getValue());
				Integer code2 = Integer.valueOf(obj2.getValue());
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
		for (ShopTemplateStatusEnum articleTypeEnum : ShopTemplateStatusEnum
				.values()) {
			if (articleTypeEnum.getCode().equals(code)) {
				return articleTypeEnum.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getValue() {
		return value;
	}

	public void setValue(Short value) {
		this.value = value;
	}
}
