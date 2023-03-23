package com.tb.store.service;

import com.tb.store.entity.Order;


import java.util.List;

/**
 * 收货地址
 */
public interface OrderService {
    Order createOrder(Integer aid,Integer uid,String username,Integer[] cids);
}
