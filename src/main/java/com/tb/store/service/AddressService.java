package com.tb.store.service;

import com.tb.store.entity.Address;

import java.util.List;

/**
 * 收货地址
 */
public interface AddressService {
    void addNewAddress(Integer uid, String username, Address address);
    List<Address> getAddressByUid(Integer uid);

    /**
     * 修改某个用户的某条收货地址为默认收货地址
     * @param aid
     * @param uid
     * @param username
     */
    void setDefault(Integer aid,Integer uid,String username);

    void delAddress(Integer aid,Integer uid,String username);
    Address getByAid(Integer aid,Integer uid);
}
