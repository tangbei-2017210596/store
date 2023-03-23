package com.tb.store.mapper;

import com.tb.store.entity.Address;
import com.tb.store.entity.User;
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
public class AddressMapperTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private AddressMapper addressMapper;
    /**
     * 1必须被test注解修饰
     * 2返回值必须是void
     * 3方法的参数不指定任何类型
     * 4方法访问修饰符必须是public
     * 满足以上方法，不用启动整个项目，就可以做单元测试
     */
    @Test
    public void ins(){
        Address address = new Address();
        address.setUid(10);
        address.setPhone("130");
        address.setName("李四");
        Integer rows = addressMapper.insert(address);
        System.out.println(rows);
    }
    @Test
    public void countByUid(){
        Integer count=addressMapper.countByUId(23);
        System.out.println(count);
    }
    @Test
    public void getAddressByUid(){
        List<Address> addressByUid = addressMapper.getAddressByUid(10);
        for (Address a:addressByUid){
            System.out.println(a);
        }
    }
    @Test
    public void findByAid(){
        Address byAid = addressMapper.findByAid(2);
        System.out.println(byAid);
    }
    @Test
    public void updateAddressNonDefault(){
        Integer rows = addressMapper.updateAddressNonDefault(10);
        System.out.println(rows);
    }
    @Test
    public void updateDefaultByAid(){
        Integer rows = addressMapper.updateDefaultByAid(1,"管理员",new Date());
        System.out.println(rows);
    }

    @Test
    public void findLastModified(){
        Address lastModified = addressMapper.findLastModified(10);
        System.out.println(lastModified);
    }

}
