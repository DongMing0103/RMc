package com.hd.kzscrm.common.model;

/**
 * 公共分页model封装
 *
 * @author kzs admin
 */
public class PageRespModel extends RespModel {

    /**
     * 记录总数
     */
    private int total;

    public PageRespModel() {
    }

    public PageRespModel(int code, String desc) {
        super(code, desc);
    }

    public PageRespModel(int code, String desc, Object rows) {
        super(code, desc, rows);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
