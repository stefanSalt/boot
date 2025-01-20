package com.lee.lecture.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.mapper.LectureMapper;
import com.lee.lecture.system.model.entity.Reservation;
import com.lee.lecture.system.mapper.ReservationMapper;
import com.lee.lecture.system.service.ReservationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.lecture.system.model.entity.Reservation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
/**
 * 讲座预约表服务实现类
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {


    /**
    * 获取讲座预约表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Reservation>} 讲座预约表分页列表
    */
    @Override
    public IPage<Reservation> listPagedReservations(Reservation queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Reservation> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Reservation> boPage = this.baseMapper.listPagedReservations(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取讲座预约表表单数据
     *
     * @param id 讲座预约表ID
     * @return
     */
    @Override
    public Reservation getReservationData(Long id) {
        Reservation entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增讲座预约表
     *
     * @param formData 讲座预约表表单对象
     * @return
     */
    @Override

    public boolean saveReservation(Reservation formData) {

        return this.save(formData);
    }
    
    /**
     * 更新讲座预约表
     *
     * @param id   讲座预约表ID
     * @param formData 讲座预约表表单对象
     * @return
     */
    @Override
    public boolean updateReservation(Long id,Reservation formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除讲座预约表
     *
     * @param ids 讲座预约表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteReservations(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的讲座预约表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
