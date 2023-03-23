package com.tb.store.service;

import com.tb.store.entity.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class CartServiceTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private CartService cartService;



    @Test
    public void addToCart(){
        cartService.addToCart(10,10000002,2,"管理员");
    }

}
