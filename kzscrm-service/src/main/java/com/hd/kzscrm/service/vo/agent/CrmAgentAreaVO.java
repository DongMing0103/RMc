package com.hd.kzscrm.service.vo.agent;

import com.hd.kzscrm.service.vo.BaseVO;
import com.hd.wolverine.aop.Column;

public class CrmAgentAreaVO implements BaseVO{

	 /**
     * 主键
   */
   private   Long   id ;
   
   private Integer delFlag;
   /**
     * 区域代码
   */
	private   Long   areaCode ;
   
   
   /**
     * 代理商id
   */
	private   Long   agentId ;
   
   
   /**
     * 创建时间
   */
	private   java.util.Date   createTime ;
   
   
   /**
     * 创建人id
   */
	private   Long   createUid ;
   
   
   
   
   /**
     * 修改时间
   */
	private   java.util.Date   updateTime ;
   
   
   /**
     * 修改人id
   */
	@Column(name="update_uid")
	private   Long   updateUid ;
   
   



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
