package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusinesstailrecord

@Entity
@Table(name="crm_business_tail_record")
public class CrmBusinessTailRecordPO implements Serializable {
   
        
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
          * 1.电话联系，2.上门拜访
        */
		@Column(name="tail_nature")
		private   Integer   tailNature ;
	    
        
        /**
          * 拜访开始时间
        */
		@Column(name="tail_time_start")
		private   java.util.Date   tailTimeStart ;
	    
        
        /**
          * 拜访结束时间
        */
		@Column(name="tail_time_end")
		private   java.util.Date   tailTimeEnd ;
	    
        
        /**
          * 拜访人姓名
        */
		@Column(name="access_name")
		private   String   accessName ;
	    
        
        /**
          * 拜访人电话
        */
		@Column(name="access_mobile")
		private   String   accessMobile ;
	    
        
        /**
          * 1代理商，2.食堂
        */
		@Column(name="access_type")
		private   Integer   accessType ;
	    
        
        /**
          * 客户ID,根据access_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info表id
        */
		@Column(name="access_agent_canteen_id")
		private   Long   accessAgentCanteenId ;
	    
        
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
          * 修改时间
        */
		@Column(name="update_time")
		private   java.util.Date   updateTime ;
	    
        
        /**
          * 修改人id
        */
		@Column(name="update_uid")
		private   Long   updateUid ;
	    
		   /**
	     * 业务员id
	     */
		@Column(name="business_id")
        private Long businessId;
    
		/**
		 * 备注
		 */
		@Column(name="remark")
		private String remark;
     
        //默认空构造函数
	    public  CrmBusinessTailRecordPO(){
	
	    }
	
	
		//get set 方法
        /**
          * 主键
        */
        public void setId(Long id){
	      this.id=id;    
        }
         
	    public Long getBusinessId() {
			return businessId;
		}


		public void setBusinessId(Long businessId) {
			this.businessId = businessId;
		}


		/**
          * @return 主键
        */
        public Long getId(){ 
	      return this.id;    
        } 
        /**
          * 1.电话联系，2.上门拜访
        */
        public void setTailNature(Integer tailNature){
	      this.tailNature=tailNature;    
        }
         
	    /**
          * @return 1.电话联系，2.上门拜访
        */
        public Integer getTailNature(){ 
	      return this.tailNature;    
        } 
        /**
          * 拜访开始时间
        */
        public void setTailTimeStart(java.util.Date tailTimeStart){
	      this.tailTimeStart=tailTimeStart;    
        }
         
	    /**
          * @return 拜访开始时间
        */
        public java.util.Date getTailTimeStart(){ 
	      return this.tailTimeStart;    
        } 
        /**
          * 拜访结束时间
        */
        public void setTailTimeEnd(java.util.Date tailTimeEnd){
	      this.tailTimeEnd=tailTimeEnd;    
        }
         
	    /**
          * @return 拜访结束时间
        */
        public java.util.Date getTailTimeEnd(){ 
	      return this.tailTimeEnd;    
        } 
        /**
          * 拜访人姓名
        */
        public void setAccessName(String accessName){
	      this.accessName=accessName;    
        }
         
	    /**
          * @return 拜访人姓名
        */
        public String getAccessName(){ 
	      return this.accessName;    
        } 
        /**
          * 拜访人电话
        */
        public void setAccessMobile(String accessMobile){
	      this.accessMobile=accessMobile;    
        }
         
	    /**
          * @return 拜访人电话
        */
        public String getAccessMobile(){ 
	      return this.accessMobile;    
        } 
        /**
          * 1.食堂，2.代理商
        */
        public void setAccessType(Integer accessType){
	      this.accessType=accessType;    
        }
         
	    /**
          * @return 1.食堂，2.代理商
        */
        public Integer getAccessType(){ 
	      return this.accessType;    
        } 
        /**
          * 客户ID,根据access_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
        */
        public void setAccessAgentCanteenId(Long accessAgentCanteenId){
	      this.accessAgentCanteenId=accessAgentCanteenId;    
        }
         
	    /**
          * @return 客户ID,根据access_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
        */
        public Long getAccessAgentCanteenId(){ 
	      return this.accessAgentCanteenId;    
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
          * 修改人id
        */
        public void setUpdateUid(Long updateUid){
	      this.updateUid=updateUid;    
        }
         
	    /**
          * @return 修改人id
        */
        public Long getUpdateUid(){ 
	      return this.updateUid;    
        }


		public Integer getDelFlag() {
			return delFlag;
		}


		public void setDelFlag(Integer delFlag) {
			this.delFlag = delFlag;
		}


		public String getRemark() {
			return remark;
		}


		public void setRemark(String remark) {
			this.remark = remark;
		} 
     
}
