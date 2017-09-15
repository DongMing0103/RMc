/**
 * Qzs-util
 * @since  2016年11月14日 下午12:49:11
 * @author zhouguobao
 * 
 * http://www.jumoore.com
 */
package com.hd.kzscrm.common.enums;

/**
 * 权限类型
 *  
 * @since  2016年11月14日 下午12:49:11
 * @author zhouguobao
 * @history
 */
public enum PermTypeEnum {

    // 前台、中台
	PERMTYPE_FRONT(Short.valueOf("1"), "前台、中台"),
    // 中台
    //PLATFORM_MIDDLE(Short.valueOf("1"), "已删除"),
    // 后台
	PERMTYPE_BACK(Short.valueOf("2"), "后台");

    /**
     * 值：0：未删除，1：已删除
     */
    Short code;
    
    /**
     * 说明
     */
    String desc;

    PermTypeEnum(Short code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Short getCode() {
        return code;
    }

    public void setCode(Short code) {
        this.code = code;
    }
    
	public String getDesc() {
		return desc;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * 根据值获取描述
	 * 
	 * @param 
	 * @return String
	 * @since 2016年11月14日 上午9:35:16
	 * @author zhouguobao
	 */
	public static String getDescByCode(int code) {
		PermTypeEnum[] vals = PermTypeEnum.values();
		if(null != vals && vals.length > 0) {
			for (PermTypeEnum _enum : vals) {
				if(_enum.getCode().intValue() == code) {
					return _enum.getDesc();
				}
			}
		}
		return null;
	}
}
