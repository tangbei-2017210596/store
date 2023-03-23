package com.tb.store.mapper;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import com.tb.store.entity.Address;
import com.tb.store.entity.Cart;
import com.tb.store.vo.CartVO;
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
public class CartMapperTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private CartMapper cartMapper;
    /**
     * 1必须被test注解修饰
     * 2返回值必须是void
     * 3方法的参数不指定任何类型
     * 4方法访问修饰符必须是public
     * 满足以上方法，不用启动整个项目，就可以做单元测试
     */
    @Test
    public void ins(){
        Cart cart = new Cart();
        cart.setUid(10);
        cart.setNum(2);
        cart.setPid(10000011);
        Integer ROW = cartMapper.insert(cart);
        System.out.println(ROW);
    }

    @Test
    public void updatenumByCid(){
        Integer row = cartMapper.updateNumByCid(1, 3, "管理员", new Date());
        System.out.println(row);
    }
    @Test
    public void findbyUidandPid(){
        Cart c = cartMapper.findByUidAndPid(10, 10000011);
        System.out.println(c);
    }

    @Test
    public void findVO(){
        List<CartVO> cartVOS = cartMapper.findVOByUid(10);
        for (CartVO cartVO:cartVOS){
            System.out.println(cartVO);
        }
    }
    @Test
    public void findVObyCid(){
        Integer[] cids={5,6};
        List<CartVO> cartVOS = cartMapper.findVOByCids(cids);
        System.out.println(cartVOS);
    }

}
