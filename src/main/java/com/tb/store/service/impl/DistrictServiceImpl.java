package com.tb.store.service.impl;

import com.tb.store.entity.Address;
import com.tb.store.entity.District;
import com.tb.store.mapper.AddressMapper;
import com.tb.store.mapper.DistrictMapper;
import com.tb.store.service.AddressService;
import com.tb.store.service.DistrictService;
import com.tb.store.service.ex.AddressCountLimitException;
import com.tb.store.service.ex.InsertException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service //将当前类的对象交给spring来管理，自动创建对象以及维护
public class DistrictServiceImpl implements DistrictService {

    @Resource
    private DistrictMapper districtMapper;

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);
        for (District i:list){
            i.setId(null);
            i.setParent(null);
        }
        return list;
    }
}
