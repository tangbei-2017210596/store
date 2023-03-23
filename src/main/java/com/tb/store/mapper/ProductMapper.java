package com.tb.store.mapper;

import com.tb.store.entity.Address;
import com.tb.store.entity.Product;

import java.util.Date;
import java.util.List;

//@Mapper
public interface ProductMapper {
    List<Product> findhotProduct();

    Product findById(Integer id);
}
