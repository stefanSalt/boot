package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.converter.VehicleTypeConverter;
import com.lee.selection.system.mapper.VehicleTypeMapper;
import com.lee.selection.system.model.bo.VehicleTypeBO;
import com.lee.selection.system.model.entity.VehicleType;
import com.lee.selection.system.model.form.VehicleTypeForm;
import com.lee.selection.system.model.query.VehicleTypePageQuery;
import com.lee.selection.system.model.vo.VehicleTypePageVO;
import com.lee.selection.system.service.VehicleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 车辆类型服务实现类
 *
 * @author baomidou
 * @since 2024-10-22
 */
@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl extends ServiceImpl<VehicleTypeMapper, VehicleType> implements VehicleTypeService {

    private final VehicleTypeConverter vehicleTypeConverter;

    /**
    * 获取车辆类型分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<VehicleTypePageVO>} 车辆类型分页列表
    */
    @Override
    public IPage<VehicleTypePageVO> listPagedVehicleTypes(VehicleTypePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<VehicleTypeBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<VehicleTypeBO> boPage = this.baseMapper.listPagedVehicleTypes(page, queryParams);
    
        // 实体转换
        return vehicleTypeConverter.toPageVo(boPage);
    }
    
    /**
     * 获取车辆类型表单数据
     *
     * @param id 车辆类型ID
     * @return
     */
    @Override
    public VehicleTypeForm getVehicleTypeFormData(Long id) {
        VehicleType entity = this.getById(id);
        return vehicleTypeConverter.toForm(entity);
    }
    
    /**
     * 新增车辆类型
     *
     * @param formData 车辆类型表单对象
     * @return
     */
    @Override
    public boolean saveVehicleType(VehicleTypeForm formData) {
        // 实体转换 form->entity
        VehicleType entity = vehicleTypeConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新车辆类型
     *
     * @param id   车辆类型ID
     * @param formData 车辆类型表单对象
     * @return
     */
    @Override
    public boolean updateVehicleType(Long id,VehicleTypeForm formData) {
        VehicleType entity = vehicleTypeConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除车辆类型
     *
     * @param ids 车辆类型ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteVehicleTypes(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的车辆类型数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
