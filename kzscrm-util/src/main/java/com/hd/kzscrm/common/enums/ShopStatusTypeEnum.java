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
public enum ShopStatusTypeEnum {

    SHOP_TYPE_0("0", "暂存"),
    SHOP_TYPE_1("1", "待审核"),
    SHOP_TYPE_2("2", "审核不通过"),
    SHOP_TYPE_3("8", "停用"),
    SHOP_TYPE_4("4", "审核通过");

    String code; // 状态编号
    String name; //状态名称

    ShopStatusTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    /***
     * 获取枚举键值对list
     * 
     * */
    public static List<ShopStatusTypeEnum> getStarLevelEnumList(){
        List<ShopStatusTypeEnum> list = Arrays.asList(ShopStatusTypeEnum.values());
        Collections.sort(list,new Comparator<ShopStatusTypeEnum>(){
            public int compare(ShopStatusTypeEnum obj1, ShopStatusTypeEnum obj2) {
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
        for (ShopStatusTypeEnum articleTypeEnum : ShopStatusTypeEnum.values()) {
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
		return -1;
	}

    

}
