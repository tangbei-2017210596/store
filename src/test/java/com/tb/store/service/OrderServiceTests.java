package com.tb.store.service;

import com.tb.store.entity.Address;
import com.tb.store.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class OrderServiceTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private OrderService orderService;

    @Test
    public void createOrder(){
        Integer[] cids={5,6};
        Order order = orderService.createOrder(6, 10, "陈姣", cids);
        System.out.println(order);
    }


}
