package com.lee.reservation.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.reservation.common.constant.SystemConstant;
import com.lee.reservation.common.exception.BusinessException;
import com.lee.reservation.common.util.DateUtils;
import com.lee.reservation.common.util.SystemUtils;
import com.lee.reservation.system.converter.StudentConverter;
import com.lee.reservation.system.mapper.StudentMapper;
import com.lee.reservation.system.model.bo.StudentBO;
import com.lee.reservation.system.model.entity.Student;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.form.StudentForm;
import com.lee.reservation.system.model.option.StudentOption;
import com.lee.reservation.system.model.query.StudentPageQuery;
import com.lee.reservation.system.model.vo.ProfileVO;
import com.lee.reservation.system.model.vo.StudentPageVO;
import com.lee.reservation.system.model.vo.StudentVO;
import com.lee.reservation.system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 学员服务实现类
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Autowired
    private  StudentConverter studentConverter;
    @Autowired
    private StudentService studentService;

    /**
    * 获取学员分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<StudentPageVO>} 学员分页列表
    */
    @Override
    public IPage<StudentPageVO> listPagedStudents(StudentPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<StudentBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<StudentBO> boPage = this.baseMapper.listPagedStudents(page, queryParams);
    
        // 实体转换
        return studentConverter.toPageVo(boPage);
    }

    @Override
    public List<StudentOption> listStudents(StudentPageQuery queryParams) {
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","nickname");
        return this.list(queryWrapper).stream().map(studentConverter::toOption).toList();
    }

    /**
     * 获取学员表单数据
     *
     * @param id 学员ID
     * @return
     */
    @Override
    public StudentForm getStudentFormData(Long id) {
        Student entity = this.getById(id);
        return studentConverter.toForm(entity);
    }
    
    /**
     * 新增学员
     *
     * @param formData 学员表单对象
     * @return
     */
    @Override
    public boolean saveStudent(StudentForm formData) {
        // 实体转换 form->entity
        Student entity = studentConverter.toEntity(formData);
        String passwordMD5 = DigestUtils.md5DigestAsHex(SystemConstant.DEFAULT_PASSWORD.getBytes());
        entity.setPassword(SystemConstant.DEFAULT_PASSWORD);
        return this.save(entity);
    }
    
    /**
     * 更新学员
     *
     * @param id   学员ID
     * @param formData 学员表单对象
     * @return
     */
    @Override
    public boolean updateStudent(Long id,StudentForm formData) {
        Student entity = studentConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除学员
     *
     * @param ids 学员ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Transactional
    @Override
    public boolean deleteStudents(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的学员数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return studentService.removeByIds(idList);
    }


    @Override
    public void login(String username, String password) {
        QueryWrapper<Student> StudentQueryWrapper = new QueryWrapper<>();
        StudentQueryWrapper.eq("username",username)
                .eq("status",1);
        Student Student = this.getOne(StudentQueryWrapper);
       // String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5 = SystemConstant.DEFAULT_PASSWORD;
        if (Student == null|| !Student.getPassword().equals(passwordMD5)){
            throw new RuntimeException("用户名或密码错误");
        }
    }

    @Override
    public StudentVO getCurrentStudentInfo() {
        return studentConverter.toVo(getCurrentStudent());
    }

    @Override
    public Student getCurrentStudent() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Student> StudentQueryWrapper = new QueryWrapper<>();
        StudentQueryWrapper.eq("username",username);
        Student student = this.getOne(StudentQueryWrapper);
        if (student == null){
            throw new BusinessException("用户不存在");
        }
        return student;
    }

    @Override
    public ProfileVO getProfile() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Student> StudentQueryWrapper = new QueryWrapper<>();
        StudentQueryWrapper.eq("username",username);
        Student Student = this.getOne(StudentQueryWrapper);
        return studentConverter.toProfileVo(Student);
    }

    @Override
    public boolean updateProfile(ProfileForm formData) {
        String username = SystemUtils.getCurrentUsername();
        Student Student = this.getStudentByUsername(username);
        formData.setId(Student.getId());
        Student entity = studentConverter.toEntity(formData);
        return this.updateById(entity);
    }

    @Override
    public boolean resetPassword(Integer userId, String password) {
        String username = SystemUtils.getCurrentUsername();
        //String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5=password;
        return this.update(new LambdaUpdateWrapper<Student>()
                .eq(Student::getId, userId)
                .set(Student::getPassword, passwordMD5)
        );
    }

    @Override
    public boolean changePassword(PasswordChangeForm data) {
        String username = SystemUtils.getCurrentUsername();
        Student user = this.getStudentByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        String oldPassword = data.getOldPassword();
        String newPassword = data.getNewPassword();

        // 校验原密码
        if (!user.getPassword().equals(oldPassword)) {
            throw new BusinessException("原密码错误");
        }
        // 新旧密码不能相同
        if (oldPassword.equals(newPassword)) {
            throw new BusinessException("新密码不能与原密码相同");
        }


        boolean result= this.update(new LambdaUpdateWrapper<Student>()
                .eq(Student::getId, user.getId())
                .set(Student::getPassword, newPassword)
        );
        //todo 退出登录
//        if(result){
//            String token = SecurityUtils.getTokenFromRequest();
//            if (StrUtil.isNotBlank(token)) {
//                SecurityUtils.invalidateToken(token);
//            }
//        }
        return result;
    }


    public Student getStudentByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<Student>().eq(Student::getUsername, username));
    }

    @Override
    public String getStudentNameById(Integer id) {
        return getById(id).getNickname();
    }
}
