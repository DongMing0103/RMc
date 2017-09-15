package com.hd.kzscrm.dao.entity.area;

import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.hd.wolverine.aop.AutoIncrease;
import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//basearea

@Entity
@Table(name="base_area")
public class BaseAreaPO implements Serializable {
   
        
        /**
          * 主键，地区代码前2码为国家， 第3-4码为大区，      5-6位表示省级编码 7-8码市级编码
        */
        @Column(name="area_code")
        @Id
	    // @AutoIncrease
	    private   Long   areaCode ;
        
        /**
          * 地区名称
        */
		@Column(name="area_name")
		private   String   areaName ;
	    
        
        /**
          * 级别  0国家   1大区   2省级  3地市级  4县市级
        */
		@Column(name="area_level")
		private   Long   areaLevel ;
	    
        
        /**
          * 父code
        */
		@Column(name="parent_code")
		private   Long   parentCode ;
	    
        
        /**
          * LANGUAGEコード
        */
		@Column(name="language_code")
		private   Long   languageCode ;
	    
        
        /**
          * 追加時間
        */
		@Column(name="create_time")
		private   java.util.Date   createTime ;
	    
        
        /**
          * 追加ID
        */
		@Column(name="create_id")
		private   Long   createId ;
	    
        
        /**
          * 更新時間
        */
		@Column(name="update_time")
		private   java.util.Date   updateTime ;
	    
        
        /**
          * 更新ID
        */
		@Column(name="update_id")
		private   Long   updateId ;
	    
        
        /**
          * 削除フラグ
        */
		@Column(name="delete_flag")
		private   String   deleteFlag ;
	    
        
        /**
          * 主键
        */
		@Column(name="id")
		private   Long   id ;
	    
        
    
     
        //默认空构造函数
	    public  BaseAreaPO(){
	
	    }
	
	
		//get set 方法
        /**
          * 主键，地区代码前2码为国家， 第3-4码为大区，      5-6位表示省级编码 7-8码市级编码
        */
        public void setAreaCode(Long areaCode){
	      this.areaCode=areaCode;    
        }
         
	    /**
          * @return 主键，地区代码前2码为国家， 第3-4码为大区，      5-6位表示省级编码 7-8码市级编码
        */
        public Long getAreaCode(){ 
	      return this.areaCode;    
        } 
        /**
          * 地区名称
        */
        public void setAreaName(String areaName){
	      this.areaName=areaName;    
        }
         
	    /**
          * @return 地区名称
        */
        public String getAreaName(){ 
	      return this.areaName;    
        } 
        /**
          * 级别  0国家   1大区   2省级  3地市级  4县市级
        */
        public void setAreaLevel(Long areaLevel){
	      this.areaLevel=areaLevel;    
        }
         
	    /**
          * @return 级别  0国家   1大区   2省级  3地市级  4县市级
        */
        public Long getAreaLevel(){ 
	      return this.areaLevel;    
        } 
        /**
          * 父code
        */
        public void setParentCode(Long parentCode){
	      this.parentCode=parentCode;    
        }
         
	    /**
          * @return 父code
        */
        public Long getParentCode(){ 
	      return this.parentCode;    
        } 
        /**
          * LANGUAGEコード
        */
        public void setLanguageCode(Long languageCode){
	      this.languageCode=languageCode;    
        }
         
	    /**
          * @return LANGUAGEコード
        */
        public Long getLanguageCode(){ 
	      return this.languageCode;    
        } 
        /**
          * 追加時間
        */
        public void setCreateTime(java.util.Date createTime){
	      this.createTime=createTime;    
        }
         
	    /**
          * @return 追加時間
        */
        public java.util.Date getCreateTime(){ 
	      return this.createTime;    
        } 
        /**
          * 追加ID
        */
        public void setCreateId(Long createId){
	      this.createId=createId;    
        }
         
	    /**
          * @return 追加ID
        */
        public Long getCreateId(){ 
	      return this.createId;    
        } 
        /**
          * 更新時間
        */
        public void setUpdateTime(java.util.Date updateTime){
	      this.updateTime=updateTime;    
        }
         
	    /**
          * @return 更新時間
        */
        public java.util.Date getUpdateTime(){ 
	      return this.updateTime;    
        } 
        /**
          * 更新ID
        */
        public void setUpdateId(Long updateId){
	      this.updateId=updateId;    
        }
         
	    /**
          * @return 更新ID
        */
        public Long getUpdateId(){ 
	      return this.updateId;    
        } 
        /**
          * 削除フラグ
        */
        public void setDeleteFlag(String deleteFlag){
	      this.deleteFlag=deleteFlag;    
        }
         
	    /**
          * @return 削除フラグ
        */
        public String getDeleteFlag(){ 
	      return this.deleteFlag;    
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
        public Long getId(){ 
	      return this.id;    
        } 
     
}
