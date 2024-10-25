package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.RoleForm;
import com.lee.selection.system.model.query.RolePageQuery;
import com.lee.selection.system.model.vo.RolePageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 角色信息表 服务类
 *
 * @author baomidou
 * @since 2024-10-24
 */
public interface RoleService extends IService<Role> {


    /**
     *角色信息表分页列表
     *
     * @return
     */
    IPage<RolePageVO> listPagedRoles(RolePageQuery queryParams);


    /**
     * 获取角色信息表表单数据
     *
     * @param id 角色信息表ID
     * @return
     */
     RoleForm getRoleFormData(Long id);


    /**
     * 新增角色信息表
     *
     * @param formData 角色信息表表单对象
     * @return
     */
    boolean saveRole(RoleForm formData);

    /**
     * 修改角色信息表
     *
     * @param id   角色信息表ID
     * @param formData 角色信息表表单对象
     * @return
     */
    boolean updateRole(Long id, RoleForm formData);


    /**
     * 删除角色信息表
     *
     * @param ids 角色信息表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteRoles(String ids);

}
