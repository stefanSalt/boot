package com.lee.lecture.system.service;

import com.lee.lecture.system.model.entity.Reservation;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.lecture.system.model.entity.Reservation;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 讲座预约表 服务类
 *
 * @author baomidou
 * @since 2025-01-07
 */
public interface ReservationService extends IService<Reservation> {


    /**
     *讲座预约表分页列表
     *
     * @return
     */
    IPage<Reservation> listPagedReservations(Reservation queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取讲座预约表表单数据
     *
     * @param id 讲座预约表ID
     * @return
     */
     Reservation getReservationData(Long id);


    /**
     * 新增讲座预约表
     *
     * @param formData 讲座预约表表单对象
     * @return
     */
    boolean saveReservation(Reservation formData);

    /**
     * 修改讲座预约表
     *
     * @param id   讲座预约表ID
     * @param formData 讲座预约表表单对象
     * @return
     */
    boolean updateReservation(Long id, Reservation formData);


    /**
     * 删除讲座预约表
     *
     * @param ids 讲座预约表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteReservations(String ids);

}
