package com.lee.warehouse.system.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 商品查询参数
 */
@Getter
@Setter
public class ProductQuery {
    private String productName;
    private Integer categoryId;
    private String categoryIds;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer orderBy;
    private String sort;
    private Integer status;
}