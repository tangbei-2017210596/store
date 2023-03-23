package com.tb.store.controller;

import com.tb.store.entity.Address;
import com.tb.store.service.AddressService;
import com.tb.store.service.CartService;
import com.tb.store.util.JsonResult;
import com.tb.store.vo.CartVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Resource
    private CartService cartService;

    @RequestMapping("add_to_cart")
    public JsonResult<Void> add_to_cart(Integer pid,Integer amount,HttpSession session){
        cartService.addToCart(getUidFromSession(session),pid,amount,getUsernameFromSession(session));
        return new JsonResult<>(OK);
    }
    @RequestMapping({"/",""})
    public JsonResult<List<CartVO>> getVOByUid(HttpSession session){
        return new JsonResult<>(OK,cartService.getVOByUid(getUidFromSession(session)));
    }
    @RequestMapping("{cid}/num/add")
    public JsonResult<Integer> addNum(@PathVariable("cid") Integer cid,HttpSession session){
        Integer data = cartService.addNum(getUidFromSession(session), cid, getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("{cid}/num/reduce")
    public JsonResult<Integer> reduce(@PathVariable("cid") Integer cid,HttpSession session){
        Integer data = cartService.reduceNum(getUidFromSession(session), cid, getUsernameFromSession(session));
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("list")
    public JsonResult<List<CartVO>> getVOByCids(Integer[] cids,HttpSession session){
        List<CartVO> result = cartService.getVOByCids(getUidFromSession(session),cids);
        return new JsonResult<>(OK,result);
    }
}
