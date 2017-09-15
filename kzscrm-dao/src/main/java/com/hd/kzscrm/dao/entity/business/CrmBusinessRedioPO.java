package com.hd.kzscrm.dao.entity.business;

import java.util.*;
import java.io.Serializable;
import java.math.*;
import com.hd.wolverine.aop.AutoIncrease;
import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusinessredio

@Entity
@Table(name="crm_business_redio")
public class CrmBusinessRedioPO implements Serializable {
   
        
        /**
          * 主键
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 年
        */
		@Column(name="year")
		private   Integer   year ;
	    
        
        /**
          * 业务类型	1业务员 2代理商
        */
		@Column(name="business_type")
		private   Integer   businessType ;
	    
        
        /**
          * 下降比例	
        */
		@Column(name="redio")
		private   BigDecimal   redio ;
	    
        
        /**
          * 创建时间
        */
		@Column(name="create_time")
		private   java.util.Date   createTime ;
	    
        
        /**
          * 删除标识 0删除 1正常
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
        
    
     
        //默认空构造函数
	    public  CrmBusinessRedioPO(){
	
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
        
        public Integer getYear() {
			return year;
		}


		public void setYear(Integer year) {
			this.year = year;
		}


		/**
          * 业务类型	1业务员 2代理商
        */
        public void setBusinessType(Integer businessType){
	      this.businessType=businessType;    
        }
         
	    /**
          * @return 业务类型	1业务员 2代理商
        */
        public Integer getBusinessType(){ 
	      return this.businessType;    
        } 
        /**
          * 下降比例	
        */
        public void setRedio(BigDecimal redio){
	      this.redio=redio;    
        }
         
	    /**
          * @return 下降比例	
        */
        public BigDecimal getRedio(){ 
	      return this.redio;    
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
          * 删除标识 0删除 1正常
        */
        public void setDelFlag(Integer delFlag){
	      this.delFlag=delFlag;    
        }
         
	    /**
          * @return 删除标识 0删除 1正常
        */
        public Integer getDelFlag(){ 
	      return this.delFlag;    
        } 
     
}
