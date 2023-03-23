package com.tb.store.service;

import com.tb.store.entity.Address;
import com.tb.store.entity.User;
import com.tb.store.service.ex.ServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class AddressServiceTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private AddressService addressService;

    @Test
    public void addAddress(){
        Address address = new Address();
        address.setPhone("13983176524");
        address.setName("李四");
        addressService.addNewAddress(23,"chenjiao2",address);
    }
    @Test
    public void setDefault(){
        addressService.setDefault(2,10,"管理员");
    }

    @Test
    public void delete(){
        addressService.delAddress(1,10,"管理员");
    }

}
