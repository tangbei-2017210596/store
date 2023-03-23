package com.tb.store.service;

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
public class DistrictServiceTests {
    //如果用@Autowired就不行，因为idea有检测功能，接口是不能直接创建Bean的（动态代理技术来解决）
    @Resource
    private DistrictService districtService;

    @Test
    public void getByParent(){
        List<District> byParent = districtService.getByParent("86");
        for (District d:byParent){
            System.err.println(d);// System.err.println()打印的是红色字
        }
    }
}
