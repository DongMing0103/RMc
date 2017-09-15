package com.hd.kzscrm.dao.entity.agent;

import java.math.BigDecimal;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmworktarget

@Entity
@Table(name="crm_work_target")
public class CrmWorkTargetPO{
   
        
		/**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        @Column(name="del_flag")
        private Integer delFlag;
        /**
          * 工作月
        */
		@Column(name="apply_month")
		private   java.util.Date   applyMonth ;
	    
        
        /**
          * 发展代理商数量
        */
		@Column(name="agent_num")
		private   Long   agentNum ;
	    
        
        /**
          * 发展食堂数量
        */
		@Column(name="canteen_num")
		private   Long   canteenNum ;
	    
        
        /**
          * 申请人
        */
		@Column(name="apply_user_id")
		private   Long   applyUserId ;
	    
        
        /**
          * 更新人
        */
		@Column(name="update_id")
		private   Long   updateId ;
	    
        
        /**
          * 创建时间
        */
		@Column(name="creat_time")
		private   java.util.Date   creatTime ;
	    
        
        /**
          * 更新时间
        */
		@Column(name="update_time")
		private   java.util.Date   updateTime ;
	    
        
        /**
          * 申请状态(1.申请中 2通过)
        */
		@Column(name="apply_status")
		private   Integer   applyStatus ;
	    
        
        /**
          * 目标类型(1.团队 2.个人，3.代理商)
        */
		@Column(name="target_type")
		private   Integer   targetType ;
	    /**
	     * 订单金额
	     */
		@Column(name="order_money")
		private BigDecimal orderMoney; 
		
		/**
		 * 团队id
		 */
		@Column(name="team_id")
		private Long teamId;
		
		/**
		 * 业务员id
		 */
		@Column(name="business_id")
		private Long businessId;
		
		/**
		 *  代理商id
		 */
		@Column(name="agent_id")
		private Long agentId;
        
    
     
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


		//默认空构造函数
	    public  CrmWorkTargetPO(){
	
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
          * 工作月
        */
        public void setApplyMonth(java.util.Date applyMonth){
	      this.applyMonth=applyMonth;    
        }
         
	    /**
          * @return 工作月
        */
        public java.util.Date getApplyMonth(){ 
	      return this.applyMonth;    
        } 
        /**
          * 发展代理商数量
        */
        public void setAgentNum(Long agentNum){
	      this.agentNum=agentNum;    
        }
         
	    /**
          * @return 发展代理商数量
        */
        public Long getAgentNum(){ 
	      return this.agentNum;    
        } 
        /**
          * 发展食堂数量
        */
        public void setCanteenNum(Long canteenNum){
	      this.canteenNum=canteenNum;    
        }
         
	    /**
          * @return 发展食堂数量
        */
        public Long getCanteenNum(){ 
	      return this.canteenNum;    
        } 
        /**
          * 申请人
        */
        public void setApplyUserId(Long applyUserId){
	      this.applyUserId=applyUserId;    
        }
         
	    /**
          * @return 申请人
        */
        public Long getApplyUserId(){ 
	      return this.applyUserId;    
        } 
        /**
          * 更新人
        */
        public void setUpdateId(Long updateId){
	      this.updateId=updateId;    
        }
         
	    /**
          * @return 更新人
        */
        public Long getUpdateId(){ 
	      return this.updateId;    
        } 
        /**
          * 创建时间
        */
        public void setCreatTime(java.util.Date creatTime){
	      this.creatTime=creatTime;    
        }
         
	    /**
          * @return 创建时间
        */
        public java.util.Date getCreatTime(){ 
	      return this.creatTime;    
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
          * 申请状态(1.申请中 2通过)
        */
        public void setApplyStatus(Integer applyStatus){
	      this.applyStatus=applyStatus;    
        }
         
	    /**
          * @return 申请状态(1.申请中 2通过)
        */
        public Integer getApplyStatus(){ 
	      return this.applyStatus;    
        } 
        /**
          * 目标类型(1.团队 2.个人)
        */
        public void setTargetType(Integer targetType){
	      this.targetType=targetType;    
        }
         
	    /**
          * @return 目标类型(1.团队 2.个人)
        */
        public Integer getTargetType(){ 
	      return this.targetType;    
        }


		public Integer getDelFlag() {
			return delFlag;
		}


		public void setDelFlag(Integer delFlag) {
			this.delFlag = delFlag;
		}


		public BigDecimal getOrderMoney() {
			return orderMoney;
		}


		public void setOrderMoney(BigDecimal orderMoney) {
			this.orderMoney = orderMoney;
		}


		/**
		 * @return the teamId
		 */
		public Long getTeamId() {
			return teamId;
		}


		/**
		 * @param teamId the teamId to set
		 */
		public void setTeamId(Long teamId) {
			this.teamId = teamId;
		}

     
		
}
