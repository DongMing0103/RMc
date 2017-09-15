package com.hd.kzscrm.service.serviceimpl.userBehavior;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.eventbus.AsyncEventBus;
import com.hd.kzscrm.common.enums.MessageInsideParam;
import com.hd.kzscrm.common.enums.MessageInsideTypeEnum;
import com.hd.kzscrm.common.enums.MessageTemplateEnum;
import com.hd.kzscrm.common.enums.MessageTypeEnum;
import com.hd.kzscrm.common.enums.PlatFormEnum;
import com.hd.kzscrm.common.param.message.MessageParam;
import com.hd.kzscrm.common.param.message.MessageSmsParam;
import com.hd.kzscrm.common.util.StringUtil;
import com.hd.kzscrm.dao.entity.CrmAccountPO;
import com.hd.kzscrm.dao.entity.userRpt.BaseUserMessagePO;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.serviceInter.userBehavior.MessageService;
import com.hd.kzscrm.service.vo.UserDTO;
import com.hd.wolverine.common.i18n.TranslatorHelper;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseServiceImpl;
import com.hd.wolverine.service.sms.SmsServiceRemote;
import com.hd.wolverine.soa.SOAService;
import com.hd.wolverine.util.ParamMap;
 
/**
 * Created by liuming DATE: 2016/10/27.
 */
@Service
public class MessageServiceImpl extends BaseServiceImpl implements MessageService {

	/**
	 * 日志
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Resource(name = "soaService")
	private SOAService soaService;

	@Resource
	private AsyncEventBus asyncEventBus;

	/**
	 * 分页查询我的消息
	 * 
	 * @param param
	 * @return
	 */
	public Page<BaseUserMessagePO> pageQueryMsg(MessageParam param) {
		BaseUserMessagePO po = new BaseUserMessagePO();
		po.setUserIdRecv(param.getUserIdRecv());
		po.setUserIdSend(param.getUserIdSend());
		po.setCompanyId(param.getCompanyId());
		return findPageByParams(BaseUserMessagePO.class, new Page<BaseUserMessagePO>(param.getOffset(), param.getLimit()),
				"BaseUserMessageMapper.queryMsg", new ParamMap(po));
	}

	public Page<BaseUserMessagePO> pageQueryMsg3(MessageParam param) {
		BaseUserMessagePO po = new BaseUserMessagePO();
		po.setUserIdRecv(param.getUserIdRecv());
		po.setUserIdSend(param.getUserIdSend());
		po.setCompanyId(param.getCompanyId());
		ParamMap paramMap = new ParamMap(param);
		paramMap.put("ids", new Object[] { paramMap.get("id") });
		execute("BaseUserMessageMapper.update_id", paramMap);
		return findPageByParams(BaseUserMessagePO.class, new Page<BaseUserMessagePO>(param.getOffset(), param.getLimit()),
				"BaseUserMessageMapper.queryMsg", new ParamMap(po));
	}

	/**
	 * all message 0+1
	 */
	public Page<BaseUserMessagePO> pageQueryMsg1(MessageParam param) {
		BaseUserMessagePO po = new BaseUserMessagePO();
		po.setUserIdRecv(param.getUserIdRecv());
		po.setUserIdSend(param.getUserIdSend());
		po.setCompanyId(param.getCompanyId());
		return findPageByParams(BaseUserMessagePO.class, new Page<BaseUserMessagePO>(param.getOffset(), param.getLimit()),
				"BaseUserMessageMapper.queryMsgIsRead1", new ParamMap(po));
	}

	/**
	 * no read message
	 */
	public Page<BaseUserMessagePO> pageQueryMsg0(MessageParam param) {
		BaseUserMessagePO po = new BaseUserMessagePO();
		po.setUserIdRecv(param.getUserIdRecv());
		po.setUserIdSend(param.getUserIdSend());
		po.setCompanyId(param.getCompanyId());
		return findPageByParams(BaseUserMessagePO.class, new Page<BaseUserMessagePO>(param.getOffset(), param.getLimit()),
				"BaseUserMessageMapper.queryMsgIsRead0", new ParamMap(po));
	}

