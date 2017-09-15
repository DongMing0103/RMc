package com.hd.kzscrm.service.serviceimpl.perm;

//crmpermrole
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.Assert;
import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleMenuPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRolePO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleMenuParam;
import com.hd.kzscrm.service.param.perm.CrmPermRoleParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmPermRole 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPermRoleServiceImpl extends BaseServiceImpl implements ICrmPermRoleService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPermRoleServiceImpl.class);
     
	 @Resource
	 private ICrmPermRoleMenuService iCrmPermRoleMenuService;
     /**
     *  默认构造函数
     */
     
	 public CrmPermRoleServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPermRoleVO> queryPage(CrmPermRoleParam param) {
    	String nameLike = param.getNameLike();
    	if(BeanUtils.isNotEmpty(nameLike)){
    		char[] nameLikeArray = nameLike.toCharArray();
    		nameLike="%";
    		for (char c : nameLikeArray) {
				nameLike+=c;
			}
    		nameLike+="%";
    		param.setNameLike(nameLike);
    	}
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmPermRoleName",param.getCrmPermRoleName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmPermRolePO> crmpermroleList = findPageByParams(CrmPermRolePO.class,new Page<CrmPermRolePO>(param.getOffset(),param.getLimit()),"CrmPermRolePOMapper.queryPage",paramMap);
    	List<CrmPermRoleVO> rows = new ArrayList<CrmPermRoleVO>();
    	int total = 0;
    	if(crmpermroleList.result != null){
    		rows = BeanConvertor.copyList(crmpermroleList.result,CrmPermRoleVO.class);
    		total = crmpermroleList.getTotalResult();
    	}    	
    	Page<CrmPermRoleVO> pageResult = new Page<CrmPermRoleVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    /**
     * 根据用户类型获取数据
     * @author 黄霄仪
     * @date 2017年7月21日 上午10:53:09
     */
    @Override
    public List<CrmPermRolePO> findByUserType(Integer userType){
    	CrmPermRoleParam crmPermRoleParam=new CrmPermRoleParam();
    	crmPermRoleParam.setUserType(userType);
    	List<CrmPermRolePO> crmPermRolePOs = listByParam(crmPermRoleParam);
    	return crmPermRolePOs;
    }
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmPermRolePO> listByParam(CrmPermRoleParam crmpermroleParam){
    	ParamMap paramMap = new ParamMap(crmpermroleParam);
    	return commonDao.listByParams(CrmPermRolePO.class,"CrmPermRolePOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmPermRolePO po = this.get(CrmPermRolePO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmPermRolePOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmPermRolePO> listPo = new ArrayList<CrmPermRolePO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmPermRolePO po = this.get(CrmPermRolePO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmPermRolePO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmPermRoleParam param){
    	CrmPermRolePO crmpermrolePO = BeanConvertor.copy(param,CrmPermRolePO.class);
    	this.save(crmpermrolePO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPermRolePO po){
    	Long id = po.getId();
    	if(BeanUtils.isEmpty(id)){
    		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_role));
    	}
     	this.save(po);
    }
    
    @Override
    public void doSave(CrmPermRoleParam crmPermRoleParam){
    	Assert.notNull(crmPermRoleParam, "com.hd.kzscrm.service.serviceimpl.perm.CrmPermRoleServiceImpl.doSave(CrmPermRoleParam):crmPermRoleParam不能为空");
    	List<CrmPermRoleMenuParam> crmPermRoleMenuParams = crmPermRoleParam.getCrmPermRoleMenuParams();
    	
    	Long id=SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_role);
    	if(BeanUtils.isNotEmpty(crmPermRoleMenuParams)){
    		for (CrmPermRoleMenuParam crmPermRoleMenuParam : crmPermRoleMenuParams) {
    			crmPermRoleMenuParam.setRoleId(id);
    			iCrmPermRoleMenuService.saveEntity(BeanConvertor.copy(crmPermRoleMenuParam, CrmPermRoleMenuPO.class));
			}
    	}
    	Date currentDate=new Date();
    	crmPermRoleParam.setId(id);
    	crmPermRoleParam.setCreateTime(currentDate);
    	crmPermRoleParam.setUpdateTime(currentDate);
    	this.saveEntity(BeanConvertor
				.convertBean(crmPermRoleParam, CrmPermRolePO.class));
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPermRoleParam crmPermRoleParam){
    	Assert.notNull(crmPermRoleParam, "com.hd.kzscrm.service.serviceimpl.perm.CrmPermRoleServiceImpl.doSave(CrmPermRoleParam):crmPermRoleParam不能为空");
    	Long id = crmPermRoleParam.getId();
    	Assert.notNull(id, "com.hd.kzscrm.service.serviceimpl.perm.CrmPermRoleServiceImpl.doSave(CrmPermRoleParam):角色ID不能为空");
    	List<CrmPermRoleMenuParam> crmPermRoleMenuParams = crmPermRoleParam.getCrmPermRoleMenuParams();
    	if(BeanUtils.isNotEmpty(crmPermRoleMenuParams)){
    		iCrmPermRoleMenuService.delByRoleId(id);
    		for (CrmPermRoleMenuParam crmPermRoleMenuParam : crmPermRoleMenuParams) {
				crmPermRoleMenuParam.setRoleId(id);
    			iCrmPermRoleMenuService.saveEntity(BeanConvertor.copy(crmPermRoleMenuParam, CrmPermRoleMenuPO.class));
			}
    	}
     	this.update(BeanConvertor
				.convertBean(crmPermRoleParam, CrmPermRolePO.class));
    }
}

