package com.tb.store.mapper;

import com.tb.store.entity.Address;
import com.tb.store.entity.Order;
import com.tb.store.entity.OrderItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class OrderMapperTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private OrderMapper orderMapper;
    /**
     * 1必须被test注解修饰
     * 2返回值必须是void
     * 3方法的参数不指定任何类型
     * 4方法访问修饰符必须是public
     * 满足以上方法，不用启动整个项目，就可以做单元测试
     */
    @Test
    public void ins(){
        Order order = new Order();
        order.setUid(10);
        order.setRecvName("李四");
        order.setRecvPhone("123");
        Integer row = orderMapper.insertOrder(order);
        System.out.println(row);
    }
    @Test
    public void insItem(){
        OrderItem orderItem = new OrderItem();
        orderItem.setId(10);
        orderItem.setOid(1);
        orderItem.setPid(10000042);
        Integer row = orderMapper.insertOrderItem(orderItem);
        System.out.println(row);
    }

}
