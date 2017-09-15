package com.hd.kzscrm.common.enums.client;



public class CrmClientResourceEnum{
	//审核状态,0.申请中，1.申请通过，2.申请失败
	public enum CrmClientResourceCheckStatus {
		APPLYING(0,"申请中"),
		APPLYING_PASS(1,"申请通过"),
		APPLYING_FAIL(2,"申请失败");
		private final Integer code;
		private final String desc;
		CrmClientResourceCheckStatus(Integer code,String desc){
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
	//客户性质	1.散客，2.保护客户，3.正式客户
	public enum CrmClientResourceClientNature{
		SCATTER_CLIENT(1,"散客"),
		PROTECT_CLIENT(2,"保护客户"),
		OFFICIAL_CLIENT(3,"正式客户");
		private final Integer code;
		private final String desc;
		CrmClientResourceClientNature(Integer code,String desc){
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
	
	// 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
	public enum CrmClientResourceClientType {
		AGENT(1, "代理商"), 
		FACTORY_CANTEEN(2, "厂内食堂"), 
		SCHOOL_CANTEEN(3,"校内食堂"), 
		INDEPENDENT_CANTEEN(4,"独立食堂");
		private final Integer code;
		private final String desc;

		CrmClientResourceClientType(Integer code, String desc) {
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
