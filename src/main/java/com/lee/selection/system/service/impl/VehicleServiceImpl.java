package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.converter.VehicleConverter;
import com.lee.selection.system.mapper.VehicleMapper;
import com.lee.selection.system.model.bo.VehicleBO;
import com.lee.selection.system.model.entity.Vehicle;
import com.lee.selection.system.model.form.VehicleForm;
import com.lee.selection.system.model.query.VehiclePageQuery;
import com.lee.selection.system.model.vo.VehiclePageVO;
import com.lee.selection.system.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 车辆信息服务实现类
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Service
@RequiredArgsConstructor
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle> implements VehicleService {

    private final VehicleConverter vehicleConverter;

    /**
    * 获取车辆信息分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<VehiclePageVO>} 车辆信息分页列表
    */
    @Override
    public IPage<VehiclePageVO> listPagedVehicles(VehiclePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<VehicleBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<VehicleBO> boPage = this.baseMapper.listPagedVehicles(page, queryParams);
    
        // 实体转换
        return vehicleConverter.toPageVo(boPage);
    }
    
    /**
     * 获取车辆信息表单数据
     *
     * @param id 车辆信息ID
     * @return
     */
    @Override
    public VehicleForm getVehicleFormData(Long id) {
        Vehicle entity = this.getById(id);
        return vehicleConverter.toForm(entity);
    }
    
    /**
     * 新增车辆信息
     *
     * @param formData 车辆信息表单对象
     * @return
     */
    @Override
    public boolean saveVehicle(VehicleForm formData) {
        // 实体转换 form->entity
        Vehicle entity = vehicleConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新车辆信息
     *
     * @param id   车辆信息ID
     * @param formData 车辆信息表单对象
     * @return
     */
    @Override
    public boolean updateVehicle(Long id,VehicleForm formData) {
        Vehicle entity = vehicleConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除车辆信息
     *
     * @param ids 车辆信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteVehicles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的车辆信息数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
