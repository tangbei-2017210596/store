package com.tb.store.service.impl;

import com.tb.store.entity.Product;
import com.tb.store.mapper.ProductMapper;
import com.tb.store.service.ProductService;
import com.tb.store.service.ex.ProductNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service //将当前类的对象交给spring来管理，自动创建对象以及维护
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> findHotList() {
        List<Product> products = productMapper.findhotProduct();
        for (Product product:products){
            product.setModifiedTime(null);
            product.setModifiedUser(null);
            product.setPriority(null);
            product.setCreatedTime(null);
            product.setCreatedUser(null);
        }
        return products;
    }

    @Override
    public Product findById(Integer id) {
        Product product = productMapper.findById(id);
        if (product==null){
            throw new ProductNotFoundException("尝试访问的商品数据不存在");
        }
        product.setModifiedTime(null);
        product.setModifiedUser(null);
        product.setPriority(null);
        product.setCreatedTime(null);
        product.setCreatedUser(null);
        return product;
    }
}
