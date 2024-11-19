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
import com.lee.selection.system.model.entity.Role;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 角色服务实现类
 *
 * @author baomidou
 * @since 2024-11-07
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {


    /**
    * 获取角色分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Role>} 角色分页列表
    */
    @Override
    public IPage<Role> listPagedRoles(Role queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Role> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<Role> boPage = this.baseMapper.listPagedRoles(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取角色表单数据
     *
     * @param id 角色ID
     * @return
     */
    @Override
    public Role getRoleData(Long id) {
        Role entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增角色
     *
     * @param formData 角色表单对象
     * @return
     */
    @Override
    public boolean saveRole(Role formData) {

        return this.save(formData);
    }
    
    /**
     * 更新角色
     *
     * @param id   角色ID
     * @param formData 角色表单对象
     * @return
     */
    @Override
    public boolean updateRole(Long id,Role formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除角色
     *
     * @param ids 角色ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteRoles(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的角色数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
