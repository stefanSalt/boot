package com.lee.warehouse.system.mapper;

import com.lee.warehouse.system.model.entity.Payment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-28
 */

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Payment> listPagedPayments(Page<Payment> page, Payment queryParams);

}
