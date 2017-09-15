package com.hd.kzscrm.common.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 消息模板
 * @author: liuming
 * @since: 2016年10月27日
 * @history:
 */
public enum MessageTemplateEnum {

    /**
     * 积分系统-常规动作
     *[企业名称]    动作名称        +/-X分！如：[企业名称]  注册筷子说会员   +100分；
     */
	//SMS_REGISTER("新用户注册", "1_800", "验证码[%s]该验证码用于聚博通用户注册，5分钟有效，请勿将验证码泄露于他人！【聚博通】"),
    SMS_REGISTER("新用户注册", "1_800", "验证码[%s]该验证码用于筷子说用户注册，5分钟有效，请勿将验证码泄露于他人！【筷子说】"),

    //SMS_MODIFY_PHONE("更新手机号", "1_801", "验证码[%s]该验证码用于筷子说修改手机操作，5分钟有效，请勿将验证码泄露于他人！【筷子说】"),

    SMS_MODIFY_PASSWORD("修改密码", "1_802", "验证码[%s]该验证码用于筷子说修改密码操作，5分钟有效，请勿将验证码泄露于他人！【筷子说】"),
    SMS_USER_STOP("用户停用", "1_803", "【筷子说】尊敬的【%s】您好，抱歉，您的账号已被管理员停用，请关注！【筷子说】"),

    
    
    /**
     * 短信  前缀必须是1，参见MessageTypeEnum
     * 客户企业资质通过审核
     *【筷子说】尊敬的用户【{客户企业名称}】： 您好，贵公司已通过筷子说资质审核，恭喜成为正式客户企业！
     */
    SMS_SHOP_REVIEW("客户企业资质通过审核", "1_001", "【筷子说】尊敬的用户【%s】： 您好，贵公司已通过筷子说资质审核，恭喜成为正式客户企业！"),
    
    /**
     * 短信  前缀必须是1，参见MessageTypeEnum
     * 企业资质后台审核不通过
     *【筷子说】尊敬的用户【{拍档/客户企业名称}】：您好，抱歉的通知您，您的企业资质申请未通过审核，不通过的原因为【{原因描述}】！
     */
    SMS_SHOP_REVIEW_NOT_PASS("企业资质后台审核不通过", "1_025", "【筷子说】尊敬的用户【%s】：您好，抱歉的通知您，您的企业资质申请未通过审核，不通过的原因为【%s】！");

    String operation; //操作
    String code;     // 编号
    String content;  //消息内容

    /**
     * 通过枚举<code>code</code>获得枚举。
     *
     * @param code
     * @return MessageTemplateEnum 权限值枚举
     */
    public static MessageTemplateEnum getEnumByCode(String code) {
        for (MessageTemplateEnum mtEnum : MessageTemplateEnum.values()) {
            if (StringUtils.equals(code,mtEnum.getCode())) {
                return mtEnum;
            }
        }
        return null;
    }

    MessageTemplateEnum(String operation, String code, String content) {
        this.operation = operation;
        this.code = code;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
