package com.tb.store.controller;

import com.tb.store.entity.Address;
import com.tb.store.entity.Order;
import com.tb.store.service.AddressService;
import com.tb.store.service.OrderService;
import com.tb.store.util.JsonResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {
    @Resource
    private OrderService orderService;

    @RequestMapping("create")
    public JsonResult<Order> create(HttpSession session,Integer aid,Integer[] cids){
        Order order = orderService.createOrder(aid, getUidFromSession(session), getUsernameFromSession(session), cids);
        return new JsonResult<>(OK,order);
    }
}
