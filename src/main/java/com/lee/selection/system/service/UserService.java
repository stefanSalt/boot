package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.form.UserForm;
import com.lee.selection.system.model.query.UserPageQuery;
import com.lee.selection.system.model.vo.UserPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;

/**
 * 包含  管理员  教师  学生等 服务类
 *
 * @author baomidou
 * @since 2024-10-24
 */
public interface UserService extends IService<User> {


    /**
     *包含  管理员  教师  学生等分页列表
     *
     * @return
     */
    IPage<UserPageVO> listPagedUsers(UserPageQuery queryParams);


    /**
     * 获取包含  管理员  教师  学生等表单数据
     *
     * @param id 包含  管理员  教师  学生等ID
     * @return
     */
     UserForm getUserFormData(Long id);


    /**
     * 新增包含  管理员  教师  学生等
     *
     * @param formData 包含  管理员  教师  学生等表单对象
     * @return
     */
    boolean saveUser(UserForm formData);

    /**
     * 修改包含  管理员  教师  学生等
     *
     * @param id   包含  管理员  教师  学生等ID
     * @param formData 包含  管理员  教师  学生等表单对象
     * @return
     */
    boolean updateUser(Long id, UserForm formData);


    /**
     * 删除包含  管理员  教师  学生等
     *
     * @param ids 包含  管理员  教师  学生等ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteUsers(String ids);

    UserVO getCurrentUserInfo();

    User getCurrentUser();

    String getUserName(Integer id);

    UserProfileVO getProfile();

    boolean updateProfile(ProfileForm formData);
}
