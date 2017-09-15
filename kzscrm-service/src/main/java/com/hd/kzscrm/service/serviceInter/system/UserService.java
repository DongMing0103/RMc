/**
 * 
 */
package com.hd.kzscrm.service.serviceInter.system;

import java.util.List;

import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.dao.entity.UserPO;
import com.hd.kzscrm.dao.entity.user.CrmUserPO;
import com.hd.kzscrm.service.param.user.UserParam;
import com.hd.kzscrm.service.vo.UserDTO;
import com.hd.wolverine.service.BaseService;

/**
 * 用户表
 * 
 * @author 黄霄仪
 * @date 2017年3月6日 下午2:38:05
 * 
 */
public interface UserService extends BaseService {
	/**
	 * 根据条件查询用户信息
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月6日 下午2:39:07
	 */
	public UserPO getUserPO(UserParam param);

	/**
	 * 根据userId查询用户名
	 *
	 * @Description : TODO
	 * @author : lcl
	 * @time : 2017年3月7日 下午2:04:55
	 */
	public UserPO findNameById(Long userId);

	/**
	 * 批量插入数据
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月7日 上午11:54:36
	 */
	int batchInsert(List<UserParam> userParams);

	/**
	 * 获取个人用户信息
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月7日 下午2:58:22
	 */
	public UserDTO getUserDTOPersonInfo(UserParam param);

	/**
	 * 登录服务
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月13日 上午11:52:32
	 */
	UserDTO loginService(UserParam userParam);

	/**
	 * 更新用户
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月14日 下午7:36:18
	 */
	UserDTO updateUserInfo(UserParam userParam);

	/**
	 * 根据条件查询全部
	 * 
	 * @param param
	 * @return
	 * @author jyt 2017年3月23日 下午2:47:06
	 */
	public List<UserPO> findAllByParam(UserParam param);

	/**
	 * 根据userType查id
	 * 
	 * @return List<UserPO>
	 * @author create 郁圆圆
	 * @date create 2017年3月25日 下午4:02:24
	 */
	public List<UserPO> findUserType(UserParam param);

	/**
	 * 根据用户IDS获取用户数据
	 * 
	 * @author 黄霄仪
	 * @date 2017年3月25日 下午2:56:25
	 */
	List<UserPO> queryByIds(List<Long> userIds);

	/**
	 * 根据用户名查
	 * 
	 * @return List<UserPO>
	 * @author create 郁圆圆
	 * @date create 2017年3月28日 下午4:26:03
	 */
	public List<UserPO> findByUserName(UserParam param);

	/**
	 * 根据用户名模糊查询对象
	 *
	 * @Description : TODO
	 * @author : lcl
	 * @time : 2017年4月10日 下午3:14:01
	 */
	public List<UserPO> findLikeUserName(String selectName);

	/**
	 * 根据用户名模糊查询对象
	 *
	 * @Description : TODO
	 * @author : lcl
	 * @time : 2017年4月10日 下午3:14:01
	 */
	public List<UserPO> findLikeMobilephone(String selectName);

	/**
	 * 根据id集合查手机号
	 * 
	 * @return List<UserPO>
	 * @author create 郁圆圆
	 * @date create 2017年4月8日 下午1:31:34
	 */
	public List<UserPO> findByuserIds(UserParam param);

	/**
	 * 登陆首页
	 *
	 * @author LuGaogao
	 * @date 2017年4月20日下午4:05:27
	 * @param model
	 */
	public void toIndex(ModelAndView model, Long canteenId);

	public UserDTO getUserById(Long userId);
	
	UserPO getById(Long id);

}
