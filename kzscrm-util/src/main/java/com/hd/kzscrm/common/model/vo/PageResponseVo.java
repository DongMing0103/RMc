/**
 *
 */
package com.hd.kzscrm.common.model.vo;


import java.util.List;

import com.hd.kzscrm.common.model.CommResponse;

/**
 * 分页返回对象
 *
 * @author: zhengzy
 * @since: 2016年9月14日13:31:41
 */
public class PageResponseVo<T> extends CommResponse {

    /**
     * 返回数据
     */
    private List<T> rows;

    /**
     * 总条数
     */
    private int total;

    public PageResponseVo() {

    }

    public PageResponseVo(List<T> rows, int total) {
        this.rows = rows;
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

}
