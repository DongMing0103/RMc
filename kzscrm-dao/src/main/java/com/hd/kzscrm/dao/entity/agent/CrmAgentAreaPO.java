package com.hd.kzscrm.dao.entity.agent;

import java.io.Serializable;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmagentarea

@Entity
@Table(name="crm_agent_area")
public class CrmAgentAreaPO implements Serializable {
   
        
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
          * 区域代码
        */
		@Column(name="area_code")
		private   Long   areaCode ;
	    
        
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
          * 修改时间
        */
		@Column(name="update_time")
		private   java.util.Date   updateTime ;
	    
        
        /**
          * 修改人id
        */
		@Column(name="update_uid")
		private   Long   updateUid ;
	    
        
    
     
        //默认空构造函数
	    public  CrmAgentAreaPO(){
	
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
          * 区域代码
        */
        public void setAreaCode(Long areaCode){
	      this.areaCode=areaCode;    
        }
         
	    /**
          * @return 区域代码
        */
        public Long getAreaCode(){ 
	      return this.areaCode;    
        } 
        /**
          * 代理商id
        */
        public void setAgentId(Long agentId){
	      this.agentId=agentId;    
        }
         
	    /**
          * @return 代理商id
        */
        public Long getAgentId(){ 
	      return this.agentId;    
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
     
}
