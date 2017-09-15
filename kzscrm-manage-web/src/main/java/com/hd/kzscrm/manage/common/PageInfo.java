package com.hd.kzscrm.manage.common;

import com.hd.wolverine.plugin.Page;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页处理
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class PageInfo {

    // limit
    private static final String LIMIT = "limit";

    // start
    private static final String START = "start";

    // 默认分页长度
    private static final String DEFAULT_LIMIT = "10";

    // 默认分页起始页码
    private static final String DEFAULT_START = "0";

    /**
     * 功能说明：从request中初始化分页参数
     * <p>为空时默认从第0页开始，每页10条数据</p>
     *
     * @param request
     * @param page    void
     */
    public static void paginationHandle(HttpServletRequest request, Page<?> page) {

        String limit = request.getParameter(LIMIT);
        String start = request.getParameter(START);
        if (StringUtils.isBlank(limit)) {
            limit = DEFAULT_LIMIT;
        }
        if (StringUtils.isBlank(start)) {
            start = DEFAULT_START;
        } else {
            Integer a = (Integer.parseInt(start) - 1);
            start = a.toString();
        }
        page.setPageSize(Integer.parseInt(limit));
        page.setCurrentResult(Integer.parseInt(start));
    }

    /**
     * 功能说明：获取分页长度，如果没有值，默认为10
     *
     * @param request
     * @return String
     */
    public static Integer getLimit(HttpServletRequest request) {
        String limit = request.getParameter(LIMIT);
        if (StringUtils.isBlank(limit)) {
            limit = DEFAULT_LIMIT;
        }
        return Integer.parseInt(limit);
    }

    /**
     * 功能说明：获取分页起始页码，如果没有值，默认为0
     *
     * @param request
     * @return String
     */
    public static Integer getStart(HttpServletRequest request) {
        String start = request.getParameter(START);
        if (StringUtils.isBlank(start)) {
            start = DEFAULT_START;
        }
        return Integer.parseInt(start);
    }

}
