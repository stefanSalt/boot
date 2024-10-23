package com.lee.reservation.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.reservation.system.model.entity.Instructor;
import com.lee.reservation.system.model.form.InstructorForm;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.option.InstructorOption;
import com.lee.reservation.system.model.query.InstructorPageQuery;
import com.lee.reservation.system.model.vo.InstructorPageVO;
import com.lee.reservation.system.model.vo.InstructorVO;
import com.lee.reservation.system.model.vo.ProfileVO;

import java.util.List;

/**
 * 教练 服务类
 *
 * @author baomidou
 * @since 2024-10-14
 */
public interface InstructorService extends IService<Instructor> {


    /**
     *教练分页列表
     *
     * @return
     */
    IPage<InstructorPageVO> listPagedInstructors(InstructorPageQuery queryParams);


    /**
     * 获取教练表单数据
     *
     * @param id 教练ID
     * @return
     */
     InstructorForm getInstructorFormData(Long id);


    /**
     * 新增教练
     *
     * @param formData 教练表单对象
     * @return
     */
    boolean saveInstructor(InstructorForm formData);

    /**
     * 修改教练
     *
     * @param id   教练ID
     * @param formData 教练表单对象
     * @return
     */
    boolean updateInstructor(Long id, InstructorForm formData);


    /**
     * 删除教练
     *
     * @param ids 教练ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteInstructors(String ids);

    void login(String username, String password);

    InstructorVO getCurrentInstructorInfo();

    Instructor getCurrentInstructor();

    ProfileVO getProfile();

    boolean updateProfile(ProfileForm formData);

    boolean resetPassword(Integer userId, String password);

    boolean changePassword(PasswordChangeForm data);

    List<InstructorOption> listInstructors(InstructorPageQuery queryParams);

    String getInstructorNameById(Integer id);
}
