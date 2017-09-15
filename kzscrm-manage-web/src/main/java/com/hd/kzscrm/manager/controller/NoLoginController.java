package com.hd.kzscrm.manager.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.StringUtil;
import com.hd.kzscrm.common.util.VerifyCodeUtils;
import com.hd.kzscrm.dao.entity.UserPO;
import com.hd.kzscrm.service.constants.QzsWebConstants;
import com.hd.kzscrm.service.param.user.UserParam;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.wolverine.cache.CacheService;
//import com.hd.wolverine.controller.base.BaseController;
import com.hd.wolverine.util.MD5;

/**
 * @ClassName:NoLoginController
 * @Description:不需要登录相关
 * @author jyt 2017年3月17日 下午7:06:02
 *
 */
@Controller
@RequestMapping("/nl")
public class NoLoginController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoLoginController.class);
	/**
	 * 用户
	 */
	@Autowired
	private UserService userService;

	/**
	 * @author jyt 2017年3月16日 上午11:16:08
	 */
	@Resource
	private CacheService cacheService;

	@Value("${sms_verification}")
	private String smsVerification;// 是否验证短信验证码

	/**
	 * @Description:跳转至修改密码页面
	 * @return ModelAndView 返回类型
	 * @throws Exception
	 * @history
	 */
	@RequestMapping(value = "/changPassword")
	public ModelAndView changPassword() {
		ModelAndView mv = new ModelAndView("/user/reset_password");
		return mv;
	}

	/**
	 * @Description: 生成短信验证码
	 * @param userParam
	 *            用户参数
	 * @param request
	 * @return RespModel 返回类型
	 * @author jyt 2017年3月10日 下午8:07:53
	 */
	@RequestMapping("/verificationCode")
	@ResponseBody
	public RespModel verificationCode(UserParam userParam, HttpServletRequest request) {
		LOGGER.info("#####NoLoginController###verificationCode##userParam:" + userParam.toString());

		RespModel res = new RespModel();
		String mobilephone = userParam.getMobilephone();// 手机号码
		UserPO userPO = new UserPO();
		userPO.setMobilephone(mobilephone);
		userPO.setUserType(2);// 商家
		userPO.setDelFlag(1);
		userPO = (UserPO) userService.getByExample(userPO);
		if (userPO == null) {
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("该用户不存在");
			return res;
		}
		// 生成验证码
		String veriCode = StringUtil.getRandomNum2();
		// System.out.println("verificationCode 短信验证码 : " + veriCode);
		LOGGER.info("#####NoLoginController###verificationCode##短信验证码 :" + veriCode);
		userParam.setSmsCode(veriCode);

		// 生成验证码放入缓存
		String objType = "canteen:" + QzsWebConstants.SYS_KEY_MAIN + ":" + QzsWebConstants.OBJTYPE_USER_SENDVERICODEMSG;
		cacheService.set(objType, mobilephone, veriCode, QzsWebConstants.MOBILE_CHK_CODE_EXPIRE_TIME);
		// 发送短信
		try {
			// iSmsService.sendsms(mobilephone,
			// "【浙江惠点】验证码是"+veriCode+"，10分钟有效[筷子说]","", 0);
		} catch (Exception e) {
			LOGGER.error("NoLoginController:verificationCode:" + e.getMessage());
			e.printStackTrace();

		}

		// 保存session
		request.getSession().setAttribute("canteen:" + request.getRequestedSessionId(), mobilephone);
		request.getSession().setMaxInactiveInterval(QzsWebConstants.MOBILE_CHK_CODE_EXPIRE_TIME);

		res.setCode(RespModel.RespCode.SUCCESS.getCode());
		res.setDesc("操作成功");
		return res;
	}

	/**
	 * @Description:校验短信验证码
	 * @param userParam
	 *            用户参数
	 * @param request
	 * @return RespModel 返回类型
	 * @author jyt 2017年3月11日 上午9:42:57
	 */
	@RequestMapping("/verifyCode")
	@ResponseBody
	public RespModel verifyCode(UserParam userParam, HttpServletRequest request) {
		LOGGER.info("#####NoLoginController###verifyCode##userParam:" + userParam.toString());

		RespModel res = new RespModel();
		String mobilephone = userParam.getMobilephone();// 手机号码
		String smsCode = userParam.getSmsCode();// 页面输入验证码
		if (StringUtil.isEmpty(mobilephone)) {
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("请输入手机号码！");
			return res;
		}
		/**
		 * 校验验证码
		 */
		if (StringUtil.isEmpty(smsCode)) {
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("请输入验证码！");
			return res;
		}

		String mphone = (String) request.getSession().getAttribute("canteen:" + request.getRequestedSessionId());
		if (StringUtil.isEmpty(mphone)) {// 页面失效
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("当前页面已失效，请刷新页面重新操作");
			return res;
		}
		if (!mobilephone.equals(mphone)) {// 验证是否相同
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("请输入相同的手机号");
			return res;
		}

		// 获取发送的验证码
		String objType = "canteen:" + QzsWebConstants.SYS_KEY_MAIN + ":" + QzsWebConstants.OBJTYPE_USER_SENDVERICODEMSG;
		String sendSmsCode = cacheService.getString(objType, mobilephone);
		if (StringUtil.isNotEmpty(smsVerification) && smsVerification.equals("false") && smsCode.equals("123456")) {
			// 跳过验证
		} else {
			if (StringUtils.isEmpty(sendSmsCode)) {
				res.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
				res.setDesc("短信验证码已失效");
				return res;
			} else if (!sendSmsCode.equals(smsCode)) {
				res.setCode(RespModel.RespCode.PARAM_EXCEPTION.getCode());
				res.setDesc("短信验证码输入错误");
				return res;
			}
		}

		res.setCode(RespModel.RespCode.SUCCESS.getCode());
		res.setDesc("操作成功");
		return res;
	}

	/**
	 * @Description:修改密码
	 * @param userParam
	 *            用户参数
	 * @param request
	 * @return RespModel 返回类型
	 * @author jyt 2017年3月11日 上午9:42:57
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public RespModel updatePassword(UserParam userParam, HttpServletRequest request) {
		LOGGER.info("#####NoLoginController###updatePassword##userParam:" + userParam.toString());
		RespModel res = new RespModel();
		String password = userParam.getPassword();

		if (StringUtil.isEmpty(password)) {
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("请输入密码！");
			return res;
		}
		String mobilephone = (String) request.getSession().getAttribute("canteen:" + request.getRequestedSessionId());
		if (StringUtil.isEmpty(mobilephone)) {
			res.setCode(RespModel.RespCode.NOT_DATA.getCode());
			res.setDesc("当前页面已失效，请刷新页面重新操作");
			return res;
		}
		UserPO userPO = new UserPO();
		userPO.setMobilephone(mobilephone);
		userPO.setDelFlag(1);
		userPO = (UserPO) userService.getByExample(userPO);
		userPO.setPassword(MD5.md5(password));
		try {
			LOGGER.info("#####NoLoginController###updatePassword##更新userPO:" + userPO.toString());
			userService.update(userPO);

			res.setCode(RespModel.RespCode.SUCCESS.getCode());
			res.setDesc("操作成功");
			request.getSession().setMaxInactiveInterval(-1);
		} catch (Exception e) {
			LOGGER.error("NoLoginController:updatePassword:" + e.getMessage());
			e.printStackTrace();
			res.setCode(RespModel.RespCode.SYS_EXCEPTION.getCode());
			res.setDesc("操作失败");
		}

		return res;
	}

	/**
	 * @Description:获取图片验证码
	 * @param request
	 * @param response
	 * @return ModelAndView 返回类型
	 * @author jyt 2017年3月16日 上午11:13:32
	 */
	@RequestMapping(value = "/getVerifyCode")
	public ModelAndView getVerifyCode(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		try {
			// 生成随机字串
			String verifyCode = VerifyCodeUtils.generateVerifyCode(4);

			// 生成验证码放入缓存
			String objType = "canteen:" + QzsWebConstants.SYS_KEY_MAIN + ":"
					+ QzsWebConstants.OBJTYPE_USER_GETVERIFYCODE;
			cacheService.set(objType, request.getRequestedSessionId(), verifyCode,
					QzsWebConstants.IMAGE_CHK_CODE_EXPIRE_TIME);
			VerifyCodeUtils.outputImage(QzsWebConstants.VERIFY_WIDTH, QzsWebConstants.VERIFY_HEIGHT,
					response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
			try {
				// 生成随机字串
				String verifyCode = VerifyCodeUtils.generateVerifyCode(4).toUpperCase();
				String objType = "canteen:" + QzsWebConstants.SYS_KEY_MAIN + ":"
						+ QzsWebConstants.OBJTYPE_USER_GETVERIFYCODE;
				cacheService.set(objType, request.getRequestedSessionId(), verifyCode,
						QzsWebConstants.IMAGE_CHK_CODE_EXPIRE_TIME);
				// 生成验证码放入缓存
				VerifyCodeUtils.outputImage(QzsWebConstants.VERIFY_WIDTH, QzsWebConstants.VERIFY_HEIGHT,
						response.getOutputStream(), verifyCode);
			} catch (IOException e1) {
				LOGGER.error("NoLoginController:getVerifyCode:" + e1.getMessage());
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @Description:校验图片验证码
	 * @param userParam
	 *            用户参数
	 * @param request
	 * @return RespModel 返回类型
	 * @author jyt 2017年3月16日 上午11:34:19
	 */
	@RequestMapping("/verifyCodeImg")
	@ResponseBody
	public RespModel verifyCodeImg(UserParam userParam, HttpServletRequest request) {
		LOGGER.info("#####NoLoginController###verifyCodeImg##userParam:" + userParam.toString());
		RespModel res = new RespModel();
		String imgCode = userParam.getImgCode();// 页面输入验证码

		if (this.debugModel) {
			//验证输入任何字符都可以
		} else {
			// 校验图片验证码
			String objType = "canteen:" + QzsWebConstants.SYS_KEY_MAIN + ":"
					+ QzsWebConstants.OBJTYPE_USER_GETVERIFYCODE;

			String imageCode = cacheService.getString(objType, request.getRequestedSessionId());
			if (StringUtils.isEmpty(imgCode)) {
				return new RespModel(RespModel.RespCode.PARAM_EXCEPTION.getCode(), "请输入图片验证码");
			}
			if (StringUtils.isEmpty(imageCode)) {
				return new RespModel(RespModel.RespCode.PARAM_EXCEPTION.getCode(), "图片验证码已失效");
			}
			if (!imageCode.toLowerCase().equals(imgCode.toLowerCase())) {
				return new RespModel(RespModel.RespCode.PARAM_EXCEPTION.getCode(), "图片验证码输入错误");
			}
		}
		res.setCode(RespModel.RespCode.SUCCESS.getCode());
		res.setDesc("操作成功");
		return res;
	}

}
