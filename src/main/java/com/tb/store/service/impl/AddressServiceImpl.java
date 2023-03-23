package com.tb.store.service.impl;

import com.tb.store.entity.Address;
import com.tb.store.entity.User;
import com.tb.store.mapper.AddressMapper;
import com.tb.store.mapper.UserMapper;
import com.tb.store.service.AddressService;
import com.tb.store.service.DistrictService;
import com.tb.store.service.UserService;
import com.tb.store.service.ex.*;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service //将当前类的对象交给spring来管理，自动创建对象以及维护
public class AddressServiceImpl implements AddressService {

    @Resource
    private AddressMapper addressMapper;

    //添加用户收货地址的业务层依赖于DistrictService的业务接口
    @Resource
    private DistrictService districtService;

    @Value("${user.address.max-count}")
    private Integer maxCount;

    @Override
    public void addNewAddress(Integer uid, String username, Address address) {
        //调用收货地址统计的方法
        Integer count = addressMapper.countByUId(uid);
        if (count>maxCount){
            throw new AddressCountLimitException("收货地址数量超过最大数量："+maxCount);
        }


        //调用DistrictService的业务接口进行补全
        String ProvinceName = districtService.getNameByCode(address.getProvinceCode());
        String CityName = districtService.getNameByCode(address.getCityCode());
        String AreaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(ProvinceName);
        address.setCityName(CityName);
        address.setAreaName(AreaName);

        //uid、isDelete
        address.setUid(uid);

        //当只有一条收货地址时，设置为默认收货地址
        address.setIsDefault(count==0? 1:0);
        //补全四项日志
        address.setCreatedUser(username);
        address.setModifiedUser(username);
        address.setModifiedTime(new Date());
        address.setCreatedTime(new Date());

        Integer rows = addressMapper.insert(address);
        if (rows !=1){
            throw new InsertException("插入用户地址产生未知异常");
        }
    }

    @Override
    public List<Address> getAddressByUid(Integer uid) {
        return addressMapper.getAddressByUid(uid);
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        Address address = addressMapper.findByAid(aid);
        if (address==null){
            throw new AddressNotFoundException("地址未找到");
        }
        if (!address.getUid().equals(uid)){
            throw new AccessDeniedException("非法地址");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        return address;
    }

    @Override
    public void delAddress(Integer aid, Integer uid, String username) {
        Integer count = addressMapper.countByUId(uid);
        Address result = addressMapper.findByAid(aid);
        if (count==0){
            return;
        }
        if (result==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的地址数据的归宿
        if (result.getUid()!=uid){
            throw new AccessDeniedException("非法访问");
        }

        Integer rows = addressMapper.delByAid(aid);
        if (rows!=1){
            throw new DeleteException("删除数据产生未知异常");
        }
        if (result.getIsDefault()==1){
            Address lastModified = addressMapper.findLastModified(uid);
            rows = addressMapper.updateDefaultByAid(lastModified.getAid(), username, new Date());
            if (rows!=1){
                throw new UpdateException("更新数据异常");
            }
        }


    }

    @Override
    public void setDefault(Integer aid, Integer uid, String username) {
        Address byAid = addressMapper.findByAid(aid);
        if (byAid==null){
            throw new AddressNotFoundException("收货地址不存在");
        }
        //检测当前获取到的地址数据的归宿
        if (byAid.getUid()!=uid){
            throw new AccessDeniedException("非法访问");
        }
        Integer rows = addressMapper.updateAddressNonDefault(uid);
        if (rows<1){
            throw new UpdateException("更新数据时产生未知异常");
        }
        Integer rows2 = addressMapper.updateDefaultByAid(aid,username,new Date());
        if (rows2!=1){
            throw  new UpdateException("设置默认地址异常");
        }
    }
}
