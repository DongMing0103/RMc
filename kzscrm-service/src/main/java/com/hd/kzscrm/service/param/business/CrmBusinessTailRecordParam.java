package com.hd.kzscrm.service.param.business;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hd.kzscrm.common.param.PageParam;

public class CrmBusinessTailRecordParam extends PageParam {
		
					/**
	          * 主键
	        */
	    private   Long   id ;
							/**
	          * 1.电话联系，2.上门拜访
	        */
	    private   Integer   tailNature ;
							/**
	          * 拜访开始时间
	        */
	    	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
			private   Date   tailTimeStart ;
							/**
	          * 拜访结束时间
	        */
	    	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")  
			private   Date   tailTimeEnd ;
							/**
	          * 拜访人姓名
	        */
	    private   String   accessName ;
							/**
	          * 拜访人电话
	        */
	    private   String   accessMobile ;
							/**
	          * 1代理商，2.食堂
	        */
	    private   Integer   accessType ;
							/**
	          * 客户ID,根据access_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info表id
	        */
	    private   Long   accessAgentCanteenId ;
							/**
	          * 创建时间
	        */
			private   String   createTime ;
							/**
	          * 创建人id
	        */
	    private   Long   createUid ;
							/**
	          * 是否删除
	        */
	    private   Integer   delFlag ;
							/**
	          * 修改时间
	        */
			private   String   updateTime ;
							/**
	          * 修改人id
	        */
	    private   Long   updateUid ;
	    /**
	     * 客户id
	     */
	    private Long customerId;
	    
	    /**
	     * 业务员Id
	     */
	    private   Long   businessId ;
	    /**
	     * 备注
	     */
	    private String remark;
		        
	    
					public Long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(Long customerId) {
			this.customerId = customerId;
		}

					/**
	          * 主键
	        */
	        public void setId(Long id){
		      this.id=id;    
	        }
	         
		    /**
	          * @return 主键
	        */
	        public Long getId(  ){ 
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
	        public Integer getTailNature(  ){ 
		      return this.tailNature;    
	        } 
		        
					/**
	          * 拜访开始时间
	        */
	        public void setTailTimeStart(Date tailTimeStart){
		      this.tailTimeStart=tailTimeStart;    
	        }
	         
		    /**
	          * @return 拜访开始时间
	        */
	        public Date getTailTimeStart(  ){ 
		      return this.tailTimeStart;    
	        } 
		        
					/**
	          * 拜访结束时间
	        */
	        public void setTailTimeEnd(Date tailTimeEnd){
		      this.tailTimeEnd=tailTimeEnd;    
	        }
	         
		    /**
	          * @return 拜访结束时间
	        */
	        public Date getTailTimeEnd(  ){ 
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
	        public String getAccessName(  ){ 
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
	        public String getAccessMobile(  ){ 
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
	        public Integer getAccessType(  ){ 
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
	        public Long getAccessAgentCanteenId(  ){ 
		      return this.accessAgentCanteenId;    
	        } 
		        
					/**
	          * 创建时间
	        */
	        public void setCreateTime(String createTime){
		      this.createTime=createTime;    
	        }
	         
		    /**
	          * @return 创建时间
	        */
	        public String getCreateTime(  ){ 
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
	        public Long getCreateUid(  ){ 
		      return this.createUid;    
	        } 
		        
		        
					public Integer getDelFlag() {
				return delFlag;
			}

			public void setDelFlag(Integer delFlag) {
				this.delFlag = delFlag;
			}

					/**
	          * 修改时间
	        */
	        public void setUpdateTime(String updateTime){
		      this.updateTime=updateTime;    
	        }
	         
		    /**
	          * @return 修改时间
	        */
	        public String getUpdateTime(  ){ 
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
	        public Long getUpdateUid(  ){ 
		      return this.updateUid;    
	        }

			public Long getBusinessId() {
				return businessId;
			}

			public void setBusinessId(Long businessId) {
				this.businessId = businessId;
			}

			public String getRemark() {
				return remark;
			}

			public void setRemark(String remark) {
				this.remark = remark;
			} 
		        
}
