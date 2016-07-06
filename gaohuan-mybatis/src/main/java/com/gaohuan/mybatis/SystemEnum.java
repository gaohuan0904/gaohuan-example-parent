package com.gaohuan.mybatis;

/**
 * 系统定义枚举
 *
 * @author MingDing.Li
 */
public class SystemEnum {
    /*
	 * 农庄定义
	 */
    /**
     * 创建农庄时一级分类的父节点编码
     */
    public static final String CREATE_FARM_ONE_CATEGORY_PCODE = "44971604000200010001";
    /**
     * 定制农庄时一级分类的父节点编码
     */
    public static final String CUSTOM_FARM_ONE_CATEGORY_PCODE = "44971604000200010002";
	
	
	/*
	 * 系统定义
	 */
    /**
     * 设施定义类型编码
     */
    public static final String DEVICE_TYPE_DEFINE_CODE = "44970007";
    /**
     * 增量类型
     */
    public static final String TYPE_GAIN = "449700080001";
    /**
     * 存量类型
     */
    public static final String TYPE_EXIST = "449700080002";
    /**
     * 删除标记
     */
    public static final String SYS_DELETED = "1";
    /**
     * 未删除标记
     */
    public static final String SYS_UNDELETED = "0";
	
	/*
	 * 业务类型
	 */
    /**
     * 业务类型-建造
     */
    public static final String SYS_BUSINESS_TYPE_CREATE = "449700210001";
    /**
     * 业务类型-定制
     */
    public static final String SYS_BUSINESS_TYPE_CUSTOM = "449700210002";
    /**
     * 业务类型-农庄预订
     */
    public static final String SYS_BUSINESS_TYPE_BOOK_FARM = "449700210003";
    /**
     * 业务类型-充值
     */
    public final static String SYS_BUSINESS_TYPE_RECHARGE = "449700210004";
    /**
     * 业务类型-提现
     */
    public final static String SYS_BUSINESS_TYPE_WITHDRAW = "449700210005";
    /**
     * 业务类型-消费
     */
    public final static String SYS_BUSINESS_TYPE_CONSUME = "449700210006";
    /**
     * 业务类型-酒店预订
     */
    public static final String SYS_BUSINESS_TYPE_BOOK_HOTEL = "449700210007";
	
	/*
	 * 土地定义
	 * */
    /**
     * 可租
     */
    public static final String LAND_STATUS_KEZU = "449700140001";
    /**
     * 已被租
     */
    public static final String LAND_STATUS_YIBEIZU = "449700140002";
    
	/*
	 * 返回状态定义
	 */
    /**
     * 成功的
     */
    public static final String APP_RES_STATUS_SUCCESS = "0";
    /**
     * 失败的
     */
    public static final String APP_RES_STATUS_FAILED = "1";
    /**
     * 登录成功
     */
    public static final String APP_LOGIN_STATUS_SUCCESS = "2";
    /**
     * 登录失败
     */
    public static final String APP_LOGIN_STATUS_FAILED = "3";
	
	
	/*
	 * 处理状态信息
	 */
    /**
     * 处理成功的
     */
    public static final String APP_RES_MSG_SUCCESS = "SUCCESS";
    /**
     * 处理失败的
     */
    public static final String APP_RES_MSG_FAILED = "FAILED";
    /**
     * 登录成功的
     */
    public static final String APP_LOGIN_MSG_SUCCESS = "LOGIN SUCCESS";
    /**
     * 登录失败的
     */
    public static final String APP_LOGIN_MSG_FAILED = "LOGIN FAILED";
	/*
	 * 订单状态
	 */
    /**
     * 1.待支付
     */
    public static final String ORDER_STATUS_DAIZHIFU = "449700120001";
    public static final String ORDER_STATUS_DAIZHIFU_MESSAGE = "待支付";
    /**
     * 2.已支付
     */
    public static final String ORDER_STATUS_YIZHIFU = "449700120002";
    public static final String ORDER_STATUS_YIZHIFU_MESSAGE = "已支付";
    /**
     * 3.待入住
     */
    public static final String ORDER_STATUS_DAIRUZHU = "449700120003";
    public static final String ORDER_STATUS_DAIRUZHU_MESSAGE = "待入住";
    /**
     * 4.已入住
     */
    public static final String ORDER_STATUS_YIRUZHU = "449700120004";
    public static final String ORDER_STATUS_YIRUZHU_MESSAGE = "已入住";
    /**
     * 5.待评价
     */
    public static final String ORDER_STATUS_DAIPINGJIA = "449700120005";
    public static final String ORDER_STATUS_DAIPINGJIA_MESSAGE = "待评价";
    /**
     * 6.已评价
     */
    public static final String ORDER_STATUS_YIPINGJIA = "449700120006";
    public static final String ORDER_STATUS_YIPINGJIA_MESSAGE = "已评价";
    /**
     * 7.已取消
     */
    public static final String ORDER_STATUS_YIQUXIAO = "449700120007";
    public static final String ORDER_STATUS_YIQUXIAO_MESSAGE = "已取消";
    /**
     * 8.已关闭
     */
    public static final String ORDER_STATUS_YIGUANBI = "449700120008";
    public static final String ORDER_STATUS_YIGUANBI_MESSAGE = "已关闭";

	
	
