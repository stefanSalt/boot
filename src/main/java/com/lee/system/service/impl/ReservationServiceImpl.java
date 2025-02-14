package com.lee.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.Reservation;
import com.lee.system.mapper.ReservationMapper;
import com.lee.system.service.ReservationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.system.model.entity.Reservation;

import java.util.Arrays;
import java.util.List;
/**
 * 预约记录表服务实现类
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class ReservationServiceImpl extends ServiceImpl<ReservationMapper, Reservation> implements ReservationService {


    /**
    * 获取预约记录表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Reservation>} 预约记录表分页列表
    */
    @Override
    public IPage<Reservation> listPagedReservations(Reservation queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Reservation> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Reservation> boPage = this.baseMapper.listPagedReservations(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取预约记录表表单数据
     *
     * @param id 预约记录表ID
     * @return
     */
    @Override
    public Reservation getReservationData(Long id) {
        Reservation entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增预约记录表
     *
     * @param formData 预约记录表表单对象
     * @return
     */
    @Override
    public boolean saveReservation(Reservation formData) {

        return this.save(formData);
    }
    
    /**
     * 更新预约记录表
     *
     * @param id   预约记录表ID
     * @param formData 预约记录表表单对象
     * @return
     */
    @Override
    public boolean updateReservation(Long id,Reservation formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除预约记录表
     *
     * @param ids 预约记录表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteReservations(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的预约记录表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
