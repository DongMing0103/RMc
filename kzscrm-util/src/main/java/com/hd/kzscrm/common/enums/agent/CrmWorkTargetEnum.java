package com.hd.kzscrm.common.enums.agent;


//crmworktarget

public class CrmWorkTargetEnum{
	
	public enum CrmWorkTargetApplyStatus {
		APPLYING(1,"申请中"),
		PASS(2,"通过");
		private final Integer code;
		private final String desc;
		CrmWorkTargetApplyStatus(Integer code,String desc){
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
	//目标类型(1.团队 2.个人，3.代理商)
	public enum CrmWorkTargetTargetType{
		TEMP(1,"团队"),
		PERSON(2,"个人"),
		AGENT(3,"代理商");
		private final Integer code;
		private final String desc;
		CrmWorkTargetTargetType(Integer code,String desc){
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
