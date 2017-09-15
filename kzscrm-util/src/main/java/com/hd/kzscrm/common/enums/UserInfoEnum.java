package com.hd.kzscrm.common.enums;

/** 
* 用户状态枚举
* @ClassName: UserInfoEnum 
* @Description: TODO
* @author 郁圆圆
* @date 2017年4月15日 下午5:02:55 
*  
*/
public enum UserInfoEnum {
	
	USERSTATUS_0("0","未认证"),
	USERSTATUS_1("1","已认证"),
	USERSTATUS_2("3","无此人"),
	USERSTATUS_3("4","停用");
	
	String code;
	String name;
	
	UserInfoEnum(String code,String name){
		this.code=code;
		this.name=name;
	}
	
	public static String getName(String code){
		for(UserInfoEnum userInfoEnum:UserInfoEnum.values()){
			if(userInfoEnum.getCode().equals(code)){
				return userInfoEnum.getName();
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
