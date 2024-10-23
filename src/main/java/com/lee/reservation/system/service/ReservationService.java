package com.lee.reservation.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.reservation.system.model.entity.Reservation;
import com.lee.reservation.system.model.form.ReservationForm;
import com.lee.reservation.system.model.query.ReservationCalendarQuery;
import com.lee.reservation.system.model.query.ReservationPageQuery;
import com.lee.reservation.system.model.vo.ReservationPageVO;
import com.lee.reservation.system.model.vo.ReservationVO;

import java.util.List;

/**
 * 预约记录 服务类
 *
 * @author baomidou
 * @since 2024-10-17
 */
public interface ReservationService extends IService<Reservation> {


    /**
     *预约记录分页列表
     *
     * @return
     */
    IPage<ReservationPageVO> listPagedReservations(ReservationPageQuery queryParams);


    /**
     * 获取预约记录表单数据
     *
     * @param id 预约记录ID
     * @return
     */
     ReservationForm getReservationFormData(Long id);


    /**
     * 新增预约记录
     *
     * @param formData 预约记录表单对象
     * @return
     */
    boolean saveReservation(ReservationForm formData);

    /**
     * 修改预约记录
     *
     * @param id   预约记录ID
     * @param formData 预约记录表单对象
     * @return
     */
    boolean updateReservation(Long id, ReservationForm formData);


    /**
     * 删除预约记录
     *
     * @param ids 预约记录ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteReservations(String ids);

    /** 获取指定教练的预约记录分页列表 */
    IPage<ReservationPageVO> listInstructorPagedReservations(ReservationPageQuery queryParams);

    /** 获取指定学员的预约记录分页列表 */
    IPage<ReservationPageVO> listStudentPagedReservations(ReservationPageQuery queryParams);

    boolean passReservation(Long id);

    boolean refuseReservation(Long id);

    boolean cancelReservation(Long id);

    ReservationVO getReservationDetail(Long id);

    List<ReservationVO> getReservationCalendar(ReservationCalendarQuery queryParams);
}
