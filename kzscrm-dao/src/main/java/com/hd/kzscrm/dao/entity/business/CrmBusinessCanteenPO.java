package com.hd.kzscrm.dao.entity.business;

import java.io.Serializable;
import java.util.Date;

import com.hd.wolverine.aop.Column;
import com.hd.wolverine.aop.Entity;
import com.hd.wolverine.aop.Id;
import com.hd.wolverine.aop.Table;

//crmbusinesscanteen

@Entity
@Table(name="crm_business_canteen")
public class CrmBusinessCanteenPO implements Serializable {
   
        
        /**
          * ID
        */
        @Column(name="id")
        @Id
	    // @AutoIncrease
	    private   Long   id ;
        
        /**
          * 业务员ID	crm_business
        */
		@Column(name="business_id")
		private   Long   businessId ;
	    
        
        /**
          * 商家ID	cantenn_base_info
        */
		@Column(name="canteen_id")
		private   Long   canteenId ;
	    
        
        /**
          * 食堂类型	1.厂内食堂，2.校内食堂，3.独立食堂
        */
		@Column(name="canteen_type")
		private   Integer   canteenType ;
	    /**
	     * 创建时间
	     */
		@Column(name="create_time")
		private Date createTime;
        
        /**
          * 删除标识	1.存在0.删除
        */
		@Column(name="del_flag")
		private   Integer   delFlag ;
	    
		@Column(name="head_id_card")
		private   Integer   headIdCard ;
	    
        
    
     
        //默认空构造函数
	    public  CrmBusinessCanteenPO(){
	
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
          * 业务员ID	crm_business
        */
        public void setBusinessId(Long businessId){
	      this.businessId=businessId;    
        }
         
	    /**
          * @return 业务员ID	crm_business
        */
        public Long getBusinessId(){ 
	      return this.businessId;    
        } 
        /**
          * 商家ID	cantenn_base_info
        */
        public void setCanteenId(Long canteenId){
	      this.canteenId=canteenId;    
        }
         
	    /**
          * @return 商家ID	cantenn_base_info
        */
        public Long getCanteenId(){ 
	      return this.canteenId;    
        } 
        /**
          * 食堂类型	1.厂内食堂，2.校内食堂，3.独立食堂
        */
        public void setCanteenType(Integer canteenType){
	      this.canteenType=canteenType;    
        }
         
	    /**
          * @return 食堂类型	1.厂内食堂，2.校内食堂，3.独立食堂
        */
        public Integer getCanteenType(){ 
	      return this.canteenType;    
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


		public Integer getHeadIdCard() {
			return headIdCard;
		}


		public void setHeadIdCard(Integer headIdCard) {
			this.headIdCard = headIdCard;
		} 
     
        
}
