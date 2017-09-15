package com.hd.kzscrm.common.param;

import com.hd.wolverine.dao.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author  lichangchao
 * 排序参数
 */
public class OrdersParam  extends  PageParam{
    private static final Logger logger = LoggerFactory.getLogger(OrdersParam.class);
    List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void addOrder(String field, String direction) {
        Order.Direction dir = null;
        if (StringUtils.isEmpty(direction)) {
            logger.warn("direction should not be empty");
        } else if (StringUtils.isEmpty(field)) {
            logger.warn("order field should not be empty");
        } else {
            if ("asc".equalsIgnoreCase(direction)) {
                dir = Order.Direction.ASC;
            } else {
                if (!"desc".equalsIgnoreCase(direction)) {
                    logger.warn("invalide order direction");
                    return;
                }

                dir = Order.Direction.DESC;
            }

            this.orders.add(new Order(field, dir, (String) null));
        }
    }
}
