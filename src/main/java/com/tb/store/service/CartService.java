package com.tb.store.service;

import com.tb.store.entity.Address;
import com.tb.store.vo.CartVO;

import java.util.List;

/**
 * 收货地址
 */
public interface CartService {
    /**
     * 将商品添加到购物车中
     * @param uid
     * @param pid
     * @param amount
     * @param username
     */
     void addToCart(Integer uid,Integer pid,Integer amount,String username);
     List<CartVO> getVOByUid(Integer uid);

    /**
     *
     * @param uid
     * @param cid
     * @param username
     * @return 返回购物车中商品的数量
     */
     Integer addNum(Integer uid,Integer cid,String username);
    Integer reduceNum(Integer uid,Integer cid,String username);
    List<CartVO> getVOByCids(Integer uid,Integer[] cids);
}
