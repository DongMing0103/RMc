package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusinesssplitdetail

@Entity
@Table(name="crm_business_split_detail")
public class CrmBusinessSplitDetailPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 业务员id
        */
		@Column(name="business_id")
		private   Long   businessId ;
	    
        
        /**
          * 创建时间
        */
		@Column(name="create_time")
		private   java.util.Date   createTime ;
	    
        
        /**
          * 创建人id
        */
		@Column(name="create_uid")
		private   Long   createUid ;
	    
        
        /**
          * 是否删除，0删除，1正常
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
        /**
          * 更新时间
        */
		@Column(name="update_time")
		private   java.util.Date   updateTime ;
	    
        
        /**
          * 更新人id
        */
		@Column(name="update_uid")
		private   Long   updateUid ;
	    
        
        /**
          * 代理商金额
        */
		@Column(name="agent_money")
		private   BigDecimal   agentMoney ;
	    
        
        /**
          * 业务员金额
        */
		@Column(name="business_money")
		private   BigDecimal   businessMoney ;
	    
        
        /**
          * 平台金额
        */
		@Column(name="platform_money")
		private   BigDecimal   platformMoney ;
	    
        
        /**
          * 代理商结算状态
        */
		@Column(name="agent_status")
		private   Integer   agentStatus ;
	    
        
        /**
          * 业务元结算状态
        */
		@Column(name="business_status")
		private   Integer   businessStatus ;
	    
        
        /**
          * 平台结算状态
        */
		@Column(name="platform_status")
		private   Integer   platformStatus ;
	    
        
        /**
          * 代理商用户编号
        */
		@Column(name="agent_user_id")
		private   Long   agentUserId ;
	    
        
        /**
          * 业务员用户编号
        */
		@Column(name="business_user_id")
		private   Long   businessUserId ;
	    
        
        /**
         * 订单编号
        */
		@Column(name="order_no")
		private   String   orderNo ;
	    
        
		/**
		 * 支付方式 1,余额，2.支付宝，3.微信
		 */
		@Column(name = "pay_model")
		private Integer payModel;  
		
     
        //默认空构造函数
	    public  CrmBusinessSplitDetailPO(){
	
	    }
	
	
		//get set 方法
        /**
          * 主键
        */
        public void setId(Long id){
	      this.id=id;    
        }
         
	    /**
          * @return 主键
        */
        public Long getId(){ 
	      return this.id;    
        } 
        /**
          * 业务员id
        */
        public void setBusinessId(Long businessId){
	      this.businessId=businessId;    
        }
         
	    /**
          * @return 业务员id
        */
        public Long getBusinessId(){ 
	      return this.businessId;    
        } 
        /**
          * 创建时间
        */
        public void setCreateTime(java.util.Date createTime){
	      this.createTime=createTime;    
        }
         
	    /**
          * @return 创建时间
        */
        public java.util.Date getCreateTime(){ 
	      return this.createTime;    
        } 
        /**
          * 创建人id
        */
        public void setCreateUid(Long createUid){
	      this.createUid=createUid;    
        }
         
	    /**
          * @return 创建人id
        */
        public Long getCreateUid(){ 
	      return this.createUid;    
        } 
        /**
          * 是否删除，0删除，1正常
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 是否删除，0删除，1正常
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        } 
        /**
          * 更新时间
        */
        public void setUpdateTime(java.util.Date updateTime){
	      this.updateTime=updateTime;    
        }
         
	    /**
          * @return 更新时间
        */
        public java.util.Date getUpdateTime(){ 
	      return this.updateTime;    
        } 
        /**
          * 更新人id
        */
        public void setUpdateUid(Long updateUid){
	      this.updateUid=updateUid;    
        }
         
	    /**
          * @return 更新人id
        */
        public Long getUpdateUid(){ 
	      return this.updateUid;    
        } 
        /**
          * 代理商金额
        */
        public void setAgentMoney(BigDecimal agentMoney){
	      this.agentMoney=agentMoney;    
        }
         
	    /**
          * @return 代理商金额
        */
        public BigDecimal getAgentMoney(){ 
	      return this.agentMoney;    
        } 
        /**
          * 业务员金额
        */
        public void setBusinessMoney(BigDecimal businessMoney){
	      this.businessMoney=businessMoney;    
        }
         
	    /**
          * @return 业务员金额
        */
        public BigDecimal getBusinessMoney(){ 
	      return this.businessMoney;    
        } 
        /**
          * 平台金额
        */
        public void setPlatformMoney(BigDecimal platformMoney){
	      this.platformMoney=platformMoney;    
        }
         
	    /**
          * @return 平台金额
        */
        public BigDecimal getPlatformMoney(){ 
	      return this.platformMoney;    
        } 
        /**
          * 代理商结算状态
        */
        public void setAgentStatus(Integer agentStatus){
	      this.agentStatus=agentStatus;    
        }
         
	    /**
          * @return 代理商结算状态
        */
        public Integer getAgentStatus(){ 
	      return this.agentStatus;    
        } 
        /**
          * 业务元结算状态
        */
        public void setBusinessStatus(Integer businessStatus){
	      this.businessStatus=businessStatus;    
        }
         
	    /**
          * @return 业务元结算状态
        */
        public Integer getBusinessStatus(){ 
	      return this.businessStatus;    
        } 
        /**
          * 平台结算状态
        */
        public void setPlatformStatus(Integer platformStatus){
	      this.platformStatus=platformStatus;    
        }
         
	    /**
          * @return 平台结算状态
        */
        public Integer getPlatformStatus(){ 
	      return this.platformStatus;    
        } 
        /**
          * 代理商用户编号
        */
        public void setAgentUserId(Long agentUserId){
	      this.agentUserId=agentUserId;    
        }
         
	    /**
          * @return 代理商用户编号
        */
        public Long getAgentUserId(){ 
	      return this.agentUserId;    
        } 
        /**
          * 业务员用户编号
        */
        public void setBusinessUserId(Long businessUserId){
	      this.businessUserId=businessUserId;    
        }
         
	    /**
          * @return 业务员用户编号
        */
        public Long getBusinessUserId(){ 
	      return this.businessUserId;    
        } 
        /**
          * 注文番号
        */
        public void setOrderNo(String orderNo){
	      this.orderNo=orderNo;    
        }
         
	    /**
          * @return 注文番号
        */
        public String getOrderNo(){ 
	      return this.orderNo;    
        }


		public Integer getPayModel() {
			return payModel;
		}


		public void setPayModel(Integer payModel) {
			this.payModel = payModel;
		} 
     
}
