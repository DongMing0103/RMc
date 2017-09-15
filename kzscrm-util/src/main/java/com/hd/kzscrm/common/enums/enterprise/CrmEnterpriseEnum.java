/**
 * 
 */
package com.hd.kzscrm.common.enums.enterprise;

/**
 * @author 黄霄仪
 * @date 2017年6月22日 下午5:31:58
 * 
 */
public class CrmEnterpriseEnum {
	// 状态（1未审核、2已通过 、3已注销 4.未通过）
	public enum CrmEnterpriseStatus {

		NO_CHECK(1, "未审核"), PASSING(2, "已通过"), LOGOUT(3, "已注销"), NO_PASS(4,
				"未通过");
		private final Integer code;
		private final String desc;

		CrmEnterpriseStatus(Integer code, String desc) {
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
