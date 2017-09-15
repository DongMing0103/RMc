package com.hd.kzscrm.manage.listener;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.hd.kzscrm.common.param.message.MessageParam;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.service.serviceInter.system.UserService;
import com.hd.kzscrm.service.serviceInter.userBehavior.MessageService;
import com.hd.wolverine.service.BaseService;

/**
 * 监听器
 */

public class KzscrmMessageListener implements InitializingBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(KzscrmMessageListener.class);
	@Resource
	private AsyncEventBus asyncEventBus;
	@Resource
	BaseService baseService;

	@Resource
	UserService userService;	

	@Autowired
	private MessageService messageService;

	@Subscribe
	public void messageLister(Map<String, Long> paramMap) {
		Long id = paramMap.get("id");
		Long loginId = paramMap.get("loginId");

	}

	/**
	 * 账号停用后监听事件
	 * 
	 * @param param
	 */
	@Subscribe
	public void accountStop(Object param) {
	 
	}

	/**
	 * 公司停用后监听事件
	 * 
	 * @param companyId
	 */
	@Subscribe
	public void shopStop(Long companyId) {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		asyncEventBus.register(this);

	}

	@Subscribe
	public boolean one(MessageParam param) {
		LOGGER.info("[sms][start]发送短信:" + CommUtil.offerJson(param));
		messageService.sendMessage(param);
		LOGGER.info("[sms][end]发送短信完成:" + CommUtil.offerJson(param));
		return true;
	}
}
