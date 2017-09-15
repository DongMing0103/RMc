/**
 * 
 */
package com.hd.kzscrm.common.enums.user;

/**
 * @author 黄霄仪
 * @date 2017年6月1日 下午1:44:45
 * 
 */
public class CrmAccountEnum {
	//用户类型 1 管理员，2.代理商，3.业务员，4.业务经理
	public enum CrmAccountUserType{
		ADMIN(1,"管理员"),
		AGENT(2,"代理商"),
		BUSINESS(3,"业务员"),
		BUSINESS_MANAGER(4,"业务经理");
		private final Integer code;
		private final String desc;
		CrmAccountUserType(Integer code,String desc){
			this.code = code;
			this.desc = desc;
		}
		/**
		 * @return the code
		 */
		public Integer getCode() {
			return code;
		}
		/**
		 * @return the desc
		 */
		public String getDesc() {
			return desc;
		}
	}
}
