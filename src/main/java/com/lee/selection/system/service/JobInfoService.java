package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.JobInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 *  服务类
 *
 * @author baomidou
 * @since 2024-11-13
 */
public interface JobInfoService extends IService<JobInfo> {


    /**
     *分页列表
     *
     * @return
     */
    IPage<JobInfo> listPagedJobInfos(JobInfo queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取表单数据
     *
     * @param id ID
     * @return
     */
     JobInfo getJobInfoData(Long id);


    /**
     * 新增
     *
     * @param formData 表单对象
     * @return
     */
    boolean saveJobInfo(JobInfo formData);

    /**
     * 修改
     *
     * @param id   ID
     * @param formData 表单对象
     * @return
     */
    boolean updateJobInfo(Long id, JobInfo formData);


    /**
     * 删除
     *
     * @param ids ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteJobInfos(String ids);

    IPage<JobInfo> listPagedJobInfosForApply(JobInfo queryParams, Integer userId, Integer pageNum, Integer pageSize);
}
