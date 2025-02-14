package com.lee.system.service;

import com.lee.system.model.entity.Reservation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.Reservation;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 预约记录表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface ReservationService extends IService<Reservation> {


    /**
     *预约记录表分页列表
     *
     * @return
     */
    IPage<Reservation> listPagedReservations(Reservation queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取预约记录表表单数据
     *
     * @param id 预约记录表ID
     * @return
     */
     Reservation getReservationData(Long id);


    /**
     * 新增预约记录表
     *
     * @param formData 预约记录表表单对象
     * @return
     */
    boolean saveReservation(Reservation formData);

    /**
     * 修改预约记录表
     *
     * @param id   预约记录表ID
     * @param formData 预约记录表表单对象
     * @return
     */
    boolean updateReservation(Long id, Reservation formData);


    /**
     * 删除预约记录表
     *
     * @param ids 预约记录表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteReservations(String ids);

}
