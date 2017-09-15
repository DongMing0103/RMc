package com.hd.kzscrm.common.model.vo;

import com.hd.kzscrm.common.util.Consts;

public class ResponseVo<T>{

    /** 业务处理结果, 0成功 -1失败处理失败，默认成功 **/
    private Integer code;

    /** 提示信息 **/
    private String desc;

    /** 记录总数，只在需要分页时使用，没有默认值，使用前必须赋值 **/
    private Integer total;

    /** 返回值 **/
    private T data;

    private ResponseVo() {
        // 默认业务处理成功
        this.code = Consts.RESULT_CODE_SUCCESS;
    }

    public Integer getCode() {
        return code;
    }

    public ResponseVo<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getDesc() {
        return desc;
    }

    public ResponseVo<T> setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseVo<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public ResponseVo<T> setTotal(Integer total) {
        this.total = total;
        return this;
    }

    /**
     * 简化使用
     * @param <E>
     * @return
     */
    public static <E> ResponseVo<E> BUILDER() {
        return new ResponseVo<E>();
    }
}
