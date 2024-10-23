package com.lee.reservation.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.reservation.system.model.entity.Student;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.form.StudentForm;
import com.lee.reservation.system.model.option.StudentOption;
import com.lee.reservation.system.model.query.StudentPageQuery;
import com.lee.reservation.system.model.vo.ProfileVO;
import com.lee.reservation.system.model.vo.StudentPageVO;
import com.lee.reservation.system.model.vo.StudentVO;

import java.util.List;

/**
 * 学员 服务类
 *
 * @author baomidou
 * @since 2024-10-14
 */
public interface StudentService extends IService<Student> {


    /**
     *分页列表
     *
     * @return
     */
    IPage<StudentPageVO> listPagedStudents(StudentPageQuery queryParams);


    /**
     * 获取学员表单数据
     *
     * @param id 管理员ID
     * @return
     */
     StudentForm getStudentFormData(Long id);


    /**
     * 新增学员
     *
     * @param formData 学员表单对象
     * @return
     */
    boolean saveStudent(StudentForm formData);

    /**
     * 修改学员
     *
     * @param id   学员ID
     * @param formData 学员表单对象
     * @return
     */
    boolean updateStudent(Long id, StudentForm formData);


    /**
     * 删除学员
     *
     * @param ids 学员ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteStudents(String ids);

    void login(String username, String password);

    StudentVO getCurrentStudentInfo();
    Student getCurrentStudent();

    ProfileVO getProfile();

    boolean updateProfile(ProfileForm formData);

    boolean resetPassword(Integer userId, String password);

    boolean changePassword(PasswordChangeForm data);

    String getStudentNameById(Integer id);


    List<StudentOption> listStudents(StudentPageQuery queryParams);
}
