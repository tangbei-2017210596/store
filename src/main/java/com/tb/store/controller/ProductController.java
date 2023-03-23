package com.tb.store.controller;

import com.tb.store.entity.Address;
import com.tb.store.entity.Product;
import com.tb.store.service.AddressService;
import com.tb.store.service.ProductService;
import com.tb.store.util.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Resource
    private ProductService productService;

    @RequestMapping("hot_list")
    public JsonResult<List<Product>> getHotList(){
        return  new JsonResult<>(OK,productService.findHotList());
    }

    @RequestMapping("{id}/details")
    public JsonResult<Product> getById(@PathVariable("id") Integer id){
        Product data = productService.findById(id);
        return new JsonResult<>(OK,data);
    }
}
