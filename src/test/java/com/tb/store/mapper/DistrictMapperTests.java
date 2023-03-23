package com.tb.store.mapper;

import com.tb.store.entity.Address;
import com.tb.store.entity.District;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
//@RunWith:表示启动这个单元测试类需要传递一个参数，必须是springRunner类型
@RunWith(SpringRunner.class)
public class DistrictMapperTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private DistrictMapper districtMapper;

    @Test
    public void findByParent(){
        List<District> list = districtMapper.findByParent("210100");
        for (District d:list){
            System.out.println(d);
        }
    }
    @Test
    public void findnamebyCode(){
        String nameByCode = districtMapper.findNameByCode("610000");
        System.out.println(nameByCode);
    }

}
