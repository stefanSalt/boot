package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.entity.OrderProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.OrderProduct;
import com.lee.online_store.system.model.vo.OrderProductVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订单商品关联 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-28
 */

@Mapper
public interface OrderProductMapper extends BaseMapper<OrderProduct> {

    /**
     * 根据订单id查询订单商品信息列表
     *
     * @param orderId 订单ID
     * @return
     */
    List<OrderProductVO> listOrderProductsByOrderId(Integer orderId);

    int saveBatch(List<OrderProductVO> orderProducts);
}
