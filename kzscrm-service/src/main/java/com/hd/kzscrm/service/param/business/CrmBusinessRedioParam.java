package com.hd.kzscrm.service.param.business;

import java.util.*;
import java.math.*;
import com.hd.kzscrm.common.param.PageParam;

public class CrmBusinessRedioParam extends PageParam {
		
					/**
	          * 主键
	        */
	    private   Long   id ;
							/**
	          * 年
	        */
			private   String   year ;
							/**
	          * 业务类型	1业务员 2代理商
	        */
	    private   Integer   businessType ;
							/**
	          * 下降比例	
	        */
	    private   BigDecimal   redio ;
							/**
	          * 创建时间
	        */
			private   String   createTime ;
							/**
	          * 删除标识 0删除 1正常
	        */
	    private   Integer   delFlag ;
		        
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
	          * 年
	        */
	        public void setYear(String year){
		      this.year=year;    
	        }
	         
		    /**
	          * @return 年
	        */
	        public String getYear(  ){ 
		      return this.year;    
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
	        public Integer getBusinessType(  ){ 
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
	        public BigDecimal getRedio(  ){ 
		      return this.redio;    
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
	          * 删除标识 0删除 1正常
	        */
	        public void setDelFlag(Integer delFlag){
		      this.delFlag=delFlag;    
	        }
	         
		    /**
	          * @return 删除标识 0删除 1正常
	        */
	        public Integer getDelFlag(  ){ 
		      return this.delFlag;    
	        } 
		        
}
