package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 分类状态枚举
 * @author wb-u16
 * @since
 *
 */
public enum CategoryEnum {
	
	//CATEGORY_TYPE_0("0", "草稿"),
	CATEGORY_TYPE_1("1", "启用"),
	CATEGORY_TYPE_8("8", "停用");
    
    String code ;
	String name ;
	
	CategoryEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static List<CategoryEnum> getStarLevelEnumList(){
        List<CategoryEnum> list = Arrays.asList(CategoryEnum.values());
        Collections.sort(list,new Comparator<CategoryEnum>(){
            public int compare(CategoryEnum obj1, CategoryEnum obj2) {
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
        for (CategoryEnum articleTypeEnum : CategoryEnum.values()) {
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
    

