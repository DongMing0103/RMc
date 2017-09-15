package com.hd.kzscrm.common.enums.value;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 *初始化值
 *@author: wuying
 * @since: 2016年9月14日13:31:41
 */
public enum SiteDefaultValueEnum {

	CATE_PARENT_DEFAULT_VALUE("分类父节点初始化值", 0L);

	String name;
	Long value;

	SiteDefaultValueEnum(String name, Long value) {
		this.name = name;
		this.value = value;
	}

	public static List<SiteDefaultValueEnum> getAccountStatusEnumList() {
		List<SiteDefaultValueEnum> list = Arrays.asList(SiteDefaultValueEnum.values());
		Collections.sort(list, new Comparator<SiteDefaultValueEnum>() {
			public int compare(SiteDefaultValueEnum obj1, SiteDefaultValueEnum obj2) {
				// 比较每个ArrayList的第二个元素
				Long code1 = obj1.getValue();
				Long code2 = obj2.getValue();
				if (code1.equals(code2)) {
					return 0;
				} else if (code1 < code2) {
					return -1;
				} else {
					return 1;
				}
			}
		});
		return list;
	}

	public static Long getValue(String name) {
		for (SiteDefaultValueEnum orderStatusEnum : SiteDefaultValueEnum.values()) {
			if (orderStatusEnum.getName().equals(name)) {
				return orderStatusEnum.getValue();
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

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}
}
