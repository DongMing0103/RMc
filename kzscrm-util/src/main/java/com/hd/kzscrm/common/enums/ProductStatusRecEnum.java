package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 推荐状态枚举
 * @author wb-u16
 * @since
 *
 */
public enum ProductStatusRecEnum {
	
	PRODUCT_TYPE_0("0", "否"),
	PRODUCT_TYPE_1("1", "是");
//	PRODUCT_TYPE_2("2", "推荐置顶");
    
    String code ;
	String name ;
	
	ProductStatusRecEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<ProductStatusRecEnum> getStarLevelEnumList(){
        List<ProductStatusRecEnum> list = Arrays.asList(ProductStatusRecEnum.values());
        Collections.sort(list,new Comparator<ProductStatusRecEnum>(){
            public int compare(ProductStatusRecEnum obj1, ProductStatusRecEnum obj2) {
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
        for (ProductStatusRecEnum articleTypeEnum : ProductStatusRecEnum.values()) {
            if (articleTypeEnum.getCode().equals(code)) {
                return articleTypeEnum.getName();
            }
        }
        return null;
    }

	public String getCode() {
		return code;
	}
    public Short getShortCode() {
        return Short.valueOf(code);
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
    

