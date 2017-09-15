package com.hd.kzscrm.service.serviceimpl.perm;

//crmpermrolemenu
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hd.kzscrm.common.enums.BaseExceptionEnum;
import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.CollectionsUtil;
import com.hd.kzscrm.dao.entity.perm.CrmPermRoleMenuPO;
import com.hd.kzscrm.service.param.perm.CrmPermRoleMenuParam;
import com.hd.kzscrm.service.serviceInter.perm.ICrmPermRoleMenuService;
import com.hd.kzscrm.service.util.SystemCacheHelper;
import com.hd.kzscrm.service.vo.perm.CrmPermRoleMenuVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;


/**
 *
   
 * @description   crmPermRoleMenu 对应的（业务逻辑类）
 *
 */
@Service
@Transactional
public class CrmPermRoleMenuServiceImpl extends BaseServiceImpl implements ICrmPermRoleMenuService {
  
     // 日志服务对象
	 protected static final Logger logger = LoggerFactory.getLogger(CrmPermRoleMenuServiceImpl.class);
     
     /**
     *  默认构造函数
     */
     
	 public CrmPermRoleMenuServiceImpl() {
	   
	 }
	 
    //自定义方法
    //*****************************************************************************************************************
    
    /**
     * PO转换为VO
     * @param param
     * @return
     */
    private List<CrmPermRoleMenuVO> convertPOToVO(List<CrmPermRoleMenuPO> crmpermrolemenuList){
    	List<CrmPermRoleMenuVO> crmpermrolemenuVoList = new ArrayList<CrmPermRoleMenuVO>();
    	if (CollectionUtils.isEmpty(crmpermrolemenuList)) {
    		return crmpermrolemenuVoList;
    	}
    	for (CrmPermRoleMenuPO tag : crmpermrolemenuList) {
    		CrmPermRoleMenuVO tagVo = BeanConvertor.copy(tag,CrmPermRoleMenuVO.class);
    		crmpermrolemenuVoList.add(tagVo);
    	}
    	return crmpermrolemenuVoList;
    }
    
    @Override
    public List<CrmPermRoleMenuPO> findByRoleId(Long roleId){
    	CrmPermRoleMenuParam crmpermrolemenuParam=new CrmPermRoleMenuParam();
    	crmpermrolemenuParam.setRoleId(roleId);
    	return this.listByParam(crmpermrolemenuParam);
    }
        
    /**
     * 查询
     */
    @Override
    public Page<CrmPermRoleMenuVO> queryPage(CrmPermRoleMenuParam param) {
    	ParamMap paramMap = new ParamMap(param);
    	/*
    	paramMap.put("CrmPermRoleMenuName",param.getCrmPermRoleMenuName());
    	paramMap.put("status",param.getStatus());
    	String sortStr = param.getSort();
    	if(param.getSortConditionMap().size() == 0){
    		if(StringUtil.isEmpty(sortStr)){
    			paramMap.addOrder("sort_no",asc);
    		}
    	}
    	*/
    	Page<CrmPermRoleMenuPO> crmpermrolemenuList = findPageByParams(CrmPermRoleMenuPO.class,new Page<CrmPermRoleMenuPO>(param.getOffset(),param.getLimit()),"CrmPermRoleMenuPOMapper.queryPage",paramMap);
    	List<CrmPermRoleMenuVO> rows = new ArrayList<CrmPermRoleMenuVO>();
    	int total = 0;
    	if(crmpermrolemenuList.result != null){
    		rows = convertPOToVO(crmpermrolemenuList.result);
    		total = crmpermrolemenuList.getTotalResult();
    	}    	
    	Page<CrmPermRoleMenuVO> pageResult = new Page<CrmPermRoleMenuVO>();
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
    public List<CrmPermRoleMenuPO> listByParam(CrmPermRoleMenuParam crmpermrolemenuParam){
    	ParamMap paramMap = new ParamMap(crmpermrolemenuParam);
    	return commonDao.listByParams(CrmPermRoleMenuPO.class,"CrmPermRoleMenuPOMapper.queryList",paramMap);
    }
    
    /**
     * 删除
     */
    @Override
    @Transactional
    public void deleteById(Long id) throws BizException{
    	/*Long id = Long.parseLong(ids);
    	CrmPermRoleMenuPO po = this.get(CrmPermRoleMenuPO.class,id);
    	if(po != null){
    		po.setDeleteFlag(DeleteFlagEnum.DELETED.getShortCode());
    		this.update(po);
    	}else{
    		throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    	}*/
    	ParamMap paramMap = new ParamMap();
        paramMap.put("id", id);
        this.execute("CrmPermRoleMenuPOMapper.deleteById", paramMap);
    }
    
    /**
     * 中台删除
     */
    @Override
    public void deleteByIds(String ids) throws BizException{
    	Long[] idl = CollectionsUtil.transStringToArray(ids, ",");
    	List<CrmPermRoleMenuPO> listPo = new ArrayList<CrmPermRoleMenuPO>(idl.length);
    	for (int i=0; i<idl.length; i++) {
    		CrmPermRoleMenuPO po = this.get(CrmPermRoleMenuPO.class, idl[i]);
    		if (po != null) {
    			listPo.add(po);
    		} else {
    			throw new BizException(BaseExceptionEnum.UPDATE_FAILURE);
    		}
    	}
		for (CrmPermRoleMenuPO po : listPo) {
			this.update(po);
		}
    	
    }
    
    /**
     * 新增
     */
    @Override
    public void add(CrmPermRoleMenuParam param){
    	CrmPermRoleMenuPO crmpermrolemenuPO = BeanConvertor.copy(param,CrmPermRoleMenuPO.class);
    	this.save(crmpermrolemenuPO);
    }
    /**
     * 根据角色ID删除映射表的数据
     * @author 黄霄仪
     * @date 2017年7月3日 上午11:49:00
     */
    @Override
    public void delByRoleId(Long roleId){
    	ParamMap paramMap = new ParamMap();
        paramMap.put("roleId", roleId);
        this.execute("CrmPermRoleMenuPOMapper.delByRoleId", paramMap);
    }
    /**
     * 新增
     */
    @Override
    public void saveEntity(CrmPermRoleMenuPO po){
    	Long id = po.getId();
    	if(BeanUtils.isEmpty(id)){
    		po.setId(SystemCacheHelper.genNextIDValue(DatabaseTableNameEnum.crm_perm_role_menu));
    	}
     	this.save(po);
    }
    
    /**
     * update
     */
    @Override
    public void updateEntity(CrmPermRoleMenuPO po){
     	this.update(po);
    }
}

