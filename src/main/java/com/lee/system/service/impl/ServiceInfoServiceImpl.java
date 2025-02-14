package com.lee.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.system.model.entity.ServiceInfo;
import com.lee.system.mapper.ServiceInfoMapper;
import com.lee.system.service.ServiceInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.system.model.entity.ServiceInfo;

import java.util.Arrays;
import java.util.List;
/**
 * 服务表服务实现类
 *
 * @author baomidou
 * @since 2025-02-10
 */
@Service
@RequiredArgsConstructor
public class ServiceInfoServiceImpl extends ServiceImpl<ServiceInfoMapper, ServiceInfo> implements ServiceInfoService {


    /**
    * 获取服务表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<ServiceInfo>} 服务表分页列表
    */
    @Override
    public IPage<ServiceInfo> listPagedServiceInfos(ServiceInfo queryParams, Integer pageNum, Integer pageSize) {
    

        Page<ServiceInfo> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<ServiceInfo> boPage = this.baseMapper.listPagedServiceInfos(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取服务表表单数据
     *
     * @param id 服务表ID
     * @return
     */
    @Override
    public ServiceInfo getServiceInfoData(Long id) {
        ServiceInfo entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增服务表
     *
     * @param formData 服务表表单对象
     * @return
     */
    @Override
    public boolean saveServiceInfo(ServiceInfo formData) {

        return this.save(formData);
    }
    
    /**
     * 更新服务表
     *
     * @param id   服务表ID
     * @param formData 服务表表单对象
     * @return
     */
    @Override
    public boolean updateServiceInfo(Long id,ServiceInfo formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除服务表
     *
     * @param ids 服务表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteServiceInfos(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的服务表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
