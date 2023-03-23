package com.tb.store.mapper;

import com.tb.store.entity.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

//@Mapper
public interface AddressMapper {
    /**
     * 插入用户的收获地址数据
     * @param address
     * @return
     */
    Integer insert(Address address);

    Integer countByUId(Integer uid);

    List<Address> getAddressByUid(Integer uid);

    Address findByAid(Integer aid);

    /**
     * 根据用户uid修改用户的收货地址为非默认。
     * @param uid
     * @return
     */
    Integer updateAddressNonDefault(Integer uid);

    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);

    Integer delByAid(Integer aid);

    Address findLastModified(Integer uid);

}
