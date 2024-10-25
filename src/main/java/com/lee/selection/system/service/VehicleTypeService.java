package com.lee.selection.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.VehicleType;
import com.lee.selection.system.model.form.VehicleTypeForm;
import com.lee.selection.system.model.query.VehicleTypePageQuery;
import com.lee.selection.system.model.vo.VehicleTypePageVO;
/**
 * 车辆类型 服务类
 *
 * @author baomidou
 * @since 2024-10-22
 */
public interface VehicleTypeService extends IService<VehicleType> {


    /**
     *车辆类型分页列表
     *
     * @return
     */
    IPage<VehicleTypePageVO> listPagedVehicleTypes(VehicleTypePageQuery queryParams);


    /**
     * 获取车辆类型表单数据
     *
     * @param id 车辆类型ID
     * @return
     */
     VehicleTypeForm getVehicleTypeFormData(Long id);


    /**
     * 新增车辆类型
     *
     * @param formData 车辆类型表单对象
     * @return
     */
    boolean saveVehicleType(VehicleTypeForm formData);

    /**
     * 修改车辆类型
     *
     * @param id   车辆类型ID
     * @param formData 车辆类型表单对象
     * @return
     */
    boolean updateVehicleType(Long id, VehicleTypeForm formData);


    /**
     * 删除车辆类型
     *
     * @param ids 车辆类型ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteVehicleTypes(String ids);

}