	/**
	 * 根据id和用户id获取消息
	 * 
	 * @param param
	 */
	@Override
	public BaseUserMessagePO queryByIdAndUserIdRecv(MessageParam param) {
		ParamMap paramMap = new ParamMap(param);
		List<BaseUserMessagePO> bum = listByParams(BaseUserMessagePO.class, "BaseUserMessageMapper.queryByIdAndUserIdRecv", paramMap);
		if (bum != null && bum.size() != 0) {
			return bum.get(0);
		}
		return null;
	}

	/**
	 * 更新消息为已读
	 * 
	 * @param param
	 */
	@Override
	public void updateAllMsg(MessageParam param) {
		BaseUserMessagePO po = new BaseUserMessagePO();
		po.setIsRead(1);
		if (param.getUserIdRecv() != null) {
			po.setUserIdRecv(param.getUserIdRecv());
		}
		ParamMap paramMap = new ParamMap(po);
		execute("BaseUserMessageMapper.updateAllMsgRead", paramMap);
	}

	/**
	 * 新增消息
	 * 
	 * @param param
	 */
	/*
	 * @Override public void addMessage(MessageParam param) { BaseUserMessagePO
	 * po = new BaseUserMessagePO(); BeanUtils.copyProperties(param, po); if
	 * (null == po.getCreateTime()) { po.setCreateTime(new Date()); } save(po);
	 * }
	 */
	@Override
	public void sendMessage(MessageParam param) {
		MessageTemplateEnum messageTemplateEnum = param.getMessageTemplateEnum();
		try {
			LOGGER.debug("消息模板:" + messageTemplateEnum.toString());
			String[] typeArray = messageTemplateEnum.getCode().split("_");
			LOGGER.debug("消息发送类型:" + typeArray[0]);
			if (MessageTypeEnum.SMS.getCode().equals(typeArray[0])) {
				boolean flag = sendSMS(param);
				if (!flag) {
					LOGGER.debug("消息发送失败！");
				}
			} else if (MessageTypeEnum.INSIDE_MESSAGE.getCode().equals(typeArray[0])) {
				sendInside(param);
			} else {
				LOGGER.error("未找到对应的消息模板!模板类型编号:" + typeArray[0]);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("发送消息失败:" + param.toString(), e);
		}
	}

	@Override
	public void batchSendMessage(List<MessageParam> paramList) {
		if (CollectionUtils.isEmpty(paramList)) {
			return;
		}
		for (MessageParam param : paramList) {
			sendMessage(param);
		}
	}

	/**
	 * 参数替换
	 *
	 * @param content
	 *            模板内容
	 * @param param
	 *            实际值map
	 * @return
	 * @history
	 */
	@SuppressWarnings("unused")
	private String replace(String content, Map<String, String> param) {
		if (CollectionUtils.isEmpty(param)) {
			return content;
		}
		for (String key : param.keySet()) {
			String value = param.get(key);
			content = content.replace("{" + key + "}", value);
		}
		return content;
	}

	/**
	 * 短信发送
	 *
	 * @param messageParam
	 *            参数
	 * @return
	 * @history
	 */
	private boolean sendSMS(MessageParam messageParam) {
		MessageSmsParam messageSmsParam = (MessageSmsParam) messageParam;
		String contentReplace = String.format(messageParam.getMessageTemplateEnum().getContent(), messageSmsParam.getParam());
		LOGGER.debug("短信内容：" + contentReplace);
		// 当手机号码为空时，判断userid是否为空，根据userid补全手机号码
		if (StringUtil.isEmpty(messageSmsParam.getMobile())) {
			if (null != messageSmsParam.getUserId()) {
				UserService userService = soaService.getRemoteService(UserService.class, "1");
				  //ResponseDTO<UserDTO> 
				  UserDTO result = userService.getUserById(messageSmsParam
						.getUserId());
				//if (result.isSuccess() && null != result.getResult()) {
					messageSmsParam.setMobile(result.getResult().getMobileNo());
				//} else {
					messageSmsParam.setMobile("");
				//}
			} else {
				LOGGER.error("发送短信失败！参数错误,手机号和用户id不能同时为空");
			}
		}
		LOGGER.debug("短信发送参数：FunctionNo=" + messageSmsParam.getFunctionNo() + ",mobile=" + messageSmsParam.getMobile() + ",短信内容="
				+ contentReplace + ",网站id=" + PlatFormEnum.AI.getValue() + ",用户ip地址：" + messageSmsParam.getUserIP());
		SmsServiceRemote smsService = soaService.getRemoteService(SmsServiceRemote.class);
		boolean flag = false;
		if (smsService != null) {
			LOGGER.debug("调用认证中心短信服务接口，发送短信......");
			flag = smsService.sendSMS(messageSmsParam.getFunctionNo(), messageSmsParam.getMobile(), contentReplace,
					PlatFormEnum.AI.getValue(), messageSmsParam.getUserIP());
			LOGGER.debug("调用认证中心短信服务接口，发送短信结果:" + flag);
		} else {
			LOGGER.debug("SmsServiceRemote为空！");
		}
		return flag;
	}

	/**
	 * 保存站内消息
	 *
	 * @param messageParam
	 *            参数
	 * @return
	 * @history
	 */
	private boolean sendInside(MessageParam messageParam) {

		return true;
	}

	/**
	 * 查找公司主帐号往待发送的消息列表中添加数据
	 * 
	 * @param companyId
	 * @param messageParamList
	 */
	public void pushMessageParamList(Long companyId, List<MessageParam> messageParamList, MessageTemplateEnum messageTemplateEnum,
			String... param) {

	}

	public void updateMessages(MessageParam param) {
		try {
			String[] params = param.getParam();
			BaseUserMessagePO po = new BaseUserMessagePO();
			po.setIsRead(1);
			// 设置消息阅读时间
			po.setRecvTime(new Date());
			ParamMap paramMap = new ParamMap(po);
			if (ArrayUtils.isEmpty(params)) {
				return;
			}
			List<Long> ids = new ArrayList<>(params.length);
			for (int i = 0; i < params.length; i++) {
				ids.add(Long.valueOf(params[i]));
			}
			paramMap.put("ids", ids);
			// execute("BaseUserMessageMapper.updateMsgRead", paramMap);
		} catch (Exception e) {
			LOGGER.error(TranslatorHelper.getText("更新消息状态出错"));
		}
	}

	@Override
	public List<BaseUserMessagePO> queryUnReadMsg(MessageParam param) {
		BaseUserMessagePO po = new BaseUserMessagePO();
		po.setUserIdRecv(param.getUserIdRecv());
		if (param.getIsRead() != null) {
			po.setIsRead(param.getIsRead().intValue());
		}
		return listByParams(BaseUserMessagePO.class, "BaseUserMessageMapper.queryMsg", new ParamMap(po));
	}



	/**
	 * 业务系统调用发送手机短信
	 *
	 * @param userId
	 *            接收短信人id
	 * @param messageTemplateEnum
	 *            信息模板枚举
	 * @param param
	 *            模板参数
	 */
	@Override
	public void sendBizSmsMessages(Long userId, MessageTemplateEnum messageTemplateEnum, String ip, String... param) {
		// 根据id查询对应的用户手机号
		UserService userService = soaService.getRemoteService(UserService.class, "1");
		UserDTO userDTO = userService.getUserById(userId).getResult();

		if (userDTO == null) {
			return;
		}

		// 发送短信
		MessageSmsParam messageSmsParam = new MessageSmsParam(userDTO.getMobileNo(), ip, messageTemplateEnum, param);
		// 异步提交
		asyncEventBus.post(messageSmsParam);
	}

	/**
	 * 业务系统调用 发送站内信
	 *
	 * @param acceptMsgUserId
	 *            信息接收人id
	 * @param messageTemplateEnum
	 *            信息模板枚举
	 * @param companyId
	 *            公司id
	 * @param param
	 *            模板参数
	 */
	@Override
	public void sendBizInsiteMessages(Long acceptMsgUserId, MessageTemplateEnum messageTemplateEnum, Long companyId, String... param) {
		// 发送站内信
		MessageInsideParam messageInsideParam = new MessageInsideParam(acceptMsgUserId, null, MessageInsideTypeEnum.OPERATION.getCode(),
				messageTemplateEnum, companyId, false, param);

		// 异步提交
		asyncEventBus.post(messageInsideParam);
	}

	/**
	 * 给后台工作人员发送短信和站内信
	 * 
	 * @param code
	 *            对应的枚举code[比如需要1_003对应的枚举,则传入003即可]
	 * @param param
	 *            模板参数
	 */
	@Override
	public void sendJumoreManagerMessages(String code, String... param) {
		// 003 是客户 拍档审核 005 027 是绑定申请和解绑申请 007 是融资申请
		// 查询账户正常且未删除的账户
		ParamMap params = new ParamMap();
		params.put("status", 1);
		//////params.put("deleteFlag", QzsWebConstants.DELETE_FLAG_NO);
		if (code == "003") {

		} else if (code.equals("005") || code .equals( "027")) {
			params.put("menuid", "(5)");
		} else if (code .equals( "007")) {
			params.put("menuid", "(9)");
		} else {
			params.put("menuid", "()");
		}

		// AccountPO query = new AccountPO();
		// query.setStatus(1);
		// query.setDeleteFlag(JdtWebConstants.DELETE_FLAG_NO);

		// List<AccountPO> accList = commonDao.listByExample(query);

		List<CrmAccountPO> accList = commonDao.listByParams(CrmAccountPO.class, "PermissionMapper.getUserByRole", params);

		List<MessageParam> messageParamList = new ArrayList<>();
		MessageSmsParam messageSmsParam = null;
		MessageInsideParam messageInsideParam = null;

		for (CrmAccountPO accountpo : accList) {

			// 是否接受短信，0接受，1拒绝
			if (StringUtils.equals("0", accountpo.getIsReceiveMsg())) {
				// 发送短信
				messageSmsParam = new MessageSmsParam(accountpo.getMobilephone(), null, MessageTemplateEnum.getEnumByCode("1_" + code), param);

				messageParamList.add(messageSmsParam);

			}

			// 发送站内信
			messageInsideParam = new MessageInsideParam(accountpo.getId(), null, MessageInsideTypeEnum.OPERATION.getCode(),
					MessageTemplateEnum.getEnumByCode("2_" + code), null, true, param);

			messageParamList.add(messageInsideParam);

		}

		// 异步提交
		asyncEventBus.post(messageParamList);
	}

	@Override
	public int getUnreadMsgNum(Long userId) {
		MessageParam param = new MessageParam();
		param.setUserIdRecv(userId);
		param.setIsRead(0L);
		int num = 0;
		List<BaseUserMessagePO> list = this.queryUnReadMsg(param);
		if (!CollectionUtils.isEmpty(list))
			num = list.size();
		return num;
	}

	@Override
	public void update2ReadFromIdsMessages(List<Long> ids) {
		ParamMap paramMap = new ParamMap();
		paramMap.put("ids", ids);
		execute("BaseUserMessageMapper.update_id", paramMap);

	}

	@Override
	public void readMes(List<BaseUserMessagePO> list) {
		try {
			for (BaseUserMessagePO baseUserMessagePO : list) {
				baseUserMessagePO.setIsRead(1);
				update(baseUserMessagePO);
			}
		} catch (Exception e) {
			LOGGER.error("center.Qzs.com query message list error:{}", e);
		}
	}
}
