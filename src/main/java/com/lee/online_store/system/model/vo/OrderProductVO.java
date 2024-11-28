package com.lee.online_store.system.model.vo;

import com.lee.online_store.system.model.entity.OrderProduct;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * TODO
 */
@Getter
@Setter
public class OrderProductVO extends OrderProduct {
    private String productName;
    private String productImg;
}