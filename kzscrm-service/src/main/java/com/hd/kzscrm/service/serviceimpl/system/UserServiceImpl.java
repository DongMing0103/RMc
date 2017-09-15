package com.hd.kzscrm.service.serviceimpl.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.enums.DatabaseTableNameEnum;
import com.hd.kzscrm.common.enums.SiteConstEnum;
import com.hd.kzscrm.common.enums.SystemStatusEnum.EnterpriseEmployeesLinkAuthenticationStatus;
import com.hd.kzscrm.common.util.BeanConvertor;
import com.hd.kzscrm.common.util.BeanUtils;
import com.hd.kzscrm.common.util.Consts;
import com.hd.kzscrm.common.util.ServiceUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.UserPO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.param.user.UserParam;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.vo.UserDTO;
import com.hd.wolverine.cache.CacheService;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.util.ParamMap;

/**
 * @author 黄霄仪
 * @date 2017年3月6日 下午2:40:06
 * 
 */
@Service("userServiceImp")
@SuppressWarnings({ "unused", "deprecation" })
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private SqlSessionTemplate sqlSession;
	/**
	 * /** 缓存
	 */
	@Resource
	private CacheService cacheService;

	/**
	 * 根据条件查询用户信息
	 */
	@Override
	public UserPO getUserPO(UserParam param) {
		ParamMap paramMap = new ParamMap(param);
		List<UserPO> userPOs = commonDao.listByParams(UserPO.class, "UserMapper.findAll", paramMap);
		if (BeanUtils.isNotEmptyUniqueList(userPOs)) {
			return userPOs.get(0);
		}
		return null;
	}

	/**
	 * 根据 手机号获取数据
	 * 
	 * @param mobilephone
	 * @return
	 */

	public List<UserPO> getUserPOByMobilephone(String mobilephone) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("mobilephone", mobilephone);
		List<UserPO> userPOs = commonDao.listByParams(UserPO.class, "UserMapper.findAll", paramMap);
		return userPOs;
	}

	/**
	 * 根据userId查询用户名
	 *
	 * @Description : TODO
	 * @author : lcl
	 * @time : 2017年3月7日 下午2:04:55
	 */
	@Override
	public UserPO findNameById(Long userId) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("userId", userId);
		List<UserPO> userPOs = commonDao.listByParams(UserPO.class, "UserMapper.findByUserId", paramMap);
		if (BeanUtils.isNotEmptyUniqueList(userPOs)) {

			return userPOs.get(0);
		}
		return null;
	}

	/**
	 * 批量添加
	 */
	@Override
	public int batchInsert(List<UserParam> userParams) {
		ParamMap paramMap = new ParamMap(userParams);
		paramMap.put("users", userParams);
		LOGGER.info("#####UserServiceImpl###batchInsert##批量添加paramMap:" + paramMap.toString());
		return commonDao.execute("UserMapper.batchInsert", paramMap);
	}

	/**
	 * 登录服务
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月13日 上午11:52:15
	 */
	@Override
	public UserDTO loginService(UserParam userParam) {
		// 获取USER表的ID
		Long userId = null;
		String mobilephone = userParam.getMobilephone();// 用户手机
		String smsCode = userParam.getSmsCode();// 短信验证码，调用信息接口进行验证
		// 如果用户存在于表中，就直接登录，否则写入数据库
		List<UserPO> userPOs = this.getUserPOByMobilephone(mobilephone);
		Integer authenticationStatus = EnterpriseEmployeesLinkAuthenticationStatus.NO_AUTHENTICATION.getCode();// 默认未认证
		// 如果为空，就注册
		if (BeanUtils.isEmpty(userPOs)) {
			userId = ServiceUtil.genNextIDValue(DatabaseTableNameEnum.user);
			authenticationStatus = EnterpriseEmployeesLinkAuthenticationStatus.NO_COMPLETION.getCode();// 未填写资料
			Date date = new Date();// 当前日期
			userParam.setId(userId);

			List<UserParam> userParams = new LinkedList<>();
			userParams.add(userParam);

			// 初始化用户余额表

			this.batchInsert(userParams);// 保存用户信息
			UserDTO userDTO = BeanConvertor.copyInclude(userParam, UserDTO.class, "id", "consumeCode", "regiserTime", "mobilephone",
					"headImg", "userType", "delFlag", "userStatus");
			userDTO.setAuthenticationStatus(authenticationStatus);
			return userDTO;
		} else {
			UserDTO userDTO = new UserDTO();
			if (BeanUtils.isNotEmptyUniqueList(userPOs)) {
				userDTO = BeanConvertor.copyInclude(userPOs.get(0), UserDTO.class, "id", "consumeCode", "regiserTime", "mobilephone",
						"headImg", "userType", "delFlag", "userStatus");

				userId = userDTO.getId();

			}
			return userDTO;
		}

	}

	/**
	 * 根据条件查询全部
	 * 
	 * @param param
	 * @return
	 * @author jyt 2017年3月23日 下午2:47:06
	 */
	public List<UserPO> findAllByParam(UserParam param) {
		ParamMap paramMap = new ParamMap(param);
		return commonDao.listByParams(UserPO.class, "UserMapper.findAll", paramMap);
	}

	/**
	 * 提供公共的调用类
	 * 
	 * @author 苏常松
	 * @date 2017年3月18日 下午3:52:23
	 */
	public List<Map> getData(List<String> list, List<Map> mapList) {
		List<Map> maps = new ArrayList<Map>();
		for (String str : list) {
			Map m = new HashMap();
			m.put("time", str);
			m.put("money", 0);
			m.put("num", 0);
			if (CollectionUtils.isNotEmpty(mapList)) {
				for (Map map : mapList) {
					if (map.get("time").toString().trim().equals(str.trim().toString())) {
						if (map.get("money") != null) {
							m.put("money", map.get("money"));
						}
						if (map.get("num") != null) {
							m.put("num", map.get("num"));
						}
					}
					;
				}
			}
			maps.add(m);
		}
		return maps;
	}

	/**
	 * 根据userType查id
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年3月25日 下午4:02:53
	 */
	@Override
	public List<UserPO> findUserType(UserParam param) {
		ParamMap paramMap = new ParamMap(param);
		return commonDao.listByParams(UserPO.class, "UserMapper.findUserType", paramMap);
	}

	/**
	 * 根据用户IDS集合查询
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月25日 下午2:56:12
	 */
	@Override
	public List<UserPO> queryByIds(List<Long> userIds) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("ids", userIds);
		return commonDao.listByParams(UserPO.class, "UserMapper.findAll", paramMap);
	}

	/**
	 * 根据用户名查
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年3月28日 下午4:27:12
	 */
	@Override
	public List<UserPO> findByUserName(UserParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(UserPO.class, "UserMapper.findByUserName", paramMap);
	}

	/**
	 * 根据用户名模糊查询对象
	 *
	 * @Description : TODO
	 * @author : lcl
	 * @time : 2017年4月10日 下午3:14:01
	 */
	@Override
	public List<UserPO> findLikeUserName(String selectName) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("selectName", selectName);
		List<UserPO> userPOs = listByParams(UserPO.class, "UserMapper.findLikeUserName", paramMap);
		if (BeanUtils.isNotEmpty(userPOs)) {
			return userPOs;
		}
		return null;
	}

	/**
	 * 根据用h用户账号模糊查询对象
	 *
	 * @Description : TODO
	 * @author : lcl
	 * @time : 2017年4月10日 下午3:14:01
	 */
	@Override
	public List<UserPO> findLikeMobilephone(String selectName) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("selectName", selectName);
		List<UserPO> userPOs = listByParams(UserPO.class, "UserMapper.findLikeMobilephone", paramMap);
		if (BeanUtils.isNotEmpty(userPOs)) {
			return userPOs;
		}
		return null;
	}

	/**
	 * 根据用户集合查手机号
	 * 
	 * @author create 郁圆圆
	 * @date create 2017年4月8日 下午1:33:10
	 */
	@Override
	public List<UserPO> findByuserIds(UserParam param) {
		ParamMap paramMap = new ParamMap(param);
		return listByParams(UserPO.class, "UserMapper.findByuserIds", paramMap);
	}

	/**
	 * 登陆首页
	 */
	@Override
	public void toIndex(ModelAndView model, Long canteenId) {
		model.addObject("siteTitle", SiteConstEnum.SITE_TITLE.getName());

		// 用户信息放入session,"session_user"应该定义成常量
		CrmAccountPO accountPO = (CrmAccountPO) SecurityUtils.getSubject().getSession().getAttribute(Consts.SESSION_USER_KEY);
		if (accountPO != null) {
			model.addObject("sessionUser", accountPO);
		}

	}

	@Override
	public UserDTO getUserDTOPersonInfo(UserParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO updateUserInfo(UserParam userParam) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hd.kzscrm.service.serviceInter.system.UserService#getUserById(java.lang.Long)
	 */
	@Override
	public UserDTO getUserById(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserPO getById(Long id) {
		return commonDao.get(UserPO.class, id);
	}

}
