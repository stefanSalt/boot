package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Company;
import com.lee.selection.system.mapper.CompanyMapper;
import com.lee.selection.system.service.CompanyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.entity.Company;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 企业服务实现类
 *
 * @author baomidou
 * @since 2024-11-12
 */
@Service
@RequiredArgsConstructor
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {


    /**
    * 获取企业分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Company>} 企业分页列表
    */
    @Override
    public IPage<Company> listPagedCompanys(Company queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Company> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Company> boPage = this.baseMapper.listPagedCompanys(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取企业表单数据
     *
     * @param id 企业ID
     * @return
     */
    @Override
    public Company getCompanyData(Long id) {
        Company entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增企业
     *
     * @param formData 企业表单对象
     * @return
     */
    @Override
    public boolean saveCompany(Company formData) {

        return this.save(formData);
    }
    
    /**
     * 更新企业
     *
     * @param id   企业ID
     * @param formData 企业表单对象
     * @return
     */
    @Override
    public boolean updateCompany(Long id,Company formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除企业
     *
     * @param ids 企业ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteCompanys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的企业数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public List<Company> listOption() {
        LambdaQueryWrapper<Company> companyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        companyLambdaQueryWrapper.select(Company::getId,Company::getName);
        return this.list(companyLambdaQueryWrapper);
    }
}
