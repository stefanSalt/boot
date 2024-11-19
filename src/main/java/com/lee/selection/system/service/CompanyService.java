package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Company;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Company;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 企业 服务类
 *
 * @author baomidou
 * @since 2024-11-12
 */
public interface CompanyService extends IService<Company> {


    /**
     *企业分页列表
     *
     * @return
     */
    IPage<Company> listPagedCompanys(Company queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取企业表单数据
     *
     * @param id 企业ID
     * @return
     */
     Company getCompanyData(Long id);


    /**
     * 新增企业
     *
     * @param formData 企业表单对象
     * @return
     */
    boolean saveCompany(Company formData);

    /**
     * 修改企业
     *
     * @param id   企业ID
     * @param formData 企业表单对象
     * @return
     */
    boolean updateCompany(Long id, Company formData);


    /**
     * 删除企业
     *
     * @param ids 企业ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCompanys(String ids);

    List<Company> listOption();
}
