package com.lee.reservation.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.reservation.system.model.entity.Admin;
import com.lee.reservation.system.model.form.AdminForm;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.query.AdminPageQuery;
import com.lee.reservation.system.model.vo.AdminPageVO;
import com.lee.reservation.system.model.vo.AdminVO;
import com.lee.reservation.system.model.vo.ProfileVO;

/**
 * 管理员 服务类
 *
 * @author baomidou
 * @since 2024-10-14
 */
public interface AdminService extends IService<Admin> {


    /**
     *管理员分页列表
     *
     * @return
     */
    IPage<AdminPageVO> listPagedAdmins(AdminPageQuery queryParams);


    /**
     * 获取管理员表单数据
     *
     * @param id 管理员ID
     * @return
     */
     AdminForm getAdminFormData(Long id);


    /**
     * 新增管理员
     *
     * @param formData 管理员表单对象
     * @return
     */
    boolean saveAdmin(AdminForm formData);

    /**
     * 修改管理员
     *
     * @param id   管理员ID
     * @param formData 管理员表单对象
     * @return
     */
    boolean updateAdmin(Long id, AdminForm formData);


    /**
     * 删除管理员
     *
     * @param ids 管理员ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteAdmins(String ids);

    void login(String username, String password);

    AdminVO getCurrentAdminInfo();


    ProfileVO getProfile();

    boolean updateProfile(ProfileForm formData);

    boolean resetPassword(Integer userId, String password);

    boolean changePassword(PasswordChangeForm data);

    String  getAdminName(Integer userId);
}
