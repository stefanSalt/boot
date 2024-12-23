package com.lee.warehouse.system.model.vo;

import com.lee.warehouse.system.model.entity.OrderProduct;
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