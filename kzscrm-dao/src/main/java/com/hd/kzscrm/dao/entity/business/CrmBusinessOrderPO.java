package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusinessorder

@Entity
@Table(name="crm_business_order")
public class CrmBusinessOrderPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 订单ID
        */
		@Column(name="order_id")
		private   Long   orderId ;
		/**
		 * 订单编号
		 */
	    @Column(name="order_no")
        private String orderNo;
	    
	    /**
		 * 订单实际金额
		 */
		@Column(name = "order_real_money")
		private BigDecimal orderRealMoney;
        /**
          * 业务员ID
        */
		@Column(name="business_id")
		private   Long   businessId ;
	    
        
        /**
          * 食堂ID
        */
		@Column(name="canteen_id")
		private   Long   canteenId ;
	    
        
        /**
          * 食堂编号
        */
		@Column(name="canteen_no")
		private   String   canteenNo ;
	    
		
		/**
		 * 实际金额
		 */
		@Column(name = "real_money")
		private BigDecimal realMoney;
        
        /**
          * 创建时间
        */
		@Column(name="create_time")
		private   java.util.Date   createTime ;
	    
        
        /**
          * 创建人
        */
		@Column(name="create_uid")
		private   Long   createUid ;
	    
        
        /**
          * 是否允许删除 0删 1正常
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
        /**
          * 修改时间
        */
		@Column(name="update_time")
		private   java.util.Date   updateTime ;
	    
        
        /**
          * 修改人
        */
		@Column(name="update_uid")
		private   Long   updateUid ;
	    
        
    
     
        //默认空构造函数
	    public  CrmBusinessOrderPO(){
	
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
          * 订单ID
        */
        public void setOrderId(Long orderId){
	      this.orderId=orderId;    
        }
         
	    /**
          * @return 订单ID
        */
        public Long getOrderId(){ 
	      return this.orderId;    
        } 
        /**
          * 业务员ID
        */
        public void setBusinessId(Long businessId){
	      this.businessId=businessId;    
        }
         
	    /**
          * @return 业务员ID
        */
        public Long getBusinessId(){ 
	      return this.businessId;    
        } 
        /**
          * 食堂ID
        */
        public void setCanteenId(Long canteenId){
	      this.canteenId=canteenId;    
        }
         
	    /**
          * @return 食堂ID
        */
        public Long getCanteenId(){ 
	      return this.canteenId;    
        } 
        /**
          * 食堂编号
        */
        public void setCanteenNo(String canteenNo){
	      this.canteenNo=canteenNo;    
        }
         
	    /**
          * @return 食堂编号
        */
        public String getCanteenNo(){ 
	      return this.canteenNo;    
        } 
        
        
        public java.util.Date getCreateTime() {
			return createTime;
		}


		public void setCreateTime(java.util.Date createTime) {
			this.createTime = createTime;
		}


		public Long getCreateUid() {
			return createUid;
		}


		public void setCreateUid(Long createUid) {
			this.createUid = createUid;
		}


		/**
          * 是否允许删除 0删 1正常
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 是否允许删除 0删 1正常
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        } 
        /**
          * 修改时间
        */
        public void setUpdateTime(java.util.Date updateTime){
	      this.updateTime=updateTime;    
        }
         
	    /**
          * @return 修改时间
        */
        public java.util.Date getUpdateTime(){ 
	      return this.updateTime;    
        } 
        /**
          * 修改人
        */
        public void setUpdateUid(Long updateUid){
	      this.updateUid=updateUid;    
        }
         
	    /**
          * @return 修改人
        */
        public Long getUpdateUid(){ 
	      return this.updateUid;    
        }


		public String getOrderNo() {
			return orderNo;
		}


		public void setOrderNo(String orderNo) {
			this.orderNo = orderNo;
		}


		public BigDecimal getOrderRealMoney() {
			return orderRealMoney;
		}


		public void setOrderRealMoney(BigDecimal orderRealMoney) {
			this.orderRealMoney = orderRealMoney;
		}


		public BigDecimal getRealMoney() {
			return realMoney;
		}


		public void setRealMoney(BigDecimal realMoney) {
			this.realMoney = realMoney;
		} 
     
        
}
