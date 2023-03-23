package com.tb.store.service.impl;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.tb.store.entity.Address;
import com.tb.store.entity.Cart;
import com.tb.store.entity.Order;
import com.tb.store.entity.OrderItem;
import com.tb.store.mapper.AddressMapper;
import com.tb.store.mapper.CartMapper;
import com.tb.store.mapper.OrderMapper;
import com.tb.store.mapper.ProductMapper;
import com.tb.store.service.AddressService;
import com.tb.store.service.CartService;
import com.tb.store.service.OrderService;
import com.tb.store.service.ex.AccessDeniedException;
import com.tb.store.service.ex.CartNotFoundException;
import com.tb.store.service.ex.InsertException;
import com.tb.store.service.ex.UpdateException;
import com.tb.store.vo.CartVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service //将当前类的对象交给spring来管理，自动创建对象以及维护
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private AddressService addressService;
    @Resource
    private CartService cartService;
    @Override
    public Order createOrder(Integer aid, Integer uid, String username, Integer[] cids) {
        //即将要下单的列表
        List<CartVO> cartVOS = cartService.getVOByCids(uid, cids);
        Long totalPrice=0L;

        Address address = addressService.getByAid(aid, uid);
        //插入数据
        Order order = new Order();
        for (CartVO cartVO:cartVOS){
            totalPrice+=cartVO.getRealPrice()*cartVO.getNum();
            OrderItem orderItem = new OrderItem();
            orderItem.setOid(order.getOid());
            orderItem.setPid(cartVO.getPid());
            orderItem.setTitle(cartVO.getTitle());
            orderItem.setImage(cartVO.getImage());
            orderItem.setPrice(cartVO.getRealPrice());
            orderItem.setNum(cartVO.getNum());
            //日志字段
            orderItem.setModifiedTime(new Date());
            orderItem.setModifiedUser(username);
            orderItem.setCreatedTime(new Date());
            orderItem.setCreatedUser(username);
            Integer row = orderMapper.insertOrderItem(orderItem);
            if (row!=1){
                throw new InsertException("插入异常");
            }
        }
        order.setUid(uid);
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());
        order.setTotalPrice(totalPrice);
        order.setCreatedTime(new Date());
        order.setCreatedUser(username);
        order.setStatus(0); //0表示还未支付
        Integer row = orderMapper.insertOrder(order);
        if (row!=1){
            throw new InsertException("插入数据异常");
        }


        return order;
    }
}
