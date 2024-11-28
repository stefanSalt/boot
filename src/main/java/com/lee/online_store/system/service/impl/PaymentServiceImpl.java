package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.Payment;
import com.lee.online_store.system.mapper.PaymentMapper;
import com.lee.online_store.system.service.PaymentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.online_store.system.model.entity.Payment;

import java.util.Arrays;
import java.util.List;
/**
 * 支付信息服务实现类
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {


    /**
    * 获取支付信息分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Payment>} 支付信息分页列表
    */
    @Override
    public IPage<Payment> listPagedPayments(Payment queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Payment> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Payment> boPage = this.baseMapper.listPagedPayments(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取支付信息表单数据
     *
     * @param id 支付信息ID
     * @return
     */
    @Override
    public Payment getPaymentData(Long id) {
        Payment entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增支付信息
     *
     * @param formData 支付信息表单对象
     * @return
     */
    @Override
    public boolean savePayment(Payment formData) {

        return this.save(formData);
    }
    
    /**
     * 更新支付信息
     *
     * @param id   支付信息ID
     * @param formData 支付信息表单对象
     * @return
     */
    @Override
    public boolean updatePayment(Long id,Payment formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除支付信息
     *
     * @param ids 支付信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deletePayments(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的支付信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
