package com.hd.kzscrm.service.constants;

public class QzsWebConstants {

    /**
     * 私有化构造函数
     */
    private QzsWebConstants(){

    }
   
    
    /**
     * 用户session
     */
    public static String PROJECT_MODEL = "QZS:";

    /**
     * 用户session
     */
    public static String SESSION_USER = "user";
    
    /**
     * 公司session
     */
    public static String SESSION_COMPANY = "company";
    
    /** 
     * 店铺信息session key 
     */ 
    public static String SESSION_SHOPINFO = "shopInfo";
    
    /**
     * 手机验证码失效时间
     */
    public static int MOBILE_CHK_CODE_EXPIRE_TIME = 600;

    /**
     * 图片验证码失效时间
     */
    public static int IMAGE_CHK_CODE_EXPIRE_TIME = 300;

    /**
     * redis手机验证码objtype
     */
    public static String OBJTYPE_INITVERCODE = "userinfo:qzs_initvercode";
    /**
     * redis的usertoken
     */
    public static String OBJTYPE_USER_TOKEN= "user:qzs_userToken";
    /**
     * redis的订单编号
     */
    public static String OBJTYPE_ORDER_NO= "order:qzs_order_no";
    /**
     * redis手机验证码objtype
     */
    public static String OBJTYPE_USER_SENDVERICODEMSG = "user:qzs_sendVeriCodeMsg";

    /**
     * redis手机验证码objtype
     */
    public static String OBJTYPE_USER_GETVERIFYCODE = "user:qzs_getVerifyCode";

    /**
     * 用户权利列表
     */
    public static String USER_ROLE_LIST = "userRoleList";

    /**
     * 当前登陆者公司
     */
    public static String CURRENT_COMPANY ="CurrentCompany";

    /**
     * 是否为主账户：true主账户，false子账户
     */
    public static String IS_OWNER = "IsOwner";

    /**
     * 删除标识 0：正常
     */
    public static Integer DELETE_FLAG_NO = 0;

    /**
     * 删除标识 1：已删除
     */
    public static Integer DELETE_FLAG_YES = 1;

    /**
     * 公司级别
     */
    public static String LG_COMPANY_GRADE = "lgCompanyGrade";

    /**
     * 地区表
     */
    public static String BASE_AREA = "baseArea";

    /**
     *退出要跳转的页面（其他前台系统进入企业中心之前的页面）
     */
    public static String PREVIOUS_URL="previous_url";

    /**
     *重新加载之前的菜单
     */
    public static String PREVIOUS_MENU="previous_menu";

    /**
     * 是否物流供应商 true是，false否
     */
    public static String IS_PDCOMPANY = "isLGCompany";

    /**
     * 静态文件全域名session key
     */
    public static String STATIC_DOMAIN_URL = "staticDomainUrl";

    /**
     * 身份 0：客户
     */
    public static String CUSTOMER_FALG = "0";

    /**
     * 身份 1：拍档
     */
    public static String PARTNER_FALG = "1";


    /**
     * 上传用静态文件全域名session key
     */
    public static String STATIC_UPLOAD_URL = "staticUploadUrl";

    /**
     * 降序
     */
    public static final String SORT_DESC = "DESC";

    /**
     * 创建时间(排序字段)
     */
    public static final String COLUMN_CREATE_TIME = "create_time";

    /**
     * 日期结束时间
     */
    public static final String DATE_SECONDS_END = " 23:59:59";




    /*****************************Qzs 缓存表数据常量*******************************/

    /**
     * 系统主key
     */
    public static String SYS_KEY_MAIN = "Qzs-web";

    /**
     * 数据选项码
     */
    public static String BASE_DATA_OPTION = "baseDataOption";

    /**
     * 国检国家地区编码表
     */
    public static String CIQ_COUNTRY = "ciq_country";

    /**
     * 国检所需单证编码表
     */
    public static String CIQ_DOCUMENT = "ciq_document";

    /**
     * 国检随附单据-出境编码表
     */
    public static String CIQ_DOCUMENT_OUT = "ciq_document_out";

