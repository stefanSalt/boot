package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.mapper.RoleMapper;
import com.lee.selection.system.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.RoleForm;
import com.lee.selection.system.model.query.RolePageQuery;
import com.lee.selection.system.model.bo.RoleBO;
import com.lee.selection.system.model.vo.RolePageVO;
import com.lee.selection.system.converter.RoleConverter;

import java.util.Arrays;
import java.util.List;

/**
 * 角色信息表服务实现类
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    private final RoleConverter roleConverter;

    /**
    * 获取角色信息表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<RolePageVO>} 角色信息表分页列表
    */
    @Override
    public IPage<RolePageVO> listPagedRoles(RolePageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<RoleBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<RoleBO> boPage = this.baseMapper.listPagedRoles(page, queryParams);
    
        // 实体转换
        return roleConverter.toPageVo(boPage);
    }
    
    /**
     * 获取角色信息表表单数据
     *
     * @param id 角色信息表ID
     * @return
     */
    @Override
    public RoleForm getRoleFormData(Long id) {
        Role entity = this.getById(id);
        return roleConverter.toForm(entity);
    }
    
    /**
     * 新增角色信息表
     *
     * @param formData 角色信息表表单对象
     * @return
     */
    @Override
    public boolean saveRole(RoleForm formData) {
        // 实体转换 form->entity
        Role entity = roleConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新角色信息表
     *
     * @param id   角色信息表ID
     * @param formData 角色信息表表单对象
     * @return
     */
    @Override
    public boolean updateRole(Long id,RoleForm formData) {
        Role entity = roleConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除角色信息表
     *
     * @param ids 角色信息表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteRoles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的角色信息表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
