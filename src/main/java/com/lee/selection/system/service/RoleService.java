package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Role;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 角色 服务类
 *
 * @author baomidou
 * @since 2024-11-07
 */
public interface RoleService extends IService<Role> {


    /**
     *角色分页列表
     *
     * @return
     */
    IPage<Role> listPagedRoles(Role queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取角色表单数据
     *
     * @param id 角色ID
     * @return
     */
     Role getRoleData(Long id);


    /**
     * 新增角色
     *
     * @param formData 角色表单对象
     * @return
     */
    boolean saveRole(Role formData);

    /**
     * 修改角色
     *
     * @param id   角色ID
     * @param formData 角色表单对象
     * @return
     */
    boolean updateRole(Long id, Role formData);


    /**
     * 删除角色
     *
     * @param ids 角色ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteRoles(String ids);

}
