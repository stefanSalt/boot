package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Clazz;
import com.lee.selection.system.mapper.ClazzMapper;
import com.lee.selection.system.service.ClazzService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.ClazzForm;
import com.lee.selection.system.model.query.ClazzPageQuery;
import com.lee.selection.system.model.bo.ClazzBO;
import com.lee.selection.system.model.vo.ClazzPageVO;
import com.lee.selection.system.converter.ClazzConverter;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 班级服务实现类
 *
 * @author baomidou
 * @since 2024-10-30
 */
@Service
@RequiredArgsConstructor
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    private final ClazzConverter clazzConverter;

    /**
    * 获取班级分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<ClazzPageVO>} 班级分页列表
    */
    @Override
    public IPage<ClazzPageVO> listPagedClazzs(ClazzPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<ClazzBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
       // DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<ClazzBO> boPage = this.baseMapper.listPagedClazzs(page, queryParams);
    
        // 实体转换
        return clazzConverter.toPageVo(boPage);
    }

    @Override
    public List<ClazzPageVO> listClazzs(ClazzPageQuery queryParams) {
        List<ClazzBO> clazzBOS = this.baseMapper.listClazzes(queryParams);
        return clazzBOS.stream().map(clazzConverter::toPageVo).toList();
    }

    /**
     * 获取班级表单数据
     *
     * @param id 班级ID
     * @return
     */
    @Override
    public ClazzForm getClazzFormData(Long id) {
        Clazz entity = this.getById(id);
        return clazzConverter.toForm(entity);
    }
    
    /**
     * 新增班级
     *
     * @param formData 班级表单对象
     * @return
     */
    @Override
    public boolean saveClazz(ClazzForm formData) {
        // 实体转换 form->entity
        Clazz entity = clazzConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新班级
     *
     * @param id   班级ID
     * @param formData 班级表单对象
     * @return
     */
    @Override
    public boolean updateClazz(Long id,ClazzForm formData) {
        Clazz entity = clazzConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除班级
     *
     * @param ids 班级ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteClazzs(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的班级数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
