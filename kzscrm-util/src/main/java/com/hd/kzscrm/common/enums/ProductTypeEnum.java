package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public enum ProductTypeEnum {
	CATEGORYTY_TYPE_0("1", "普通产品"),
	CATEGORYTY_TYPE_1("2", "主打产品");
    
    String code ;
	String name ;
	
	ProductTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<ProductTypeEnum> getStarLevelEnumList(){
        List<ProductTypeEnum> list = Arrays.asList(ProductTypeEnum.values());
        Collections.sort(list,new Comparator<ProductTypeEnum>(){
            public int compare(ProductTypeEnum obj1, ProductTypeEnum obj2) {
                //比较每个ArrayList的第二个元素
                Integer code1= Integer.valueOf(obj1.getCode());
                Integer code2= Integer.valueOf(obj2.getCode());
                if (code1 == code2){
                    return 0;
                }else if(code1>code2){
                    return -1;
                }else{
                    return 1;
                }
            }
        });
        return list;
    }
    
    
    public static String getName(String code) {
        for (ProductTypeEnum articleTypeEnum : ProductTypeEnum.values()) {
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
