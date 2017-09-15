package com.hd.kzscrm.service.serviceimpl.perm;

//crmpermmenu
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.CrmCommonEnum.CrmCommonDelFlag;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermMenuPO;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleMenuPO;
import com.hd.kzscrm.service.param.perm.CrmPermMenuParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleMenuService;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.perm.CrmPermMenuVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmPermMenu 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPermMenuServiceImpl extends BaseServiceImpl implements ICrmPermMenuService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPermMenuServiceImpl.class);
	 @Resource
	 private ICrmPermRoleService iCrmPermRoleService;
     
	 @Resource
	 private ICrmPermRoleMenuService iCrmPermRoleMenuService;
     /**
     *  默认构造函数
     */
     
	 public CrmPermMenuServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    
        
         
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPermMenuVO> queryPage(CrmPermMenuParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmPermMenuName",param.getCrmPermMenuName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmPermMenuPO> crmpermmenuList = findPageByParams(CrmPermMenuPO.class,new Page<CrmPermMenuPO>(param.getOffset(),param.getLimit()),"CrmPermMenuPOMapper.queryPage",paramMap);
    	List<CrmPermMenuVO> rows = new ArrayList<CrmPermMenuVO>();
    	int total = 0;
    	if(crmpermmenuList.result != null){
    		rows =BeanConvertor.copyList(crmpermmenuList.result,CrmPermMenuVO.class);
    		total = crmpermmenuList.getTotalResult();
    	}    	
    	Page<CrmPermMenuVO> pageResult = new Page<CrmPermMenuVO>();
    	pageResult.result = rows;
    	pageResult.setTotalResult(total);
    	return pageResult;
    	
    }
    
    /**
     * 根据主键查询详情
     * @param param
     * @return
     */
    
    @Override
    public List<CrmPermMenuPO> listByParam(CrmPermMenuParam crmpermmenuParam){
    	ParamMap paramMap = new ParamMap(crmpermmenuParam);
    	return commonDao.listByParams(CrmPermMenuPO.class,"CrmPermMenuPOMapper.queryList",paramMap);
    }
    
    @Override
    public List<CrmPermMenuVO> getMenuAndSource(CrmPermMenuParam crmpermmenuParam){
    	List<CrmPermMenuVO> crmPermMenuVOs = BeanConvertor.copyList(this.listByParam(crmpermmenuParam),CrmPermMenuVO.class);
		return crmPermMenuVOs;
    }
    /**
     * 根据角色，获取已勾选的数据
     * @author 黄霄仪
     * @date 2017年7月3日 下午1:51:16
     */
    @Override
    public List<CrmPermMenuVO> getAuthority(CrmPermMenuParam crmpermmenuParam){
    	Long roleId = crmpermmenuParam.getRoleId();
    	List<CrmPermMenuVO> crmPermMenuVOs = this.getMenuAndSource(crmpermmenuParam);//获取所有权限
    	List<CrmPermRoleMenuPO> crmPermRoleMenuPOs = iCrmPermRoleMenuService.findByRoleId(roleId);
    	Set<Long> menuIdsSet=new HashSet<>();
    	for (CrmPermRoleMenuPO crmPermRoleMenuPO : crmPermRoleMenuPOs) {
    		menuIdsSet.add(crmPermRoleMenuPO.getMenuId());
		}
    	for (CrmPermMenuVO crmPermMenuVO : crmPermMenuVOs) {
    		if(menuIdsSet.contains(crmPermMenuVO.getId())){
    			crmPermMenuVO.setChecked(true);
    		}else{
    			crmPermMenuVO.setChecked(false);
    		}
		}
		return crmPermMenuVOs;
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmPermMenuPO po = this.get(CrmPermMenuPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmPermMenuPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmPermMenuPO> listPo = new ArrayList<CrmPermMenuPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmPermMenuPO po = this.get(CrmPermMenuPO.class, idl[i]);
    		if (po != null) {
    			po.setDelFlag(CrmCommonDelFlag.DELETE.getCode());
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmPermMenuPO po : listPo) {
			this.update(po);
		}
    	
    }
    @Override
    public void addMenu(CrmPermMenuParam param){
    	String code = param.getCode();
    	if(BeanUtils.isNotEmpty(code)){
    		int indexOf = code.indexOf(".");
    		if(indexOf==-1){
    			param.setCode("."+code);
    		}
    	}
    	Date currentTime=new Date();
    	param.setDelFlag(1);
    	param.setCreateTime(currentTime);
    	param.setUpdateTime(currentTime);
    	Integer level = param.getLevel();
    	Assert.notNull(level, "菜单等级不能为空");
    	if(level==1){
    		param.setParentId(0l);
    	}
    	
    	this.add(param);
    }
    /**
     * 新增
     */
    @Override
    public void add(CrmPermMenuParam param){
    	param.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_menu));
    	CrmPermMenuPO crmpermmenuPO = BeanConvertor.copy(param,CrmPermMenuPO.class);
    	this.save(crmpermmenuPO);
    }
    
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPermMenuPO po){
        po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_menu));
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPermMenuPO po){
     	this.update(po);
    }

	/**
	 * 根据菜单id或 父id查询
	* @Title: findByMemuId 
	* @author : lcl
	* @time : 2017年7月6日 上午9:27:09
	* @return CrmPermMenuPO    返回类型 
	* @throws
	 */
	@Override
	public CrmPermMenuPO findByMemuIdOrParentId(Long menuIdOrParentId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("id", menuIdOrParentId);
		List<CrmPermMenuPO> crmPermMenuPOs = listByParams(CrmPermMenuPO.class, "CrmPermMenuPOMapper.findByMemuIdOrParentId", paramMap);
		if(CollectionUtils.isNotEmpty(crmPermMenuPOs)){
			return crmPermMenuPOs.get(0);
		}
		return null;
	}
}

