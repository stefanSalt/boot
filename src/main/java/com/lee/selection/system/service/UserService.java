package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.form.UserForm;
import com.lee.selection.system.model.option.UserOption;
import com.lee.selection.system.model.query.UserPageQuery;
import com.lee.selection.system.model.vo.UserPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;

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
    IPage<UserPageVO> listPagedUsers(UserPageQuery queryParams);


    /**
     * 获取用户表单数据
     *
     * @param id 用户ID
     * @return
     */
     UserForm getUserFormData(Long id);


    /**
     * 新增用户
     *
     * @param formData 用户表单对象
     * @return
     */
    boolean saveUser(UserForm formData);

    /**
     * 修改用户
     *
     * @param id   用户ID
     * @param formData 用户表单对象
     * @return
     */
    boolean updateUser(Long id, UserForm formData);


    /**
     * 删除用户
     *
     * @param ids 用户ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String ids);

    UserVO getCurrentUserInfo();

    User getCurrentUser();

    String getUserName(Integer id);

    UserProfileVO getProfile();

    boolean updateProfile(ProfileForm formData);

    List<UserOption> getOptions(Integer roleId);

    List<UserOption> getTeachersByCourseId(Integer courseId);

    boolean resetPassword(Integer userId, String password);
}
