package com.hd.kzscrm.service.serviceInter.business;

import java.util.List;

import com.hd.kzscrm.common.exceptions.BizException;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.dao.entity.business.CrmTeamPO;
import com.hd.kzscrm.service.param.business.CrmBusinessParam;
import com.hd.kzscrm.service.param.business.CrmTeamParam;
import com.hd.kzscrm.service.vo.business.CrmBusinessVO;
import com.hd.kzscrm.service.vo.business.CrmTeamVO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;

/** 
 * 
 * @param  传入参数
 * @author create 郁圆圆
 * @date 2017年7月19日 上午9:50:33 
*/
public interface ICrmTeamService extends BaseService {

	public Page<CrmTeamVO> queryPage(CrmTeamParam param);

	public List<CrmTeamPO> listByParam(CrmTeamParam crmteamParam);

	public void deleteById(Long id) throws BizException;

	public void deleteByIds(String id) throws BizException;

	/** add */
	public void add(CrmTeamParam param);

	/**
	 * add save
	 */
	public void saveEntity(CrmTeamPO param);

	/**
	 * update save
	 */
	public void updateEntity(CrmTeamPO param);

	/**
	 * 跟主键查询对象
	 * 
	 * @param teamId
	 * @return
	 */
	public CrmTeamPO findByTeamId(Long teamId);
	
	/**
	 * 根据团队名称模糊查询
	 * @param condition
	 * @return
	 */
	public List<CrmTeamPO> findLikeByTeamName(String condition);

	/**
	 * 查询对象 返回集合List
	 * 
	 * @return
	 */
	public List<CrmTeamPO> findAll(CrmTeamParam param);

	/**
	 * 查询团队信息
	 * 
	 * @Title: commonQuery
	 * @Description: TODO
	 * @param @param
	 *            crmTeamParam
	 * @param @return
	 * @return List<CrmTeamPO> 返回类型
	 * @throws @author
	 *             LuGaogao
	 * @date 2017年5月31日 上午10:30:14
	 */
	public List<CrmTeamPO> commonQuery(CrmTeamParam crmTeamParam);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月1日 下午5:00:17
	 */
	CrmTeamPO getById(Long id);

	/**
	 * 导出查询
	 */
	public Page<CrmTeamVO> findPageSelect(CrmTeamParam param);

	Page<CrmTeamVO> queryPageteam(CrmTeamParam param);

	/**
	 * 团队结构
	 * 
	 * @author 黄霄仪
	 * @date 2017年6月2日 下午3:17:01
	 */
	Page<CrmTeamVO> showTeamStrctrue(CrmTeamParam param);

	/**
	 * 
	 * @Title: getLowestLevelChileanTeam
	 * @Description: 查询该团队下的终端团队id
	 * @param @param
	 *            crmTeamPOs
	 * @param @param
	 *            crmTeamParam
	 * @return void 返回类型
	 * @throws @author
	 *             LuGaogao
	 * @date 2017年6月2日 下午4:53:35
	 */
	public void getLowestLevelChileanTeam(List<Long> crmTeamIds, CrmTeamParam crmTeamParam);

	/**
	 * 
	 * @Title: queryTeamMsg
	 * @Description: 查询团队相关信息
	 * @param @param
	 *            crmteamParam
	 * @param @return
	 * @return List<CrmTeamPO> 返回类型
	 * @throws @author
	 *             LuGaogao
	 * @date 2017年6月5日 下午2:52:04
	 */
	public List<CrmTeamPO> queryTeamMsg(CrmTeamParam crmteamParam);

	/**
	 * 
	 * @Title: getChildTeam
	 * @Description: 获取团队的所有子团队(包括本身)
	 * @param @param
	 *            teamParam
	 * @param @return
	 * @return List<CrmTeamPO> 返回类型
	 * @throws @author
	 *             LuGaogao
	 * @date 2017年6月5日 下午9:19:19
	 */
	public List<CrmTeamPO> getChildTeam(CrmTeamParam teamParam);

