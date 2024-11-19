package com.lee.selection.system.mapper;

import com.lee.selection.system.model.entity.JobInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.system.model.entity.JobInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 *  Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-13
 */

@Mapper
public interface JobInfoMapper extends BaseMapper<JobInfo> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<JobInfo> listPagedJobInfos(Page<JobInfo> page, JobInfo queryParams);

    Page<JobInfo> listPagedJobInfosForApply(Page<JobInfo> page, JobInfo queryParams, Integer userId);
}
