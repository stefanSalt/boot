package com.lee.questionnaire.system.service;

import com.lee.questionnaire.system.model.dto.ChangePasswordDTO;
import com.lee.questionnaire.system.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

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
     User getUserData(Long id);


    /**
     * 新增用户
     *
     * @param formData 用户表单对象
     * @return
     */
    boolean saveUser(User formData);

    /**
     * 修改用户
     *
     * @param id   用户ID
     * @param formData 用户表单对象
     * @return
     */
    boolean updateUser(Long id, User formData);


    /**
     * 删除用户
     *
     * @param ids 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String ids);



    String getUserName(Integer id);


    boolean updateProfile(User formData);
    

    boolean resetPassword(Integer userId, String password);

    User getUserByUsername(String username);

    boolean updatePassword(ChangePasswordDTO changePasswordDTO);

    List<User> listUserOptions(String role);
}
