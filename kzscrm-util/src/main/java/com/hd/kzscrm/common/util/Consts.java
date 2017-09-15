package com.hd.kzscrm.common.util;

/**
 * 常量类
 *  
 * @since  2016年11月11日 下午4:04:11
 * @author zhouguobao
 * @history
 */
public class Consts {

    public static final String SESSION_USER_KEY = "user_session_key";
    public static final String SESSION_BUSINESS_KEY = "business_session_key";
    public static final String SESSION_AGENT_KEY = "agent_session_key";

    public static final String SESSION_MENU_ROLE_KEY = "session_menu_of_role_key";

    /**
     * 系统主key
     */
    public static String SYS_KEY_MAIN = "Qzs-web";

    /**
     * 数据选项码
     */
    public static String BASE_DATA_OPTION = "baseDataOption";

    /**
     * 地区表
     */
    public static String BASE_AREA = "baseArea";

    /**
     * 退出要跳转的页面（其他前台系统进入企业中心之前的页面）
     */
    public static String PREVIOUS_URL = "previous_url";

    /**
     * 重新加载之前的菜单
     */
    public static String PREVIOUS_MENU = "previous_menu";

    /*******************聚咨询后台项目缓存bizType**************************/
    // 长期缓存
    public static final Integer CACHE_OVER = Integer.MAX_VALUE;

    // 临时缓存：1小时
    public static final Integer CACHE_ONE_HOUR = 60 * 60;

    /**
     * 默认的语言编码
     */
    public static final String DEFAULT_LANGUAGE_CODE = "113";

    /**
     * 错误返回结果code
     */
    public static final int RESULT_CODE_ERROR = 4;

    /**
     * 错误返回结果msg
     */
    public static final String RESULT_MSG_ERROR = "操作失败";

    /**
     * 正确返回结果code
     */
    public static final int RESULT_CODE_SUCCESS = 200;

    /**
     * 正确返回结果msg
     */
    public static final String RESULT_MSG_SUCCESS = "操作成功";

    /**
     * 返回结果code的key
     */
    public static final String RESULT_CODE = "statusCode";

    /**
     * 返回结果msg的key
     */
    public static final String RESULT_MSG = "message";
    

	public final static int CATE_LEVE_FIRST = 1;// 分类层级 一级
	
	public final static int CATE_LEVE_SECOND = 2;// 分类层级 一级
}