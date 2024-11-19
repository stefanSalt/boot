package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Recruiter;
import com.lee.selection.system.mapper.RecruiterMapper;
import com.lee.selection.system.service.RecruiterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.entity.Recruiter;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 招聘者服务实现类
 *
 * @author baomidou
 * @since 2024-11-08
 */
@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl extends ServiceImpl<RecruiterMapper, Recruiter> implements RecruiterService {


    /**
    * 获取招聘者分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Recruiter>} 招聘者分页列表
    */
    @Override
    public IPage<Recruiter> listPagedRecruiters(Recruiter queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Recruiter> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Recruiter> boPage = this.baseMapper.listPagedRecruiters(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取招聘者表单数据
     *
     * @param id 招聘者ID
     * @return
     */
    @Override
    public Recruiter getRecruiterData(Long id) {
        Recruiter entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增招聘者
     *
     * @param formData 招聘者表单对象
     * @return
     */
    @Override
    public boolean saveRecruiter(Recruiter formData) {

        return this.save(formData);
    }
    
    /**
     * 更新招聘者
     *
     * @param id   招聘者ID
     * @param formData 招聘者表单对象
     * @return
     */
    @Override
    public boolean updateRecruiter(Long id,Recruiter formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除招聘者
     *
     * @param ids 招聘者ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteRecruiters(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的招聘者数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
