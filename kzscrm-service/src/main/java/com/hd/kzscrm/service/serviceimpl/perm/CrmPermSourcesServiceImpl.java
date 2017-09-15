package com.hd.kzscrm.service.serviceimpl.perm;

//crmpermsources
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermMenuPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleSourcesPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermSourcesPO;
import com.hd.kzscrm.service.param.perm.CrmPermSourcesParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleSourcesService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermSourcesService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.perm.CrmPermSourcesVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmPermSources 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPermSourcesServiceImpl extends BaseServiceImpl implements ICrmPermSourcesService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPermSourcesServiceImpl.class);
     //系统菜单表
	 @Autowired
	 ICrmPermMenuService permMenuService;
	 //
	 @Autowired
	 ICrmPermRoleSourcesService roleSourcesService;
	 
     /**
     *  默认构造函数
     */
     
	 public CrmPermSourcesServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmPermSourcesVO> convertPOToVO(List<CrmPermSourcesPO> crmpermsourcesList){
    	List<CrmPermSourcesVO> crmpermsourcesVoList = new ArrayList<CrmPermSourcesVO>();
    	if (CollectionUtils.isEmpty(crmpermsourcesList)) {
    		return crmpermsourcesVoList;
    	}
    	for (CrmPermSourcesPO tag : crmpermsourcesList) {
    		CrmPermSourcesVO tagVo = BeanConvertor.copy(tag,CrmPermSourcesVO.class);
    		crmpermsourcesVoList.add(tagVo);
    	}
    	return crmpermsourcesVoList;
    }
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPermSourcesVO> queryPage(CrmPermSourcesParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmPermSourcesName",param.getCrmPermSourcesName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmPermSourcesPO> crmpermsourcesList = findPageByParams(CrmPermSourcesPO.class,new Page<CrmPermSourcesPO>(param.getOffset(),param.getLimit()),"CrmPermSourcesPOMapper.queryPage",paramMap);
    	List<CrmPermSourcesPO> permSourcesPOs = crmpermsourcesList.result;
    	List<CrmPermSourcesVO> rows = new ArrayList<CrmPermSourcesVO>();
    	int total = 0;
    	if(crmpermsourcesList.result != null){
    		for(int i =0;i < permSourcesPOs.size() ;i++){
    			CrmPermSourcesVO cSourcesVO = BeanConvertor.convertBean(permSourcesPOs.get(i), CrmPermSourcesVO.class);
    			if(BeanUtils.isNotEmpty(permSourcesPOs.get(i).getMenuId())){//二级菜单id
    				CrmPermMenuPO menuPO = permMenuService.findByMemuIdOrParentId(permSourcesPOs.get(i).getMenuId());//
    				if(BeanUtils.isNotEmpty(menuPO)){
    					cSourcesVO.setSecondName(menuPO.getName());
    				}
    				
    			}
    			if(BeanUtils.isNotEmpty(permSourcesPOs.get(i).getParentId())){//一级菜单id
    				CrmPermMenuPO menuPO = permMenuService.findByMemuIdOrParentId(permSourcesPOs.get(i).getParentId());//
    				if(BeanUtils.isNotEmpty(menuPO)){
    					cSourcesVO.setFirstName(menuPO.getName());
    				}
    				
    			}
    			rows.add(cSourcesVO);
    		}
    		
    		total = crmpermsourcesList.getTotalResult();
    	}    	
    	Page<CrmPermSourcesVO> pageResult = new Page<CrmPermSourcesVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    /**
     * 查询
     */
    @Override
    public Page<CrmPermSourcesVO> queryPageBasic(CrmPermSourcesParam param) {
    	return this.queryPage(param);
    	
    }
    
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmPermSourcesPO> listByParam(CrmPermSourcesParam crmpermsourcesParam){
    	ParamMap paramMap = new ParamMap(crmpermsourcesParam);
    	return commonDao.listByParams(CrmPermSourcesPO.class,"CrmPermSourcesPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmPermSourcesPO po = this.get(CrmPermSourcesPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmPermSourcesPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmPermSourcesPO> listPo = new ArrayList<CrmPermSourcesPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmPermSourcesPO po = this.get(CrmPermSourcesPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmPermSourcesPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmPermSourcesParam param){
    	CrmPermSourcesPO crmpermsourcesPO = BeanConvertor.copy(param,CrmPermSourcesPO.class);
    	this.save(crmpermsourcesPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPermSourcesParam param){
    	Date currentDate=new Date();
    	Long id = param.getId();
    	if(BeanUtils.isEmpty(id)){
    		param.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_sources));//资源表id
    		Long roleId = param.getRoleId();//角色id
    		CrmPermRoleSourcesPO roleSourcesPO = new CrmPermRoleSourcesPO();
    		roleSourcesPO.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_role_sources));
    		roleSourcesPO.setDelFlag(1);
    		roleSourcesPO.setRoleId(roleId);
    		roleSourcesPO.setSourcesId(param.getId());
    		roleSourcesService.save(roleSourcesPO);//保存中间表
    	}
    	if(BeanUtils.isNotEmpty(param.getFrontContrlName())){
    		if(!param.getFrontContrlName().contains(".") && param.getFrontContrlName().charAt(0) !='.'){
    			param.setFrontContrlName("."+param.getFrontContrlName());
    			
    		}
    	}
    	param.setChecked(1);//是否启用 （ps: 是否别勾选）1选择 0未选择
    	param.setCreateTime(currentDate);
    	param.setUpdateTime(currentDate);
    	
    	
    	
    	CrmPermSourcesPO po=BeanConvertor.convertBean(param, CrmPermSourcesPO.class);
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPermSourcesPO po){
     	this.update(po);
    }

	/**
	 * 根据角色id查询 全部按钮资源
	* @Title: findByRoleId 
	* @author : lcl
	* @time : 2017年7月6日 下午4:56:07
	* @return List<CrmPermSourcesPO>    返回类型 
	* @throws
	 */
	@Override
	public List<CrmPermSourcesPO> findByRoleId(Long roleId) {
		ParamMap paramMap = new  ParamMap();
		List<Long> sourceList = new ArrayList<Long>();
		if(BeanUtils.isNotEmpty(roleId)){//根据角色id查询 中间表
			List<CrmPermRoleSourcesPO> permRoleSourcesPOs = roleSourcesService.findByRoleId(roleId);
			if(CollectionUtils.isNotEmpty(permRoleSourcesPOs)){
				for(int i =0 ; i < permRoleSourcesPOs.size() ;  i++){
					sourceList.add(permRoleSourcesPOs.get(i).getSourcesId());
				}
			}
			paramMap.put("roleId", roleId);
			paramMap.put("sourceList", sourceList);//资源按钮的id集合
			List<CrmPermSourcesPO> crmPermSourcesPOs = listByParams(CrmPermSourcesPO.class, "CrmPermSourcesPOMapper.findBySourceIds", paramMap);
			if(CollectionUtils.isNotEmpty(crmPermSourcesPOs)){
				return crmPermSourcesPOs;
			}
			
		}
		return null;
	}
}

