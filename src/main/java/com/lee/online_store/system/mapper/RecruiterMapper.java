package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.entity.Recruiter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.Recruiter;
import org.apache.ibatis.annotations.Mapper;

/**
 * 招聘者 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-08
 */

@Mapper
public interface RecruiterMapper extends BaseMapper<Recruiter> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Recruiter> listPagedRecruiters(Page<Recruiter> page, Recruiter queryParams);

}
