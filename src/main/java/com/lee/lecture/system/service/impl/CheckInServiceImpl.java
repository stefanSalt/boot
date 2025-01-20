package com.lee.lecture.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.CheckIn;
import com.lee.lecture.system.mapper.CheckInMapper;
import com.lee.lecture.system.service.CheckInService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.lecture.system.model.entity.CheckIn;

import java.util.Arrays;
import java.util.List;
/**
 * 讲座签到表服务实现类
 *
 * @author baomidou
 * @since 2025-01-08
 */
@Service
@RequiredArgsConstructor
public class CheckInServiceImpl extends ServiceImpl<CheckInMapper, CheckIn> implements CheckInService {


    /**
    * 获取讲座签到表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<CheckIn>} 讲座签到表分页列表
    */
    @Override
    public IPage<CheckIn> listPagedCheckIns(CheckIn queryParams, Integer pageNum, Integer pageSize) {
    

        Page<CheckIn> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<CheckIn> boPage = this.baseMapper.listPagedCheckIns(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取讲座签到表表单数据
     *
     * @param id 讲座签到表ID
     * @return
     */
    @Override
    public CheckIn getCheckInData(Long id) {
        CheckIn entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增讲座签到表
     *
     * @param formData 讲座签到表表单对象
     * @return
     */
    @Override
    public boolean saveCheckIn(CheckIn formData) {

        return this.save(formData);
    }
    
    /**
     * 更新讲座签到表
     *
     * @param id   讲座签到表ID
     * @param formData 讲座签到表表单对象
     * @return
     */
    @Override
    public boolean updateCheckIn(Long id,CheckIn formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除讲座签到表
     *
     * @param ids 讲座签到表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteCheckIns(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的讲座签到表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
