package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.selection.common.exception.BusinessException;
import com.lee.selection.system.converter.ReservationConverter;
import com.lee.selection.system.mapper.ReservationMapper;
import com.lee.selection.system.model.bo.ReservationBO;
import com.lee.selection.system.model.entity.Instructor;
import com.lee.selection.system.model.entity.Reservation;
import com.lee.selection.system.model.entity.Student;
import com.lee.selection.system.model.form.ReservationForm;
import com.lee.selection.system.model.query.ReservationCalendarQuery;
import com.lee.selection.system.model.query.ReservationPageQuery;
import com.lee.selection.system.model.vo.ReservationPageVO;
import com.lee.selection.system.model.vo.ReservationVO;
import com.lee.selection.system.service.InstructorService;
import com.lee.selection.system.service.ReservationService;
import com.lee.selection.system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 预约记录服务实现类
 *
 * @author baomidou
 * @since 2024-10-17
 */
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {

    private final ReservationConverter reservationConverter;
    private final InstructorService instructorService;
//    private final CourseService courseService;
    private final StudentService studentService;

    /**
    * 获取预约记录分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<ReservationPageVO>} 预约记录分页列表
    */
    @Override
    public IPage<ReservationPageVO> listPagedReservations(ReservationPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<ReservationBO> page = new Page<>(pageNum, pageSize);



        // 查询数据
        Page<ReservationBO> boPage = this.baseMapper.listPagedReservations(page, queryParams);
    
        // 实体转换
        IPage<ReservationPageVO> convert = boPage.convert(reservationConverter::toPageVo);
        return convert;
    }

    @Override
    public IPage<ReservationPageVO> listInstructorPagedReservations(ReservationPageQuery queryParams) {

        Instructor currentInstructor = instructorService.getCurrentInstructor();
        if (currentInstructor == null){
            throw new BusinessException("当前用户不是教练，不能查看预约记录");
        }
        queryParams.setInstructorId(currentInstructor.getId());
        // 实体转换
        return listPagedReservations(queryParams);
    }

    @Override
    public IPage<ReservationPageVO> listStudentPagedReservations(ReservationPageQuery queryParams) {
        Student currentStudent = studentService.getCurrentStudent();
        if (currentStudent == null){
            throw new BusinessException("当前用户不是学员，不能查看预约记录");
        }
        queryParams.setStudentId(currentStudent.getId());
        return listPagedReservations(queryParams);
    }

    /**
     * 获取预约记录表单数据
     *
     * @param id 预约记录ID
     * @return
     */
    @Override
    public ReservationForm getReservationFormData(Long id) {
        Reservation entity = this.getById(id);
        return reservationConverter.toForm(entity);
    }
    
    /**
     * 新增预约记录
     *
     * @param formData 预约记录表单对象
     * @return
     */
    @Override
    public boolean saveReservation(ReservationForm formData) {
        // 实体转换 form->entity
        formData.setReservationTime(LocalDateTime.now());
        Reservation entity = reservationConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新预约记录
     *
     * @param id   预约记录ID
     * @param formData 预约记录表单对象
     * @return
     */
    @Override
    public boolean updateReservation(Long id,ReservationForm formData) {
        Reservation entity = reservationConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除预约记录
     *
     * @param ids 预约记录ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteReservations(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的预约记录数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public boolean passReservation(Long id) {
        Reservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException("预约记录不存在");
        }
        reservation.setStatus(1);
        return updateById(reservation);
    }

    @Override
    public boolean refuseReservation(Long id) {
        Reservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException("预约记录不存在");
        }
        reservation.setStatus(2);
        return updateById(reservation);
    }

    @Override
    public boolean cancelReservation(Long id) {
        Reservation reservation = getById(id);
        if (reservation == null) {
            throw new BusinessException("预约记录不存在");
        }
        reservation.setStatus(3);
        return updateById(reservation);
    }

    @Override
    public ReservationVO getReservationDetail(Long id) {
        Reservation entity = this.getById(id);
        if (entity == null){
            throw new BusinessException("预约记录不存在");
        }
        return reservationConverter.toVo(entity);
    }

    @Override
    public List<ReservationVO> getReservationCalendar(ReservationCalendarQuery queryParams) {
        QueryWrapper<Reservation> query = new QueryWrapper<>();
        query.eq("status",1);
        LocalDate date = queryParams.getSelectedDate();
        //查询当月记录
        LocalDate firstDayOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        query.between("start_time",firstDayOfMonth,lastDayOfMonth);
        Integer id = studentService.getCurrentStudent().getId();
        query.eq("student_id",id);
        List<Reservation> list = list(query);
        return list.stream().map(reservationConverter::toVo).collect(Collectors.toList());
    }
}
