package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Major;
import com.lee.selection.system.mapper.MajorMapper;
import com.lee.selection.system.model.vo.MajorVO;
import com.lee.selection.system.service.MajorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.MajorForm;
import com.lee.selection.system.model.query.MajorPageQuery;
import com.lee.selection.system.model.bo.MajorBO;
import com.lee.selection.system.model.vo.MajorPageVO;
import com.lee.selection.system.converter.MajorConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 专业信息表服务实现类
 *
 * @author baomidou
 * @since 2024-10-25
 */
@Service
@RequiredArgsConstructor
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    private final MajorConverter majorConverter;

    /**
    * 获取专业信息表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<MajorPageVO>} 专业信息表分页列表
    */
    @Override
    public IPage<MajorPageVO> listPagedMajors(MajorPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<MajorBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<MajorBO> boPage = this.baseMapper.listPagedMajors(page, queryParams);
    
        // 实体转换
        return majorConverter.toPageVo(boPage);
    }

    @Override
    public List<MajorVO> listMajors(MajorPageQuery queryParams) {
        List<MajorBO> list = this.baseMapper.listMajors(queryParams);
        List<MajorVO> collect = list.stream().map(majorConverter::toVO).collect(Collectors.toList());
        return streamToTree(collect,0);
    }

    /**
     * 将专业列表转换为树形结构
     *
     * @param treeList 专业列表，每个专业都是一个节点，包括根节点和子节点
     * @param parentId 父节点的ID，用于筛选根节点
     * @return 返回转换成树形结构后的专业列表
     */
    private List<MajorVO> streamToTree(List<MajorVO> treeList, Integer parentId) {
        return treeList.stream()
                // 过滤出父节点ID匹配的节点，这是构建树形结构的第一步
                .filter(parent -> Objects.equals(parent.getParentId(), parentId))
                // 对每个匹配的父节点，递归地设置其子节点，构建树形结构
                .map(child -> {
                    child.setChildren(streamToTree(treeList,child.getId()));
                    return child;
                }).collect(Collectors.toList());
    }





    /**
     * 获取专业信息表表单数据
     *
     * @param id 专业信息表ID
     * @return
     */
    @Override
    public MajorForm getMajorFormData(Long id) {
        Major entity = this.getById(id);
        return majorConverter.toForm(entity);
    }
    
    /**
     * 新增专业信息表
     *
     * @param formData 专业信息表表单对象
     * @return
     */
    @Override
    public boolean saveMajor(MajorForm formData) {
        // 实体转换 form->entity
        Major entity = majorConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新专业信息表
     *
     * @param id   专业信息表ID
     * @param formData 专业信息表表单对象
     * @return
     */
    @Override
    public boolean updateMajor(Long id,MajorForm formData) {
        Major entity = majorConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除专业信息表
     *
     * @param ids 专业信息表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteMajors(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的专业信息表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    

}