	/**
	 * 查询所有团队信息
	 * 
	 * @param param
	 * @return
	 */
	public Page<CrmTeamVO> queryPages(CrmTeamParam param);
	
	/**
	 * 查询成员信息
	 * @param param
	 * @return
	 */
	public Page<CrmBusinessVO> queryBusiness(CrmBusinessParam param);
	
	/**
	 * 
	 * @Title: getChildTeamIds 
	 * @Description: 获取子孙团队Id(包括自身Id) 
	 * @param @param crmTeamParam
	 * @param @return  
	 * @return List<Long>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年6月16日 下午3:14:14
	 */
	public List<Long> getChildTeamIds(CrmTeamParam crmTeamParam);

	/**
	 * @author 黄霄仪
	 * @date 2017年6月19日 上午11:18:43
	 */
	List<CrmTeamPO> commonSearch(CrmTeamParam crmTeamParam);
	
	/**
	 * 查询团队名称
	 * @return
	 */
	public List<CrmTeamPO> getTeamByIds();
	/**
	 * 查询一个团队成员 有几个保护客户
	 * @return
	 */
	public Page<CrmBusinessVO> queryBusinessById(CrmBusinessParam param);
	
	/**
	 * 根据用户类型查询团队信息
	 * @return
	 */
	public List<CrmTeamPO> getType(Integer type);
	
	/**
	 * 
	 * @Title: queryParentsTeam 
	 * @Description: 查询父级团队(包括本身) 
	 * @param @param teamParam
	 * @param @return  
	 * @return List<CrmTeamPO>    返回类型 
	 * @throws 
	 * @author LuGaogao
	 * @date 2017年7月19日 下午7:58:23
	 */
	public List<CrmTeamPO> queryParentsTeam(CrmTeamParam teamParam);
	
	/** 
	 * 新增团队
	 * @author create 郁圆圆
	 * @date 2017年7月18日 上午10:27:19 
	*/
	public RespModel addTeam(CrmTeamParam param);

	/** 
	 * 检索出省
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午1:19:30 
	*/
	public List<CrmTeamPO> selectByAreaCodeLike(String areaCode);
	
	/** 
	 * 筛选团队
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月18日 下午3:05:24 
	*/
	public List<CrmTeamPO> selectByAreaCode(CrmTeamParam param);
	
	/** 
	 * 团队修改
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月19日 上午9:50:35 
	*/
	public RespModel updateTeam(CrmTeamParam param);

	/**
	 * @author 黄霄仪
	 * @date 2017年7月27日 下午5:08:31
	 */
	CrmTeamVO findByTypeAndLevel(Integer type, Integer level);

	/**
	 * @author 黄霄仪
	 * @date 2017年7月27日 下午9:10:25
	 * @deprecated 参考findByAreaCodeAndType，因为代理商也可以创建团队，所以根据负责范围，不可能有一个
	 */
	@Deprecated
	CrmTeamVO findByAreaCode(Long areaCode);
	
	/** 
	 * 查询是否存在子团队
	 * @param  传入参数
	 * @author create 郁圆圆
	 * @date 2017年7月28日 上午11:30:17 
	*/
	public List<CrmTeamPO> findByParentId(Long parentId);

	/**
	 * @author 黄霄仪
	 * @date 2017年7月28日 下午5:22:19
	 */
	List<CrmTeamVO> findByIds(List<Long> ids);

	/**
	 * @author 黄霄仪
	 * @date 2017年8月1日 下午4:26:33
	 */
	CrmTeamVO findByAreaCodeAndTypeAndAgentId(Long areaCode, Integer type,Long agentId);

	/**
	 * @author 黄霄仪
	 * @date 2017年8月3日 下午2:53:55
	 */
	List<CrmTeamVO> findByTypeAndAgentId(Integer type, Long agentId);
	
}
