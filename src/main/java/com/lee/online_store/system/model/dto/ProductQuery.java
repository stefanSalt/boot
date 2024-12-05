package com.lee.online_store.system.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hpsf.Decimal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 商品查询参数
 */
@Getter
@Setter
public class ProductQuery {
    private String productName;
    private Integer categoryId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Integer orderBy;
    private String sort;
    private Integer status;
}