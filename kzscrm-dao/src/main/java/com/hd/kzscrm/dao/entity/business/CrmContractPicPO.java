package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmcontractpic

@Entity
@Table(name="crm_contract_pic")
public class CrmContractPicPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * AGENTENTERID
          * 根据type来判断ID的来源是crm_agent,或crm_canteen_base_inf
        */
		@Column(name="agent_canteen_id")
		private   Long   agentCanteenId ;
		/**
		 * 图片路径
		 */
		@Column(name="pic_path")
		private String picPath;
	    
        
        /**
          * 类型(1.代理商 2.食堂)
        */
		@Column(name="type")
		private   Integer   type ;
	    
        
        /**
          * 删除标识 0删除     1正常
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
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
	    public  CrmContractPicPO(){
	
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
		 * @return the agentCanteenId
		 */
		public Long getAgentCanteenId() {
			return agentCanteenId;
		}


		/**
		 * @param agentCanteenId the agentCanteenId to set
		 */
		public void setAgentCanteenId(Long agentCanteenId) {
			this.agentCanteenId = agentCanteenId;
		}


		/**
          * 类型(1.代理商 2.食堂)
        */
        public void setType(Integer type){
	      this.type=type;    
        }
         
	    /**
          * @return 类型(1.代理商 2.食堂)
        */
        public Integer getType(){ 
	      return this.type;    
        } 
        /**
          * 删除标识 0删除     1正常
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 删除标识 0删除     1正常
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
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
          * 创建人
        */
        public void setCreateUid(Long createUid){
	      this.createUid=createUid;    
        }
         
	    /**
          * @return 创建人
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


		/**
		 * @return the picPath
		 */
		public String getPicPath() {
			return picPath;
		}


		/**
		 * @param picPath the picPath to set
		 */
		public void setPicPath(String picPath) {
			this.picPath = picPath;
		} 
     
}
