package com.lee.selection.system.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.bo.VehicleTypeBO;
import com.lee.selection.system.model.entity.VehicleType;
import com.lee.selection.system.model.form.VehicleTypeForm;
import com.lee.selection.system.model.vo.VehicleTypePageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * 车辆类型转换器
 *
 * @author baomidou
 * @since 2024-10-22
 */
@Mapper(componentModel = "spring")
public interface VehicleTypeConverter{

    VehicleTypePageVO toPageVo(VehicleTypeBO bo);

    Page<VehicleTypePageVO> toPageVo(Page<VehicleTypeBO> bo);

    VehicleTypeForm toForm(VehicleType entity);

    @InheritInverseConfiguration(name = "toForm")
    VehicleType toEntity(VehicleTypeForm entity);
}