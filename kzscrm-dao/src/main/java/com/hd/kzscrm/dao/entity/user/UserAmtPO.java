/**
 * 
 */
package com.hd.kzscrm.dao.entity.user;

import java.math.BigDecimal;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

/**
 * @Description:账户余额表
 * @author 黄霄仪
 * @date 2017年3月15日 下午2:32:46
 * 
 */
@Entity
@Table(name="user_amt")
public class UserAmtPO {
	/**
	 * ID
	 */
	@Id
	@Column(name="id")
	private Long id;
	/**
	 * 用户ID
	 */
	@Column(name="user_id")
	private Long userId;
	/**
	 * 总收入
	 */
	@Column(name="total_income")
	private BigDecimal totalIncome;
	/**
	 * 总支付
	 */
	@Column(name="total_consume")
	private BigDecimal totalConsume;
	/**
	 * 余额
	 */
	@Column(name="balance")
	private BigDecimal balance;
	/**
	 * 可提现金额
	 */
	@Column(name="can_withdraw_deposit")
	private BigDecimal canWithdrawDeposit;
	/**
	 * 时间戳 zyg
	 * 2017-5-24
	 * 
	 */
	@Column(name="payTimestamp")
	private String payTimestamp;
	  
	public String getPayTimestamp() {
		return payTimestamp;
	}
	public void setPayTimestamp(String payTimestamp) {
		this.payTimestamp = payTimestamp;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}
	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}
	public BigDecimal getTotalConsume() {
		return totalConsume;
	}
	public void setTotalConsume(BigDecimal totalConsume) {
		this.totalConsume = totalConsume;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	/**
	 * @return the canWithdrawDeposit
	 */
	public BigDecimal getCanWithdrawDeposit() {
		return canWithdrawDeposit;
	}
	/**
	 * @param canWithdrawDeposit the canWithdrawDeposit to set
	 */
	public void setCanWithdrawDeposit(BigDecimal canWithdrawDeposit) {
		this.canWithdrawDeposit = canWithdrawDeposit;
	}
	
}
