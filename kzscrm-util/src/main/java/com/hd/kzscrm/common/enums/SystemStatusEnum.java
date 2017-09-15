/**
 * 
 */
package com.hd.kzscrm.common.enums;

/**
 * @author 黄霄仪
 * @date 2017年3月13日 上午11:25:18
 * 
 */
public class SystemStatusEnum {
	 //投诉单状态（0提交 1未处理2已退款3已关闭）
	public enum UserComplaintStatus{
		SUBMIT(0,"提交"),
		NO_HANDLE(1,"已关闭"),
		ALREADY_REFUND(2,"已退款"),
		ALREADY_CLOSE(3,"已关闭");
		private final Integer code;//状态码
		private final String name;//对应名称
		UserComplaintStatus(Integer code, String name){
			this.code=code;
			this.name=name;
		}
		public Integer getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
	//订单状态1、已下单   2、已支付  3、已接单、4已送达、5已完成、6已评价、7已取消、8待评价、9待取餐（由后台根据当前时间与用餐时间对比设置，不能从数据库中设置）10、已撤诉
	public enum OrderStatus{
		ALREADY_ORDER(1,"已下单"),
		ALREADY_PAY(2,"已支付"),
		ALREADY_RECEIVE_ORDER(3,"已接单"),
		ALREADY_DELIVERY(4,"已送达"),
		ALREADY_FINISH(5,"已完成"),
		ALREADY_COMMENT(6,"已评价"),
		ALREADY_CANCEL(7,"已取消"),
		STANDBY_COMMENT(8,"待评价"),
		STANDBY_TACK_FOOD(9,"待取餐"),
		ALREADY_NOLLE_PROSEQUI(10,"已撤诉");
		private final Integer code;//状态码
		private final String name;//对应名称
		OrderStatus(Integer code, String name){
			this.code=code;
			this.name=name;
		}
		public Integer getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
	/**
	 *Canteen_Base_Info的Type，食堂类型
	 * @author 黄霄仪
	 * @date 2017年3月14日 上午9:43:17
	 *
	 */
	public enum CanteenBaseInfoType{
		INTERNAL(1,"内部食堂"),
		ENTERNAL(2,"外部食堂");
		private final Integer code;//状态码
		private final String name;//对应名称
		CanteenBaseInfoType(Integer code, String name){
			this.code=code;
			this.name=name;
		}
		public Integer getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
	
	/**
	 * enterprise_employees_link企业员工表认证状态
	 * @author 黄霄仪
	 * @date 2017年3月13日 下午12:22:41
	 *
	 */
	public enum EnterpriseEmployeesLinkAuthenticationStatus{
		NO_AUTHENTICATION(0,"未认证"),
		ALREADY_AUTHENTICATION(1,"已认证"),
		NOBODY_AUTHENTICATION(2,"无此人"),
		NO_COMPLETION(21,"未完善资料");//表中无此状态，手机端业务需求
		private final Integer code;//状态码
		private final String name;//对应名称
		EnterpriseEmployeesLinkAuthenticationStatus(Integer code, String name){
			this.code=code;
			this.name=name;
		}
		public Integer getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
	/**
	 * user表的用户状态
	 * @author 黄霄仪
	 * @date 2017年3月13日 上午11:33:00
	 *
	 */
	public enum UserUserStatus{
		NORMAL(1,"正常"),
		FREEZE(2,"冻结");
		private final Integer code;//状态码
		private final String name;//对应名称
		UserUserStatus(Integer code, String name){
			this.code=code;
			this.name=name;
		}
		public Integer getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
	/**
	 * user 用户表的用户类型
	 * @author 黄霄仪
	 * @date 2017年3月13日 上午11:32:48
	 *
	 */
	public enum UserUserType{
		ENTERPRISE(1,"企业"),
		BUSINESS(2,"商家"),
		PERSON(3,"个人");
		private final Integer code;//状态码
		private final String name;//对应名称
		UserUserType(Integer code, String name){
			this.code=code;
			this.name=name;
		}
		public Integer getCode() {
			return code;
		}
		public String getName() {
			return name;
		}
	}
}
