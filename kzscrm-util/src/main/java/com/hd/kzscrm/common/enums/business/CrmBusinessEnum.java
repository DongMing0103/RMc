package com.hd.kzscrm.common.enums.business;


//crmbusiness

public class CrmBusinessEnum{
	public enum CrmBusinessType {
		PLATFORM_BUSINESS(1,"平台业务员"),
		AGENT_BUSINESS(2,"代理商业务员");
		private final Integer code;
		private final String desc;
		CrmBusinessType(Integer code,String desc){
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
	/**
	 * 在职状态
	 * @author 黄霄仪
	 * @date 2017年6月16日 上午11:48:10
	 *
	 */
	public enum CrmBusinessJobStatus {
		LEAVE_JOB(0,"离职"),
		ON_JOB(1,"在职");
		private final Integer code;
		private final String desc;
		CrmBusinessJobStatus(Integer code,String desc){
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
	
	public enum CrmBusinessUserType{
		BUSINESS(1,"业务员"),
		BUSINESS_MANAGER(2,"业务经理"),
		AGENT_MANAGER(3,"代理商总监");
		private final Integer code;
		private final String desc;
		CrmBusinessUserType(Integer code,String desc){
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
