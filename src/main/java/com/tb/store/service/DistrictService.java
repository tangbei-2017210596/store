package com.tb.store.service;

import com.tb.store.entity.Address;
import com.tb.store.entity.District;

import java.util.List;

/**
 * 收货地址
 */
public interface DistrictService {

    /**
     * 根据父代号来查询区域的信息(省市区)
     * @param Parent
     * @return
     */
    List<District> getByParent(String parent);
    String getNameByCode(String code);
}
