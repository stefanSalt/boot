package com.lee.system.service;

import com.lee.system.model.entity.ServiceInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.ServiceInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 服务表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface ServiceInfoService extends IService<ServiceInfo> {


    /**
     *服务表分页列表
     *
     * @return
     */
    IPage<ServiceInfo> listPagedServiceInfos(ServiceInfo queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取服务表表单数据
     *
     * @param id 服务表ID
     * @return
     */
     ServiceInfo getServiceInfoData(Long id);


    /**
     * 新增服务表
     *
     * @param formData 服务表表单对象
     * @return
     */
    boolean saveServiceInfo(ServiceInfo formData);

    /**
     * 修改服务表
     *
     * @param id   服务表ID
     * @param formData 服务表表单对象
     * @return
     */
    boolean updateServiceInfo(Long id, ServiceInfo formData);


    /**
     * 删除服务表
     *
     * @param ids 服务表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteServiceInfos(String ids);

}
