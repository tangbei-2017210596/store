package com.tb.store.service.impl;

import com.tb.store.entity.Address;
import com.tb.store.entity.Cart;
import com.tb.store.mapper.AddressMapper;
import com.tb.store.mapper.CartMapper;
import com.tb.store.mapper.ProductMapper;
import com.tb.store.service.AddressService;
import com.tb.store.service.CartService;
import com.tb.store.service.DistrictService;
import com.tb.store.service.ex.*;
import com.tb.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service //将当前类的对象交给spring来管理，自动创建对象以及维护
public class CartServiceImpl implements CartService {

    @Resource
    private CartMapper cartMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public Integer addNum(Integer uid, Integer cid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        Integer num=result.getNum() + 1;
        Integer rows = cartMapper.updateNumByCid(cid, result.getNum() + 1, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {
        List<CartVO> result = cartMapper.findVOByCids(cids);
        for (CartVO i:result){
            if (!i.getUid().equals(uid)){
                result.remove(i);
            }
        }
        return result;
    }

    @Override
    public Integer reduceNum(Integer uid, Integer cid, String username) {
        Cart result = cartMapper.findByCid(cid);
        if (result==null){
            throw new CartNotFoundException("数据不存在");
        }
        if (!result.getUid().equals(uid)){
            throw new AccessDeniedException("数据非法访问");
        }
        if (result.getNum()==0){
            return 0;
        }
        Integer num=result.getNum() -1;
        Integer rows = cartMapper.updateNumByCid(cid, num, username, new Date());
        if (rows!=1){
            throw new UpdateException("更新数据失败");
        }
        return num;
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
       return cartMapper.findVOByUid(uid);
    }

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        if (result!=null){ //如果要添加的商品以及在购物车中，增加该商品在购物车装的数量即可
            Integer row = cartMapper.updateNumByCid(result.getCid(), result.getNum() +amount, username, new Date());
            if (row!=1){
                throw new UpdateException("添加到购物车产生未知异常");
            }
        }else {
            Cart cart = new Cart();
            cart.setPid(pid);
            cart.setUid(uid);
            cart.setNum(amount);
            cart.setPrice(productMapper.findById(pid).getPrice());
            cart.setCreatedUser(username);
            cart.setModifiedUser(username);
            cart.setCreatedTime(new Date());
            cart.setModifiedTime(new Date());
            Integer row = cartMapper.insert(cart);
            if (row!=1){
                throw new InsertException("添加到购物车产生未知异常");
            }
        }
    }
}
