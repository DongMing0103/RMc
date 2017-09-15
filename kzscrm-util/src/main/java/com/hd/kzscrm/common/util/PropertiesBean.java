package com.hd.kzscrm.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置项对象
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
@Component("propertiesBean")
public class PropertiesBean {
    /**
     * 图片上传地址
     */
    @Value(value = "${img.upload.address}")
    private String imgUploadAddress;

    /**
     * 图片查看路径
     */
    @Value(value = "${img.view.address}")
    private String imgViewAddress;

    /**
     * 项目名称，在wolverine.properties文件中配置
     */
    @Value(value = "${appName}")
    private String appName;

    public String getImgUploadAddress() {
        return imgUploadAddress;
    }

    public void setImgUploadAddress(String imgUploadAddress) {
        this.imgUploadAddress = imgUploadAddress;
    }

    public String getImgViewAddress() {
        return imgViewAddress;
    }

    public void setImgViewAddress(String imgViewAddress) {
        this.imgViewAddress = imgViewAddress;
    }

    public String getAppName() {
        return appName;
    }
}
