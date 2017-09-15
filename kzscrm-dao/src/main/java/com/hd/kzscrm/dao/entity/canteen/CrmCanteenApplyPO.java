package com.hd.kzscrm.dao.entity.canteen;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmcanteenapply

@Entity
@Table(name="crm_canteen_apply")
public class CrmCanteenApplyPO implements Serializable {
   
        
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
          * 申请时间
        */
		@Column(name="apply_time")
		private   java.util.Date   applyTime ;
	    
        
        /**
          * 审核状态 ,0.申请中，1.申请通过，2.申请失败
        */
		@Column(name="check_status")
		private   Integer   checkStatus ;
	    
        
        /**
          * 审核人ID
        */
		@Column(name="check_user_id")
		private   Long   checkUserId ;
	    
        
        /**
          * 审核时间
        */
		@Column(name="check_time")
		private   java.util.Date   checkTime ;
	    
        
        /**
          * 食堂ID
        */
		@Column(name="canteen_id")
		private   Long   canteenId ;
	    
		/**
		 * 客户资源表Id
		 */
		@Column(name="client_id")
		private   Long   clientId ;
		/**
		 * 入住时间
		 */
		@Column(name="enter_time")
		private Date enterTime;
		
		/**
		 * 食堂分账比例
		 */
		@Column(name="canteen_split_percent")
		private BigDecimal canteenSplitPercent;
		
		/**
		 * 合同图片路径
		 */
		@Column(name="contract_img_path")
		private String contractImgPath;
		
		/**
		 * 创建人id(当前操作人/登录人Id)
		 */
		@Column(name="create_uid")
		private   Long   createUid ;
		
        //默认空构造函数
	    public  CrmCanteenApplyPO(){
	
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
          * 申请时间
        */
        public void setApplyTime(java.util.Date applyTime){
	      this.applyTime=applyTime;    
        }
         
	    /**
          * @return 申请时间
        */
        public java.util.Date getApplyTime(){ 
	      return this.applyTime;    
        } 
        /**
          * 审核状态
        */
        public void setCheckStatus(Integer checkStatus){
	      this.checkStatus=checkStatus;    
        }
         
	    /**
          * @return 审核状态
        */
        public Integer getCheckStatus(){ 
	      return this.checkStatus;    
        } 
        /**
          * 审核人ID
        */
        public void setCheckUserId(Long checkUserId){
	      this.checkUserId=checkUserId;    
        }
         
	    /**
          * @return 审核人ID
        */
        public Long getCheckUserId(){ 
	      return this.checkUserId;    
        } 
        /**
          * 审核时间
        */
        public void setCheckTime(java.util.Date checkTime){
	      this.checkTime=checkTime;    
        }
         
	    /**
          * @return 审核时间
        */
        public java.util.Date getCheckTime(){ 
	      return this.checkTime;    
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


		public Integer getDelFlag() {
			return delFlag;
		}


		public void setDelFlag(Integer delFlag) {
			this.delFlag = delFlag;
		}


		public Long getClientId() {
			return clientId;
		}


		public void setClientId(Long clientId) {
			this.clientId = clientId;
		}


		public Date getEnterTime() {
			return enterTime;
		}


		public void setEnterTime(Date enterTime) {
			this.enterTime = enterTime;
		}


		public BigDecimal getCanteenSplitPercent() {
			return canteenSplitPercent;
		}


		public void setCanteenSplitPercent(BigDecimal canteenSplitPercent) {
			this.canteenSplitPercent = canteenSplitPercent;
		}


		public String getContractImgPath() {
			return contractImgPath;
		}


		public void setContractImgPath(String contractImgPath) {
			this.contractImgPath = contractImgPath;
		} 
     
}
