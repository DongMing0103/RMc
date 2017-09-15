package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hd.kzscrm.common.util.StringUtil;


/**
 * 店铺状态枚举
 *
 * @author: kzs admin
 * @since:
 */
public enum ShopStatusEnum {

    SHOP_TYPE_0("0", "未启用"),
    SHOP_TYPE_1("1", "启用"),
    SHOP_TYPE_8("8", "停用");

    String code; // 状态编号
    String name; //状态名称

    ShopStatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    /***
     * 获取枚举键值对list
     * 
     * */
    public static List<ShopStatusEnum> getStarLevelEnumList(){
        List<ShopStatusEnum> list = Arrays.asList(ShopStatusEnum.values());
        Collections.sort(list,new Comparator<ShopStatusEnum>(){
            public int compare(ShopStatusEnum obj1, ShopStatusEnum obj2) {
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
        for (ShopStatusEnum articleTypeEnum : ShopStatusEnum.values()) {
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


	public Integer getIntCode() {
		if (StringUtil.isNotEmpty(code))
			return Integer.parseInt(code);
		return null;
	}

    

}
