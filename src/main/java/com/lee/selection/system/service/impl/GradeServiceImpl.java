package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Grade;
import com.lee.selection.system.mapper.GradeMapper;
import com.lee.selection.system.service.GradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.GradeForm;
import com.lee.selection.system.model.query.GradePageQuery;
import com.lee.selection.system.model.bo.GradeBO;
import com.lee.selection.system.model.vo.GradePageVO;
import com.lee.selection.system.converter.GradeConverter;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 成绩服务实现类
 *
 * @author baomidou
 * @since 2024-11-01
 */
@Service
@RequiredArgsConstructor
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    private final GradeConverter gradeConverter;

    /**
    * 获取成绩分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<GradePageVO>} 成绩分页列表
    */
    @Override
    public IPage<GradePageVO> listPagedGrades(GradePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<GradeBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<GradeBO> boPage = this.baseMapper.listPagedGrades(page, queryParams);
    
        // 实体转换
        return gradeConverter.toPageVo(boPage);
    }
    
    /**
     * 获取成绩表单数据
     *
     * @param id 成绩ID
     * @return
     */
    @Override
    public GradeForm getGradeFormData(Long id) {
        Grade entity = this.getById(id);
        return gradeConverter.toForm(entity);
    }
    
    /**
     * 新增成绩
     *
     * @param formData 成绩表单对象
     * @return
     */
    @Override
    public boolean saveGrade(GradeForm formData) {
        // 实体转换 form->entity
        Grade entity = gradeConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新成绩
     *
     * @param id   成绩ID
     * @param formData 成绩表单对象
     * @return
     */
    @Override
    public boolean updateGrade(Long id,GradeForm formData) {
        Grade entity = gradeConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除成绩
     *
     * @param ids 成绩ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteGrades(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的成绩数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
