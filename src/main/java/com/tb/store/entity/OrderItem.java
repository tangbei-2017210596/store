package com.tb.store.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/** 订单中的商品数据 */
@Data
public class OrderItem extends BaseEntity implements Serializable {
    private Integer id;
    private Integer oid;
    private Integer pid;
    private String title;
    private String image;
    private Long price;
    private Integer num;
}