package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmposition

@Entity
@Table(name="crm_position")
public class CrmPositionPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 岗位名称
        */
		@Column(name="name")
		private   String   name ;
	    
        
        /**
          * 代理商id
        */
		@Column(name="agent_id")
		private   Long   agentId ;
	    
        
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
		 * 类型，1.平台，2.代理商
		 */
		@Column(name="type")
		private Integer type;
	    
        
    
     
        //默认空构造函数
	    public  CrmPositionPO(){
	
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
          * 岗位名称
        */
        public void setName(String name){
	      this.name=name;    
        }
         
	    /**
          * @return 岗位名称
        */
        public String getName(){ 
	      return this.name;    
        } 
        /**
          * 代理商id
        */
        public void setAgentBusinessId(Long agentId){
	      this.agentId=agentId;    
        }
         
	    /**
          * @return 代理商id
        */
        public Long getAgentId(){ 
	      return this.agentId;    
        } 
        
        public void setAgentId(Long agentId) {
			this.agentId = agentId;
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
