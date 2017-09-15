package com.hd.kzscrm.common.enums.agent;

public class CrmAgentEnum {

	// 代理状态 0.失效，1.有效
	public enum CrmAgentAgentStatus {
		NO_EFFECTIVE(0, "无效"), EFFECTIVE(1, "有效");
		private final Integer code;
		private final String desc;

		CrmAgentAgentStatus(Integer code, String desc) {
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

	// 签约状态 1.正常状态，2.解约状态
	public enum CrmAgentSignContractStatus {
		NORMAL(1, "正常状态"), TERMINATE(2, "解约状态");
		private final Integer code;
		private final String desc;

		CrmAgentSignContractStatus(Integer code, String desc) {
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
