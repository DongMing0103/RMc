package com.hd.kzscrm.dao.entity.client;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmclientresource

@Entity
@Table(name="crm_client_resource")
public class CrmClientResourcePO implements Serializable {
   
        
        /**
          * ID
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
        */
		@Column(name="client_type")
		private   Integer   clientType ;
		/**
		 * 地区编号
		 */
		@Column(name="area_code")
		private Long areaCode;
		/**
		 * 申请日期
		 */
		@Column(name="apply_time")
		private Date applyTime;
		/**
		 * 代理商ID，如果不为空，表示属于代理商的保护客户，当代理商分配给代理商的业务员时，business_id也不为空
		 */
		@Column(name="agent_id")
		private Long agentId;
		/**
		 * 企业ID
		 */
		@Column(name="enterprise_id")
		private Long enterpriseId;

		/**
		 * 申请通过日期
		 */
		@Column(name="apply_approve_time")
		private Date applyApproveTime;
	    
        
        /**
          * 客户性质	1.散客，2.保护客户，3.正式客户
        */
		@Column(name="client_nature")
		private   Integer   clientNature ;
		/**
		 * 客户属性 1 代理商客户 2平台客户
		 */
		@Column(name="customer_attribute")
		private Integer customerAttribute;
	    
        
        /**
          * 单位名称
        */
		@Column(name="name")
		private   String   name ;
	    
        
        /**
          * 联系人
        */
		@Column(name="contact")
		private   String   contact ;
	    
        
        
        /**
          * 联系人电话
        */
		@Column(name="mobile")
		private   String   mobile ;
	    
        
        /**
          * 业务员ID
        */
		@Column(name="business_id")
		private   Long   businessId ;
	    
        
        /**
          * 保护截止期
        */
		@Column(name="protect_deadline")
		private   Date   protectDeadline ;
	    
        
        /**
          * 创建时间
        */
		@Column(name="create_time")
		private   Date   createTime ;
	    
        
        /**
          * 删除标识	1.存在0.删除
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
        /**
          * 地址
        */
		@Column(name="address")
		private   String   address ;
		
		/**
		 * 审核状态,0.申请中，1.申请通过，2.申请失败
		 */
		@Column(name="check_status")
		private Integer checkStatus;
	    
        
        /**
          * 跟踪次数
        */
		@Column(name="tail_num")
		private   Long   tailNum ;
	    
        
        /**
          * 客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
        */
		@Column(name="agent_canteen_id")
		private   Long   agentCanteenId ;
	    
        
        /**
          * 客户编号
        */
		@Column(name="client_num")
		private   Long   clientNum ;
		/**
		 * 地理信息坐标
		 */
	    @Column(name="position")
        private  String position;
    
	    /**
	     * 上一次保护关联的业务员Id
	     */
	    @Column(name="last_protect_business_id")
	    private   Long   lastProtectBusinessId ;
	    
	    /**
	     * 解除保护关系(踢出,成为散客)时间
	     */
	    @Column(name="relieve_protect_time")
	    private   Date   relieveProtectTime ;
     
