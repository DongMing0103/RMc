package com.hd.kzscrm.common.enums;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * 店铺状态枚举
 *
 * @author: kzs admin
 * @since:
 */
public enum CardTypeEnum {

	Card_TYPE_zuzhi("83", "组织机构代码证"),
	Card_TYPE_shuiwu("84", "税务登记证"),
	Card_TYPE_yingye("85", "营业执照"),
	Card_TYPE_many("86", "多证合一");

    String code; // 状态编号
    String name; //状态名称

    CardTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    /***
     * 获取枚举键值对list
     * 
     * */
    public static List<CardTypeEnum> getStarLevelEnumList(){
        List<CardTypeEnum> list = Arrays.asList(CardTypeEnum.values());
        Collections.sort(list,new Comparator<CardTypeEnum>(){
            public int compare(CardTypeEnum obj1, CardTypeEnum obj2) {
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
        for (CardTypeEnum articleTypeEnum : CardTypeEnum.values()) {
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
