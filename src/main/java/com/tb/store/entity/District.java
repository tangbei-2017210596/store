package com.tb.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/** 省/市/区数据的实体类 */
@Data
public class District implements Serializable {
    private Integer id;
    private String parent;
    private String code;
    private String name;
}
