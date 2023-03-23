package com.tb.store.mapper;

import com.tb.store.entity.District;

import java.util.List;

public interface DistrictMapper {
    List<District> findByParent(String parent);
    String findNameByCode(String code);
}
