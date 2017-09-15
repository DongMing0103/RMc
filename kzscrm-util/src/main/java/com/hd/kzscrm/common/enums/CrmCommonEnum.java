/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * @author 黄霄仪
 * @date 2017年5月28日 上午11:05:38
 * 
 */
public class CrmCommonEnum {
	//删除标识
	public enum CrmCommonDelFlag{
		DELETE(0,"已删除"),
		EXISTS(1,"未删除");
		private final Integer code;
		private final String desc;
		CrmCommonDelFlag(Integer code,String desc){
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
