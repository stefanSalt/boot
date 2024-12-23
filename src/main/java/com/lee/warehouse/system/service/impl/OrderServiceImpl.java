package com.lee.warehouse.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.warehouse.system.mapper.OrderProductMapper;
import com.lee.warehouse.system.mapper.PaymentMapper;
import com.lee.warehouse.system.mapper.ProductMapper;
import com.lee.warehouse.system.model.dto.OrderQuery;
import com.lee.warehouse.system.model.entity.Order;
import com.lee.warehouse.system.mapper.OrderMapper;
import com.lee.warehouse.system.model.entity.Payment;
import com.lee.warehouse.system.model.entity.Product;
import com.lee.warehouse.system.model.vo.OrderProductVO;
import com.lee.warehouse.system.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
/**
 * 订单服务实现类
 *
 * @author baomidou
 * @since 2024-11-28
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderProductMapper orderProductMapper;
    private final ProductMapper productMapper;
    private final PaymentMapper paymentMapper;


    /**
    * 获取订单分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Order>} 订单分页列表
    */
    @Override
    public IPage<Order> listPagedOrders(OrderQuery queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Order> page = new Page<>(pageNum, pageSize);

    
        // 查询数据
        Page<Order> boPage = this.baseMapper.listPagedOrders(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取订单表单数据
     *
     * @param id 订单ID
     * @return
     */
    @Override
    public Order getOrderData(Long id) {
        Order entity = this.getById(id);
        entity.setOrderProducts(orderProductMapper.listOrderProductsByOrderId(entity.getId()));
        return entity;
    }
    
    /**
     * 新增订单
     *
     * @param formData 订单表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean saveOrder(Order formData) {

        // 获取订单商品
        List<OrderProductVO> orderProducts = formData.getOrderProducts();
        List<Long> productIds = orderProducts.stream().map(OrderProductVO::getProductId).map(Long::valueOf).toList();
        //获取商品信息
        List<Product> products = productMapper.listDiscountByIds(productIds);

        //根据商品信息计算订单商品价格
        for (OrderProductVO orderProduct : orderProducts) {
            for (Product product : products) {
                if (orderProduct.getProductId().equals(product.getId())) {
                    orderProduct.setPrice(BigDecimal.valueOf(product.getPrice()));
                    orderProduct.setDiscountValue(product.getDiscount());
                    orderProduct.setQuantity(orderProduct.getQuantity());

                    orderProduct.setDiscountAmount(product.getDiscount().multiply(new java.math.BigDecimal(orderProduct.getQuantity()))
                            .multiply(orderProduct.getPrice()));
                    break;
                }
            }
        }
        //计算订单总金额 =各个商品的单价*各个商品数量
           formData.setTotalAmount(orderProducts.stream()
       .filter(product -> product.getPrice() != null && product.getQuantity() != null)
       .map(product -> product.getPrice().multiply(new java.math.BigDecimal(product.getQuantity())))
       .reduce(BigDecimal.ZERO, BigDecimal::add));



        //计算订单优惠后金额
   formData.setDiscountAmount(orderProducts.stream()
       .filter(product -> product.getDiscountAmount() != null)
       .map(OrderProductVO::getDiscountAmount)
       .reduce(BigDecimal.ZERO, BigDecimal::add));
           formData.setStatus(1);
        boolean save = this.save(formData);
        //订单商品关联订单ID
        orderProducts.forEach(orderProduct -> orderProduct.setOrderId(formData.getId()));
        orderProductMapper.saveBatch(orderProducts);

        //生成支付信息
        Payment payment = new Payment();
        payment.setOrderId(formData.getId());
        payment.setAmount(formData.getDiscountAmount());
        payment.setPaymentMethod(formData.getPaymentMethod());
        payment.setStatus(1);
        paymentMapper.insert(payment);
        return save;
    }
    
    /**
     * 更新订单
     *
     * @param id   订单ID
     * @param formData 订单表单对象
     * @return
     */
    @Override
    public boolean updateOrder(Long id,Order formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除订单
     *
     * @param ids 订单ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteOrders(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的订单数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public List<Order> listOrders(OrderQuery queryParams) {
        List<Order> list = orderMapper.listProducts(queryParams);
        return list;
    }
}