    /**
     * 国检随附单据-入境编码表
     */
    public static String CIQ_DOCUMENT_IN = "ciq_document_in";

    /**
     * 国检国内口岸编码表
     */
    public static String CIQ_INNER_PORT = "ciq_inner_port";

    /**
     * 国检施检机构编码表
     */
    public static String CIQ_INSPECTON_ORG = "ciq_inspecton_org";

    /**
     * 国检包装类型编码表
     */
    public static String CIQ_PACKING_TYPE = "ciq_packing_type";

    /**
     * 国检贸易方式编码表
     */
    public static String CIQ_TRADE_METHOD = "ciq_trade_method";

    /**
     * 国检运输工具编码表
     */
    public static String CIQ_TRANSPORT_TYPE = "ciq_transport_type";

    /**
     * 国检报检类别编码表
     */
    public static String CIQ_TYPE = "ciq_type";

    /**
     * 国检计量单位编码表
     */
    public static String CIQ_UNIT = "ciq_unit";

    /**
     * 海关国别编码表
     */
    public static String CUST_COUNTRY = "cust_country";

    /**
     * 海关币制表
     */
    public static String CUST_CURR = "cust_curr";

    /**
     * 海关关区编码表
     */
    public static String CUST_CUSTOMS = "cust_customs";
    /**
     * 海关成交方式编码表
     */
    public static String CUST_DEAL_WAY = "cust_deal_way";

    /**
     * 海关征免性质编码表
     */
    public static String CUST_LEVY_TYPE = "cust_levy_type";

    /**
     * 包装种类编码表
     */
    public static String CUST_PACKING_TYPE = "cust_packing_type";

    /**
     * 海关指运港编码表
     */
    public static String CUST_REFER_PORT = "cust_refer_port";

    /**
     * 海关监管方式编码表
     */
    public static String CUST_SUPERVISION_WAY = "cust_supervision_way";

    /**
     * 海关运输工具编码表
     */
    public static String CUST_TRANSPORT_TOOL = "cust_transport_tool";

    /**
     * 海关运输方式编码表
     */
    public static String CUST_TRANSPORT_TYPE = "cust_transport_type";

    /**
     * 海关计量单位编码表
     */
    public static String CUST_UNIT = "cust_unit";


    /**
     * 企业星级规则表
     */
    public static String Qzs_LEVEL_RULE = "qzs_level_rule";


    /**
     * 企业比率规则表
     */
    public static String Qzs_LEVEL_PROPORTION = "qzs_level_proportion";
    
   // add by linxiaojun
    /**
     * 数量单位编码表
     */
    public static String CIQ_UNIT_TYPE = "ciq_unit_type";


    /**
     * 包装种类编码表
     */
    public static String CIQ_PACKAGE_TYPE = "ciq_package_type";


    public static final String GOODS_TABLE_NAME = "qzs_goods_info";

    /**
     * 金融机构
     */
    public static final String Qzs_FINANCIAL_ORG = "qzs_financial_org";

    /**
     * 文章
     */
    public static final String Qzs_CMS_ARTICLE = "qzs_cms_article";
    
    /**
     * 授权书名称
     */
    public static  final String SQS_DOC = "企业授权书2016.11.7.doc";
    
    
    /**
     * 网站名称
     */
    public static String WEB_TITILE_CN_ZH = "筷子说";
    
    /**
     * 联系单类型
     */
    public final static int BILL_TYPE_PRODUCT = 1;//联系单类型 1-产品
    public final static int BILL_TYPE_SOLUTION = 2;//联系单类型 2-解决方案
    
    //店铺类型
    public final static int SHOP_STATUS_OPEN = 1;//店铺状态 1-开通
    public final static int SHOP_STATUS_CLOSE = 0;//店铺状态 0-关闭

	public static final Object PRODUCT_ONSHELF = 1;//产品状态 1-上架
	

    // 图片验证码宽
	public final static int VERIFY_WIDTH = 80;
    // 图片验证码高
	public final static int VERIFY_HEIGHT = 30;
}
