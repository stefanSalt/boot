package com.lee.selection.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求对象
 *
 * @Date 2024-10-14 17:08
 */
@Data
public class BasePageQuery implements Serializable {
    private int pageNum=1;
    private int pageSize=10;
}