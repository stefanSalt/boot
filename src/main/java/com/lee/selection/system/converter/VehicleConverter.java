package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.VehicleBO;
import com.lee.selection.system.model.entity.Vehicle;
import com.lee.selection.system.model.form.VehicleForm;
import com.lee.selection.system.model.vo.VehiclePageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 车辆信息转换器
 *
 * @author baomidou
 * @since 2024-10-16
 */
@Mapper(componentModel = "spring")
public interface VehicleConverter{

    VehiclePageVO toPageVo(VehicleBO bo);

    Page<VehiclePageVO> toPageVo(Page<VehicleBO> bo);

    VehicleForm toForm(Vehicle entity);

    @InheritInverseConfiguration(name = "toForm")
    Vehicle toEntity(VehicleForm entity);
}