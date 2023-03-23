package com.tb.store.service;

import com.tb.store.entity.Address;
import com.tb.store.entity.Product;

import java.util.List;

/**
 * 收货地址
 */
public interface ProductService {
    List<Product> findHotList();
    Product findById(Integer id);
}