        //默认空构造函数
	    public  CrmClientResourcePO(){
	
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
          * 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
        */
        public void setClientType(Integer clientType){
	      this.clientType=clientType;    
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
          * @return 客户类型	1.代理商，2.厂内食堂，3.校内食堂，4.独立食堂
        */
        public Integer getClientType(){ 
	      return this.clientType;    
        } 
        /**
          * 客户性质	1.散客，2.保护客户，3.正式客户
        */
        public void setClientNature(Integer clientNature){
	      this.clientNature=clientNature;    
        }
         
	    /**
          * @return 客户性质	1.散客，2.保护客户，3.正式客户
        */
        public Integer getClientNature(){ 
	      return this.clientNature;    
        } 
        /**
          * 名称
        */
        public void setName(String name){
	      this.name=name;    
        }
         
	    /**
          * @return 名称
        */
        public String getName(){ 
	      return this.name;    
        } 
        /**
          * 联系人
        */
        public void setContact(String contact){
	      this.contact=contact;    
        }
         
	    /**
          * @return 联系人
        */
        public String getContact(){ 
	      return this.contact;    
        } 
        /**
          * 联系人
        */
        public void setMobile(String mobile){
	      this.mobile=mobile;    
        }
         
	    /**
          * @return 联系人
        */
        public String getMobile(){ 
	      return this.mobile;    
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
          * 保护截止期
        */
        public void setProtectDeadline(Date protectDeadline){
	      this.protectDeadline=protectDeadline;    
        }
         
	    /**
          * @return 保护截止期
        */
        public Date getProtectDeadline(){ 
	      return this.protectDeadline;    
        } 
        /**
          * 创建时间
        */
        public void setCreateTime(Date createTime){
	      this.createTime=createTime;    
        }
         
	    /**
          * @return 创建时间
        */
        public Date getCreateTime(){ 
	      return this.createTime;    
        } 
        /**
          * 删除标识	1.存在0.删除
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 删除标识	1.存在0.删除
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        } 
        /**
          * 地址
        */
        public void setAddress(String address){
	      this.address=address;    
        }
         
	    /**
          * @return 地址
        */
        public String getAddress(){ 
	      return this.address;    
        } 
        /**
          * 跟踪次数
        */
        public void setTailNum(Long tailNum){
	      this.tailNum=tailNum;    
        }
         
	    /**
          * @return 跟踪次数
        */
        public Long getTailNum(){ 
	      return this.tailNum;    
        } 
        /**
          * 客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
        */
        public void setAgentCanteenId(Long agentCanteenId){
	      this.agentCanteenId=agentCanteenId;    
        }
         
	    /**
          * @return 客户ID	根据client_type，如果是1，就是crm_agent表id，否则是crm_canteen_base_info
        */
        public Long getAgentCanteenId(){ 
	      return this.agentCanteenId;    
        } 
        /**
          * 客户编号
        */
        public void setClientNum(Long clientNum){
	      this.clientNum=clientNum;    
        }
         
	    /**
          * @return 客户编号
        */
        public Long getClientNum(){ 
	      return this.clientNum;    
        }


		/**
		 * @return the checkStatus
		 */
		public Integer getCheckStatus() {
			return checkStatus;
		}


		/**
		 * @param checkStatus the checkStatus to set
		 */
		public void setCheckStatus(Integer checkStatus) {
			this.checkStatus = checkStatus;
		}

		

		public String getPosition() {
			return position;
		}


		public void setPosition(String position) {
			this.position = position;
		}


		/**
		 * @return the applyTime
		 */
		public Date getApplyTime() {
			return applyTime;
		}


		/**
		 * @param applyTime the applyTime to set
		 */
		public void setApplyTime(Date applyTime) {
			this.applyTime = applyTime;
		}


		public Date getApplyApproveTime() {
			return applyApproveTime;
		}


		public void setApplyApproveTime(Date applyApproveTime) {
			this.applyApproveTime = applyApproveTime;
		}


		/**
		 * @return the agentId
		 */
		public Long getAgentId() {
			return agentId;
		}


		/**
		 * @param agentId the agentId to set
		 */
		public void setAgentId(Long agentId) {
			this.agentId = agentId;
		}


		/**
		 * @return the customerAttribute
		 */
		public Integer getCustomerAttribute() {
			return customerAttribute;
		}


		/**
		 * @param customerAttribute the customerAttribute to set
		 */
		public void setCustomerAttribute(Integer customerAttribute) {
			this.customerAttribute = customerAttribute;
		}


		/**
		 * @return the areaCode
		 */
		public Long getAreaCode() {
			return areaCode;
		}


		/**
		 * @param areaCode the areaCode to set
		 */
		public void setAreaCode(Long areaCode) {
			this.areaCode = areaCode;
		}


		public Long getLastProtectBusinessId() {
			return lastProtectBusinessId;
		}


		public void setLastProtectBusinessId(Long lastProtectBusinessId) {
			this.lastProtectBusinessId = lastProtectBusinessId;
		}


		public Date getRelieveProtectTime() {
			return relieveProtectTime;
		}


		public void setRelieveProtectTime(Date relieveProtectTime) {
			this.relieveProtectTime = relieveProtectTime;
		} 
     
}
