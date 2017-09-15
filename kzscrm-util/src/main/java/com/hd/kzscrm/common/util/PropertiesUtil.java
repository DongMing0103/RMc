package com.hd.kzscrm.common.util;

import org.apache.commons.lang.StringUtils;

import com.hd.wolverine.util.SpringContextHelper;

/**
 * 配置项工具类
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class PropertiesUtil {
    /***/
    public static PropertiesBean bean = (PropertiesBean) SpringContextHelper.getBean("propertiesBean");

    /**
     * 获取图片上传服务类路径
     */
    public static String getImgUploadAddress() {
        return bean.getImgUploadAddress();
    }

    /**
     * 获取图片查看服务类路径
     */
    public static String getImgViewAddress() {
        return bean.getImgViewAddress();
    }

    /**
     * 功能说明：根据图片的不完整地址获取图片的完整地址(完整地址=fileViewAddress+不完整地址)
     *
     * @param url
     * @return String
     */
    public static String getFileViewUrl(String url) {
        if (StringUtils.isEmpty(url)) {
            return url;
        }
        if (url.startsWith("http://")) {
            return url;
        }
        return getImgViewAddress() + url;
    }

    /**
     * 获取项目名称
     */
    public static String getAppName() {
        return bean.getAppName();
    }
}
