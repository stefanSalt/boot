package com.lee.online_store.system.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.hpsf.Decimal;
import org.springframework.stereotype.Service;

/**
 * 商品查询参数
 */
@Getter
@Setter
public class ProductQuery {
    private String productName;
    private Integer categoryId;
    private Decimal minPrice;
    private Decimal maxPrice;
    private Integer orderBy;
    private Integer sort;
    private Integer status;
}