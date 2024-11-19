package com.lee.selection.system.service;

import com.lee.selection.system.model.dto.ChangePasswordDTO;
import com.lee.selection.system.model.dto.UserDTO;
import com.lee.selection.system.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * 用户 服务类
 *
 * @author baomidou
 * @since 2024-10-24
 */
public interface UserService extends IService<User> {


    /**
     *用户分页列表
     *
     * @return
     */
    IPage listPagedUsers(User queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取用户表单数据
     *
     * @param id 用户ID
     * @return
     */
     UserDTO getUserData(Long id);


    /**
     * 新增用户
     *
     * @param formData 用户表单对象
     * @return
     */
    boolean saveUser(UserDTO formData);

    /**
     * 修改用户
     *
     * @param id   用户ID
     * @param formData 用户表单对象
     * @return
     */
    boolean updateUser(Long id, UserDTO formData);


    /**
     * 删除用户
     *
     * @param ids 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String ids);



    String getUserName(Integer id);


    boolean updateProfile(UserDTO formData);
    

    boolean resetPassword(Integer userId, String password);

    UserDTO getUserByUsername(String username);

    boolean updatePassword(ChangePasswordDTO changePasswordDTO);
}
