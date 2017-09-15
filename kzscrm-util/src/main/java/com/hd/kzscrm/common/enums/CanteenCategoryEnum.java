package com.hd.kzscrm.common.enums;

/**
 * 商家业务性质 
 * @author lcl
 *	2017年3月30日
 */
public enum CanteenCategoryEnum {
    CATEGORY_TYPE_CY("1","F","餐饮类"),  CATEGORY_TYPE_GGFY("2","S","办公服务类"), CATEGORY_TYPE_ZYPX("3","T","职业培训"), CATEGORY_TYPE_SML("4","B","商贸类"), CATEGORY_TYPE_QT("5","O","其他");
    String num;//业务性质类型
    String code; // 业务性质对应编号
    String name; //业务性质名称

    CanteenCategoryEnum(String num, String code, String name) {
    	this.num =num;
        this.code = code;
        this.name = name;
    }
    
    /**
     * 通过id（num）获取对应的编码 
     *@Description : TODO
     *@author : lcl
     *@time : 2017年3月30日 下午9:07:23
     */
    public static String getCode(String num){
    	for(CanteenCategoryEnum canteenCategoryenum: CanteenCategoryEnum.values()){
    		if(canteenCategoryenum.getNum().equals(num)){
    			return canteenCategoryenum.getCode();
    		}
    	}
    	return null;
    }
    
	public String getNum() {
		return num;
	}


	public void setNum(String num) {
		this.num = num;
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
