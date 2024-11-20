package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.entity.JobInfo;
import com.lee.selection.system.mapper.JobInfoMapper;
import com.lee.selection.system.model.vo.JobInfoVO;
import com.lee.selection.system.service.JobInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 服务实现类
 *
 * @author baomidou
 * @since 2024-11-13
 */
@Service
@RequiredArgsConstructor
public class JobInfoServiceImpl extends ServiceImpl<JobInfoMapper, JobInfo> implements JobInfoService {


    /**
     * 获取分页列表
     *
     * @param queryParams 查询参数
     * @param pageNum     页号
     * @param pageSize    页大小
     * @return {@link IPage<JobInfo>} 分页列表
     */
    @Override
    public Page<JobInfoVO> listPagedJobInfos(JobInfo queryParams, Integer pageNum, Integer pageSize) {

        Page<JobInfo> page = new Page<>(pageNum, pageSize);
    
        // 查询数据
        Page<JobInfoVO> boPage = this.baseMapper.listPagedJobInfos(page, queryParams);

        return boPage;
    }

    @Override
    public IPage<JobInfo> listPagedJobInfosForApply(JobInfo queryParams, Integer userId, Integer pageNum, Integer pageSize) {
        Page<JobInfo> page = new Page<>(pageNum, pageSize);
        Page<JobInfo> boPage = this.baseMapper.listPagedJobInfosForApply(page, queryParams,userId);
        return boPage;
    }

    /**
     * 获取表单数据
     *
     * @param id ID
     * @return
     */
    @Override
    public JobInfo getJobInfoData(Long id) {
        JobInfo entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增
     *
     * @param formData 表单对象
     * @return
     */
    @Override
    public boolean saveJobInfo(JobInfo formData) {

        return this.save(formData);
    }
    
    /**
     * 更新
     *
     * @param id   ID
     * @param formData 表单对象
     * @return
     */
    @Override
    public boolean updateJobInfo(Long id,JobInfo formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除
     *
     * @param ids ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteJobInfos(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
