package com.lee.warehouse.system.service;

import com.lee.warehouse.system.model.entity.Payment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 支付信息 服务类
 *
 * @author baomidou
 * @since 2024-11-28
 */
public interface PaymentService extends IService<Payment> {


    /**
     *支付信息分页列表
     *
     * @return
     */
    IPage<Payment> listPagedPayments(Payment queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取支付信息表单数据
     *
     * @param id 支付信息ID
     * @return
     */
     Payment getPaymentData(Long id);


    /**
     * 新增支付信息
     *
     * @param formData 支付信息表单对象
     * @return
     */
    boolean savePayment(Payment formData);

    /**
     * 修改支付信息
     *
     * @param id   支付信息ID
     * @param formData 支付信息表单对象
     * @return
     */
    boolean updatePayment(Long id, Payment formData);


    /**
     * 删除支付信息
     *
     * @param ids 支付信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deletePayments(String ids);

}
