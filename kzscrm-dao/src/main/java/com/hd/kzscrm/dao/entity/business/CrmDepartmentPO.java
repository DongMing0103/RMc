package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmdepartment

@Entity
@Table(name="crm_department")
public class CrmDepartmentPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 父ID
        */
		@Column(name="parent_id")
		private   Long   parentId ;
	    
        
        /**
          * 级别
        */
		@Column(name="level")
		private   Short   level ;
	    
        
        /**
          * 编号
        */
		@Column(name="id_no")
		private   String   idNo ;
	    
        
        /**
          * 名称
        */
		@Column(name="d_name")
		private   String   dName ;
	    
        
        /**
          * 代理商家id
        */
		@Column(name="agent_business_id")
		private   Long   agentBusinessId ;
	    
        
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
          * 状态，0停用，1正常
        */
		@Column(name="status")
		private   Integer   status ;
	    
        
        /**
          * 是否删除，0删除，1正常
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
		/**
		 * 类型，1.平台，2.代理商
		 */
		@Column(name="type")
		private Integer type;
    
     
        //默认空构造函数
	    public  CrmDepartmentPO(){
	
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
          * 父ID
        */
        public void setParentId(Long parentId){
	      this.parentId=parentId;    
        }
         
	    /**
          * @return 父ID
        */
        public Long getParentId(){ 
	      return this.parentId;    
        } 
        /**
          * 级别
        */
        public void setLevel(Short level){
	      this.level=level;    
        }
         
	    /**
          * @return 级别
        */
        public Short getLevel(){ 
	      return this.level;    
        } 
        /**
          * 编号
        */
        public void setIdNo(String idNo){
	      this.idNo=idNo;    
        }
         
	    /**
          * @return 编号
        */
        public String getIdNo(){ 
	      return this.idNo;    
        } 
        /**
          * 名称
        */
        public void setDName(String dName){
	      this.dName=dName;    
        }
         
	    /**
          * @return 名称
        */
        public String getDName(){ 
	      return this.dName;    
        } 
        /**
          * 代理商家id
        */
        public void setAgentBusinessId(Long agentBusinessId){
	      this.agentBusinessId=agentBusinessId;    
        }
         
	    /**
          * @return 代理商家id
        */
        public Long getAgentBusinessId(){ 
	      return this.agentBusinessId;    
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
          * 状态，0停用，1正常
        */
        public void setStatus(Integer status){
	      this.status=status;    
        }
         
	    /**
          * @return 状态，0停用，1正常
        */
        public Integer getStatus(){ 
	      return this.status;    
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
		 * @return the dName
		 */
		public String getdName() {
			return dName;
		}


		/**
		 * @param dName the dName to set
		 */
		public void setdName(String dName) {
			this.dName = dName;
		}


		/**
		 * @return the type
		 */
		public Integer getType() {
			return type;
		}


		/**
		 * @param type the type to set
		 */
		public void setType(Integer type) {
			this.type = type;
		} 
     
}
