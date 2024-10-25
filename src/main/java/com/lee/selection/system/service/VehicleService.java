package com.lee.selection.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Vehicle;
import com.lee.selection.system.model.form.VehicleForm;
import com.lee.selection.system.model.query.VehiclePageQuery;
import com.lee.selection.system.model.vo.VehiclePageVO;
/**
 * 车辆信息 服务类
 *
 * @author baomidou
 * @since 2024-10-16
 */
public interface VehicleService extends IService<Vehicle> {


    /**
     *车辆信息分页列表
     *
     * @return
     */
    IPage<VehiclePageVO> listPagedVehicles(VehiclePageQuery queryParams);


    /**
     * 获取车辆信息表单数据
     *
     * @param id 车辆信息ID
     * @return
     */
     VehicleForm getVehicleFormData(Long id);


    /**
     * 新增车辆信息
     *
     * @param formData 车辆信息表单对象
     * @return
     */
    boolean saveVehicle(VehicleForm formData);

    /**
     * 修改车辆信息
     *
     * @param id   车辆信息ID
     * @param formData 车辆信息表单对象
     * @return
     */
    boolean updateVehicle(Long id, VehicleForm formData);


    /**
     * 删除车辆信息
     *
     * @param ids 车辆信息ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteVehicles(String ids);

}
