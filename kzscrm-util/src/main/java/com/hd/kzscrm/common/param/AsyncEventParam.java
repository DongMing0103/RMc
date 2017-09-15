package com.hd.kzscrm.common.param;

import com.hd.kzscrm.common.enums.AsyncEventEnum;

/**
 * @author lichangchao
 * 异步参数
 */
public class AsyncEventParam {
    private AsyncEventEnum eventEnum;
    private Object object;

    public AsyncEventParam(AsyncEventEnum eventEnum,Object object){
        this.eventEnum = eventEnum;
        this.object =object;
     }




    public AsyncEventEnum getEventEnum() {
        return eventEnum;
    }

    public void setEventEnum(AsyncEventEnum eventEnum) {
        this.eventEnum = eventEnum;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
