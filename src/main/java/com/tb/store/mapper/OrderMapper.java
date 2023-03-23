package com.tb.store.mapper;

import com.tb.store.entity.Cart;
import com.tb.store.entity.Order;
import com.tb.store.entity.OrderItem;
import com.tb.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/** 处理购物车数据的持久层接口 */
public interface OrderMapper {

    Integer insertOrder(Order order);
    Integer insertOrderItem(OrderItem orderItem);
}
