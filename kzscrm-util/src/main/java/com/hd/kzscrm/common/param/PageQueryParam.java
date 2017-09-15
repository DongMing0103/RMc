package com.hd.kzscrm.common.param;

import java.io.Serializable;

/**
 * 分页查询参数
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class PageQueryParam extends BaseParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 默认起始行数
     */
    public static final Integer DEFAULT_START = 1;

    /**
     * 默认每页数量
     */
    public static final Integer DEFAULT_LIMIT = 10;

    /**
     * 起始行数
     */
    private Integer start;

    /**
     * 每页数量
     */
    private Integer limit;

    /**
     * 功能说明：获取起始行数
     * 为了与框架对应，这里的起始行数是从1开始的，实际查询时需要减1
     *
     * @return Integer
     */
    /**
	 * 用户手机号，用于验证userToken
	 */
	private String mobilephone;
	/**
	 * token
	 */
	private String userToken;
    public Integer getStart() {
        if (null == start || start<=0) {
            start = DEFAULT_START;
        }
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        if (null == limit) {
            limit = DEFAULT_LIMIT;
        }
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
}
