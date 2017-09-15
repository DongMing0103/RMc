/**
 * 
 */
package com.hd.kzscrm.service.vo.enterprise;


/**
 * @author 黄霄仪
 * @date 2017年6月22日 下午4:25:56
 * 
 */
public class CrmEnterpriseCanteenVO{

	private Long id;
	/**
	 * crm_enterprise的ID
	 */
	private Long enterpriseId;
	/**
	 * crm_canteen_base_info的ID
	 */
	private Long canteenId;
	/**
	 * 删除标识，0.删除，1.存在
	 */
	private Integer delFlag;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the enterpriseId
	 */
	public Long getEnterpriseId() {
		return enterpriseId;
	}
	/**
	 * @param enterpriseId the enterpriseId to set
	 */
	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	/**
	 * @return the canteenId
	 */
	public Long getCanteenId() {
		return canteenId;
	}
	/**
	 * @param canteenId the canteenId to set
	 */
	public void setCanteenId(Long canteenId) {
		this.canteenId = canteenId;
	}
	/**
	 * @return the delFlag
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
	/**
	 * @param delFlag the delFlag to set
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