	/*
	 * 上架/下架
	 */
    /**
     * 1.上架
     */
    public static final String SYS_PRODUCT_STATUS_SHANGJIA = "449700030001";
    /**
     * 2.下架
     */
    public static final String SYS_PRODUCT_STATUS_XIAJIA = "449700030002";
	
	/*
	 * 可用/不可用
	 */
    /**
     * 1.可用
     */
    public static final String SYS_PRODUCT_STATUS_KEYONG = "449700010001";
    /**
     * 2.不可用
     */
    public static final String SYS_PRODUCT_STATUS_BUKEYONG = "449700010002";
	
	/*
	 * 优惠政策
	 */
    /**
     * 1.送时间
     */
    public static final String FARM_DISCOUNT_TYPE_TIME = "449700190001";
    /**
     * 2.减金额
     */
    public static final String FARM_DISCOUNT_TYPE_MONEY = "449700190002";
	
	/*
	 * 定制状态
	 */
    /**
     * 1.可定制
     */
    public static final String FARM_CUSTOM_STATUS_KEDINGZHI = "449700220001";
    /**
     * 2.已定制
     */
    public static final String FARM_CUSTOM_STATUS_YIDINGZHI = "449700220002";

    /**
     * 支付方式-微信支付
     */
    public static final String WX_PAY_TYPE = "449700200001";
    /**
     * 支付方式-支付宝
     */
    public static final String ALIPAY_TYPE = "449700200002";
    /**
     * 支付方式-零钱
     */
    public static final String WALLET_TYPE = "449700200003";
    /**
     * 支付方式-到店现付
     */
    public static final String CASH_PAY_TYPE = "449700200004";


    /**
     * 账户余额变动方式-充值
     */
    public final static String WALLET_CHANGE_TYPE_CZ = "449700150001";
    /**
     * 账户余额变动方式-消费
     */
    public final static String WALLET_CHANGE_TYPE_XF = "449700150002";
    /**
     * 账户余额变动方式-提现
     */
    public final static String WALLET_CHANGE_TYPE_TX = "449700150003";
    /**
     * 账户余额变动方式-积分互换兑入
     */
    public final static String WALLET_CHANGE_TYPE_DR = "449700150004";
    /**
     * 账户余额变动方式-积分互换兑出
     */
    public final static String WALLET_CHANGE_TYPE_DC = "449700150005";


    /**
     * 天
     */
    public static final String FARM_UNIT_DAY = "449700040001";
    /**
     * 月
     */
    public static final String FARM_UNIT_MONTH = "449700040002";
    /**
     * 年
     */
    public static final String FARM_UNIT_YEAR = "449700040003";

    /**
     * 版本控制version
     */
    public static final String VERSION_1 = "1.0";
    public static final String VERSION_1_1 = "1.1";
    public static final String VERSION_2 = "2.0";
    public static final String VERSION_3 = "3.0";
	
	/*
	 * log操作日志类型
	 */
    /**
     * 0.未知类型
     */
    public static final String LOG_TYPE_NULL = "0";
    /**
     * 1.登录类型
     */
    public static final String LOG_TYPE_LOGIN = "1";
    /**
     * 2.注册类型
     */
    public static final String LOG_TYPE_REGIST = "2";
    /**
     * 3.建造下单类型
     */
    public static final String LOG_TYPE_ORDER_CREATE = "3";
    /**
     * 4.定制下单类型
     */
    public static final String LOG_TYPE_ORDER_CUSTOM = "4";
    /**
     * 5.预订下单类型
     */
    public static final String LOG_TYPE_ORDER_BOOK = "5";

    /**
     * 酒店房间允许支付方式
     */
    /**
     * 预付
     **/
    public static final String ADVANCE_ALLOW_PAY_TYPE = "449700290001";
    /**
     * 现付
     **/
    public static final String CASH_ALLOW_PAY_TYPE = "449700290002";
    /**
     * 预付|现付
     **/
    public static final String ADVANCE_AND_CASH_ALLOW_PAY_TYPE = "449700290003";

    /**
     * 个人上传农庄审核状态
     */
    /**
     * 未提交审核
     **/
    public static final String AUDIT_STATUS_TYPE_UNCOMMITTED = "449700270001";
    /**
     * 正在审核中
     **/
    public static final String AUDIT_STATUS_TYPE_AUDITING = "449700270002";
    /**
     * 审核已通过
     **/
    public static final String AUDIT_STATUS_TYPE_PASSED = "449700270003";
    /**
     * 审核未通过
     **/
    public static final String AUDIT_STATUS_TYPE_NOTPASSED = "449700270004";

}
