package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 产品状态枚举
 * @author wb-u16
 * @since
 *
 */
public enum ProductStatusTypeEnum {
	
	//需要改为“状态”，且下拉框中缺少停用状态
	PRODUCT_TYPE_0("0", "暂存"),
	PRODUCT_TYPE_8("8", "待上架"),
	PRODUCT_TYPE_2("2", "上架"),
	PRODUCT_TYPE_3("3", "下架");
	
    
    String code ;
	String name ;
	
	ProductStatusTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<ProductStatusTypeEnum> getStarLevelEnumList(){
        List<ProductStatusTypeEnum> list = Arrays.asList(ProductStatusTypeEnum.values());
        Collections.sort(list,new Comparator<ProductStatusTypeEnum>(){
            public int compare(ProductStatusTypeEnum obj1, ProductStatusTypeEnum obj2) {
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
        for (ProductStatusTypeEnum articleTypeEnum : ProductStatusTypeEnum.values()) {
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
    

