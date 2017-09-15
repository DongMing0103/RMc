package com.hd.kzscrm.service.serviceInter.userBehavior;

import java.util.List;

import com.hd.kzscrm.common.enums.MessageTemplateEnum;
import com.hd.kzscrm.common.param.message.MessageParam;
import com.hd.kzscrm.dao.entity.userRpt.BaseUserMessagePO;
import com.hd.wolverine.plugin.Page;
import com.hd.wolverine.service.BaseService;

/**
 * Created by jiangjianwei
 * DATE: 2016/6/30.
 */
 
public interface MessageService extends BaseService{
	public Page<BaseUserMessagePO> pageQueryMsg(MessageParam param);
	public Page<BaseUserMessagePO> pageQueryMsg1(MessageParam param);
	public Page<BaseUserMessagePO> pageQueryMsg0(MessageParam param);
	public Page<BaseUserMessagePO> pageQueryMsg3(MessageParam param);
	public BaseUserMessagePO queryByIdAndUserIdRecv(MessageParam param);
    public void updateAllMsg(MessageParam param);
    //public void addMessage(MessageParam param);
  //  public List<QzsMessagePO> queryInit(UserInfo user);
    /***
     * 消息发送
     *
     * @param param  参数
     * @history
     */
    public void sendMessage(MessageParam param);

    /**
     * 批量发送消息
     *
     * @param paramList  参数
     * @history
     */
    public void batchSendMessage(List<MessageParam> paramList);

    /**
     * 查找公司主帐号往待发送的消息列表中添加数据
     * @param companyId
     * @param messageParamList
     */
    void pushMessageParamList(Long companyId, List<MessageParam> messageParamList, MessageTemplateEnum messageTemplateEnum, String... param);

    /**
     *阅读消息
     * @param param
     */
    public void  updateMessages(MessageParam param);

    /**
     * 获取未读的消息列表
     * @param param
     * @return
     */
    public List<BaseUserMessagePO> queryUnReadMsg(MessageParam param);


    /**
     * 业务系统调用 发送手机短信
     * @param userId 接收短信人id
     * @param messageTemplateEnum 信息模板枚举
     * @param param 模板参数
     * @return
     */
    public void sendBizSmsMessages(Long userId,MessageTemplateEnum messageTemplateEnum, String ip, String... param);

    /**
     * 业务系统调用 发送站内信
     *
     * @param acceptMsgUserId     信息接收人id
     * @param messageTemplateEnum 信息模板枚举
     * @param companyId           公司id
     * @param param               模板参数
     */
    public void sendBizInsiteMessages(Long acceptMsgUserId, MessageTemplateEnum messageTemplateEnum, Long companyId, String... param);

    /**
     * 给后台工作人员发送短信和站内信
     * @param code 对应的枚举code[比如需要1_003对应的枚举,则传入003即可]
     * @param param 模板参数
     */
    public void sendJumoreManagerMessages(String code,String... param );
	
    /**
	 * 根据userId获取用户待阅读消息数 
	 * @param userId
	 * @return
	 * @Author:guodong.zhang created on 2016年12月3日 上午10:34:20
	 */
	public int getUnreadMsgNum(Long userId);
	
	
	public void update2ReadFromIdsMessages(List<Long> ids);
	//public BaseUserMessagePO pageQueryMsg4(MessageParam param);
	
	/**
	 * 已读
	 * 
	 * */
	public void readMes(List<BaseUserMessagePO> list);
}
