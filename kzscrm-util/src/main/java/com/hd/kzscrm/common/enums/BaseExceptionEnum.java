package com.hd.kzscrm.common.enums;

/**
 * 物流后台异常枚举
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public enum BaseExceptionEnum {
    //公共业务异常
    /**
     * 未查询到数据
     */
    NOT_QUERY_TO_THE_DATA("200000001", "未查询到数据"),

    /**
     * 创建失败
     */
    CREATE_FAILURE("200000002", "创建失败:%s"),

    /**
     * 创建失败
     */
    CREATE_FAILURE_NOPREFIX("200000004", "%s"),

    /**
     * 修改失败
     */
    UPDATE_FAILURE("200000003", "修改失败:%s"),

    /**
     * 修改失败
     */
    UPDATE_FAILURE_NOPREFIX("200000005", "%s"),

    /**修改失败*/

    //详细业务异常
    /**
     * 实例化异常
     */
    INSTANTIATION_EXCEPTION("100000001", "实例化异常"),
    /**
     * 找不到需求单
     */
    NEEDS_NOT_EXIST("100000002", "找不到需求单"),
    /**
     * 参数异常
     */
    PARAM_ERROR("100000003", "参数异常"),
    /**
     * 当前状态不允许操作
     */
    OPERATE_UNSUPPORTED("100000004", "状态已变更，请刷新页面后重试"),
    /**
     * 找不到运力单
     */
    LINE_NOT_EXIST("100000006", "该运力单号不存在"),
    /**
     * 需求推荐时未指定供应商
     */
    NO_COMPANY("100000007", "请选择供应商"),
    /**
     * 供应商信息不完善，无法通过审批
     */
    COMPANY_NOEXIST("100000009", "请完善供应商信息"),
    /**
     * 需求报价时，物流方式不匹配
     */
    LINE_TYPE_MISMATCH("100000010", "物流方式不匹配"),
    /**
     * 需求报价时，当前报价必须低于历史报价
     */
    OFFER_PRICE_HIGHER("100000011", "当前报价必须低于历史报价"),
    /**
     * 文件解析失败
     */
    FILE_PARSE_FAILED("100000012", "文件解析失败"),
    /**
     * 解析失败
     */
    VALIDATE_FAILED("100000012", "格式有误{0}"),
    /**
     * 暂停交易
     */
    COMPANY_PAUSE("100000013", "该账号已暂停交易"),
    /**
     * 不符合审核条件
     */
    AUDIT_NOT_SATISFIED("100000014", "无效运力线路和非正常状态公司不能进行审核"),
    /**
     * 导出无数据
     */
    EXPORT_NO_DATA("100000015", "导出无数据"),
    /**
     * 下载出现异常
     */
    DOWNLOAD_ERROR("100000016", "下载出现异常"),
    /**
     * 不符合审核条件
     */
    OLD_PASSWORD_ERROR("100000017", "原密码输入不正确"),
    /**
     * 帐户存在
     */
    ACCOUNT_ALREADY("100000018", "当前帐户已存在"),
    /**
     * 角色不允许禁用
     */
    ROLE_DISABLE("100000019", "当前角色不允许禁用"),
    /**
     * 帐户存在
     */
    ROLE_ALREADY("100000020", "当前角色已存在"),
    /**
     * 运力不可用
     */
    LINE_UNUSED("100000021", "您当前选择的运力审核未通过或已失效或已下架，无法进行报价，请选择有效的运力！"),
    /**
     * 供应商未通过审核
     */
    COMPANY_UNCHECKED("100000022", "您当前选择的供应商未通过审核，无法操作！"),
    /**
     * 字典数据为空
     */
    BASE_DATA_OPTION_NULL("100000023", "字典数据为空"),;
    /**
     * 编码
     */
    private String code;
    /**
     * 消息内容
     */
    private String msg;

    BaseExceptionEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
