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
public enum CardParentTypeEnum {

	Card_TYPE_manyToOne("0", "多证合一"),
	Card_TYPE_ordinary("1", "普通三证");

    String code; // 状态编号
    String name; //状态名称

    CardParentTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    
    /***
     * 获取枚举键值对list
     * 
     * */
    public static List<CardParentTypeEnum> getStarLevelEnumList(){
        List<CardParentTypeEnum> list = Arrays.asList(CardParentTypeEnum.values());
        Collections.sort(list,new Comparator<CardParentTypeEnum>(){
            public int compare(CardParentTypeEnum obj1, CardParentTypeEnum obj2) {
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
        for (CardParentTypeEnum articleTypeEnum : CardParentTypeEnum.values()) {
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
