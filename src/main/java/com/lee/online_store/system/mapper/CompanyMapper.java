package com.lee.online_store.system.mapper;

import com.lee.online_store.system.model.entity.Company;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.entity.Company;
import org.apache.ibatis.annotations.Mapper;

/**
 * 企业 Mapper 接口
 *
 * @author baomidou
 * @since 2024-11-12
 */

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Company> listPagedCompanys(Page<Company> page, Company queryParams);

}
