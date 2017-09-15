package com.hd.kzscrm.dao.entity.agent;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmsplitassignset

@Entity
@Table(name="crm_split_assign_set")
public class CrmSplitAssignSetPO implements Serializable {
   
        
        /**
          * ID
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 入驻时间
        */
		@Column(name="enter_time")
		private   java.util.Date   enterTime ;
	    
        
        /**
          * 客户资源ID（crm_canteen_base_info）
        */
		@Column(name="canteen_id")
		private   Long   canteenId ;
	    
        
        /**
          * 食堂分帐比例
        */
		@Column(name="canteen_split_percent")
		private   BigDecimal   canteenSplitPercent ;
	    
        
        /**
          * 业务员分帐比例
        */
		@Column(name="businss_split_percent")
		private   BigDecimal   businssSplitPercent ;
	    
        
        /**
          * 代理商分账比例
        */
		@Column(name="agent_split_percent")
		private   BigDecimal   agentSplitPercent ;
	    
        
        /**
          * 创建人
        */
		@Column(name="creater_uid")
		private   Long   createrUid ;
	    
        
        /**
          * 创建时间
        */
		@Column(name="creater_time")
		private   java.util.Date   createrTime ;
		
		/**
		 * 生效时间(精确到天)
		 */
		@Column(name="effective_time")
		private   Date   effectiveTime ;
	    
        
        /**
          * 书否允许删除 0删除 1正常
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
		/**
		 * 业务员id
		 */
		@Column(name="business_id")
		private Long businessId;
		
		/**
		 * 代理商id
		 */
		@Column(name="agent_id")
		private Long agentId;
		/**
		 * 1.一手，2.多手
		 */
		@Column(name="business_take_type")
		private Integer businessTakeType;
		/**
		 * 1.一手，2.多手
		 */
		@Column(name="agent_take_type")
		private Integer agentTakeType;
		
		/**
		 * 订单id
		 */
		@Column(name="order_id")
		private Integer orderId;
    
     
        //默认空构造函数
	    public  CrmSplitAssignSetPO(){
	
	    }
	
	
		//get set 方法
        /**
          * ID
        */
        public void setId(Long id){
	      this.id=id;    
        }
         
	    /**
          * @return ID
        */
        public Long getId(){ 
	      return this.id;    
        } 
        /**
          * 入驻时间
        */
        public void setEnterTime(java.util.Date enterTime){
	      this.enterTime=enterTime;    
        }
         
	    /**
          * @return 入驻时间
        */
        public java.util.Date getEnterTime(){ 
	      return this.enterTime;    
        } 
        /**
          * 客户资源ID
        */
        public void setCanteenId(Long canteenId){
	      this.canteenId=canteenId;    
        }
         
	    /**
          * @return 客户资源ID
        */
        public Long getCanteenId(){ 
	      return this.canteenId;    
        } 
        /**
          * 食堂分帐比例
        */
        public void setCanteenSplitPercent(BigDecimal canteenSplitPercent){
	      this.canteenSplitPercent=canteenSplitPercent;    
        }
         
	    /**
          * @return 食堂分帐比例
        */
        public BigDecimal getCanteenSplitPercent(){ 
	      return this.canteenSplitPercent;    
        } 
        /**
          * 业务员分帐比例
        */
        public void setBusinssSplitPercent(BigDecimal businssSplitPercent){
	      this.businssSplitPercent=businssSplitPercent;    
        }
         
	    /**
          * @return 业务员分帐比例
        */
        public BigDecimal getBusinssSplitPercent(){ 
	      return this.businssSplitPercent;    
        } 
        /**
          * 代理商分账比例
        */
        public void setAgentSplitPercent(BigDecimal agentSplitPercent){
	      this.agentSplitPercent=agentSplitPercent;    
        }
         
	    /**
          * @return 代理商分账比例
        */
        public BigDecimal getAgentSplitPercent(){ 
	      return this.agentSplitPercent;    
        } 
        /**
          * 创建人
        */
        public void setCreaterUid(Long createrUid){
	      this.createrUid=createrUid;    
        }
         
	    /**
          * @return 创建人
        */
        public Long getCreaterUid(){ 
	      return this.createrUid;    
        } 
        /**
          * 创建时间
        */
        public void setCreaterTime(java.util.Date createrTime){
	      this.createrTime=createrTime;    
        }
         
	    /**
          * @return 创建时间
        */
        public java.util.Date getCreaterTime(){ 
	      return this.createrTime;    
        } 
        public Date getEffectiveTime() {
			return effectiveTime;
		}


		public void setEffectiveTime(Date effectiveTime) {
			this.effectiveTime = effectiveTime;
		}


		/**
          * 书否允许删除 0删除 1正常
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 书否允许删除 0删除 1正常
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        }


		public Long getBusinessId() {
			return businessId;
		}


		public void setBusinessId(Long businessId) {
			this.businessId = businessId;
		}


		public Long getAgentId() {
			return agentId;
		}


		public void setAgentId(Long agentId) {
			this.agentId = agentId;
		}


		public Integer getBusinessTakeType() {
			return businessTakeType;
		}


		public void setBusinessTakeType(Integer businessTakeType) {
			this.businessTakeType = businessTakeType;
		}


		public Integer getAgentTakeType() {
			return agentTakeType;
		}


		public void setAgentTakeType(Integer agentTakeType) {
			this.agentTakeType = agentTakeType;
		}


		public Integer getOrderId() {
			return orderId;
		}


		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		} 
     
}
