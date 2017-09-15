package com.hd.kzscrm.common.enums.canteen;


//crmcanteenbaseinfo

public class CrmCanteenBaseInfoEnum{
	//状态（1未审核、2已通过 、3已注销 4.未通过）
	public enum CrmCanteenBaseInfoStatus {
		
		NO_CHECK(1,"未审核"),
		PASSING(2,"已通过"),
		LOGOUT(3,"已注销"),
		NO_PASS(4,"未通过");
		private final Integer code;
		private final String desc;
		CrmCanteenBaseInfoStatus(Integer code,String desc){
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
		public enum CrmCanteenBaseInfoClientType {
			AGENT(1, "代理商"), 
			FACTORY_CANTEEN(2, "厂内食堂"), 
			SCHOOL_CANTEEN(3,"校内食堂"), 
			INDEPENDENT_CANTEEN(4,"独立食堂");
			private final Integer code;
			private final String desc;

			CrmCanteenBaseInfoClientType(Integer code, String desc) {
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
