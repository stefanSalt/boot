package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.constant.SystemConstant;
import com.lee.selection.system.mapper.RecruiterMapper;
import com.lee.selection.system.mapper.RoleMapper;
import com.lee.selection.system.mapper.StudentMapper;
import com.lee.selection.system.model.dto.ChangePasswordDTO;
import com.lee.selection.system.model.dto.UserDTO;
import com.lee.selection.system.model.entity.Recruiter;
import com.lee.selection.system.model.entity.Role;
import com.lee.selection.system.model.entity.Student;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.mapper.UserMapper;
import com.lee.selection.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 用户服务实现类
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    private final RoleMapper roleMapper;

    private final StudentMapper studentMapper;

    private final RecruiterMapper recruiterMapper;

    /**
    * 获取用户分页列表
    *
    * @param queryParams 查询参数
    * @return 用户分页列表
    */
    @Override
    public IPage<UserDTO> listPagedUsers(User queryParams, Integer pageNum, Integer pageSize) {
    

        Page<User> page = new Page<>(pageNum, pageSize);

        // 查询数据
        Page<User> userPage = this.baseMapper.listPagedUsers(page, queryParams);
        IPage<UserDTO> dtoPage = userPage.convert(this::toDTO);

        // 实体转换
        return dtoPage;
    }
    
    /**
     * 获取用户表单数据
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public UserDTO getUserData(Long id) {
        User user = this.getById(id);
        return toDTO(user);
    }
    public UserDTO toDTO(User user){
        UserDTO entity = new UserDTO(user);
        if (entity.getRoleId()==2){
            entity.setRecruiter(recruiterMapper.selectOne(new LambdaUpdateWrapper<Recruiter>().eq(Recruiter::getUserId,entity.getId())));
        } else if (entity.getRoleId()==3){
            entity.setStudent(studentMapper.selectOne(new LambdaUpdateWrapper<Student>().eq(Student::getUserId,entity.getId())));
        }
        return entity;
    }
    
    /**
     * 新增用户
     *
     * @param formData 用户表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean saveUser(UserDTO formData) {
        formData.setPassword(SystemConstant.DEFAULT_PASSWORD);
        formData.setAvatar(SystemConstant.DEFAULT_AVATAR);
        formData.setStatus(1);
        this.save(formData);
        if (formData.getRoleId()==2){
            Recruiter recruiter = formData.getRecruiter();
            recruiter.setUserId(formData.getId());
            recruiterMapper.insert(recruiter);
        }
        if (formData.getRoleId()==3){
            Student student = formData.getStudent();
            student.setUserId(formData.getId());
            studentMapper.insert(student);
        }
        return true;
    }
    
    /**
     * 更新用户
     *
     * @param id   用户ID
     * @param formData 用户表单对象
     * @return
     */
    @Override
    @Transactional
    public boolean updateUser(Long id,UserDTO formData) {
        if (formData.getRoleId()==2){
            recruiterMapper.updateById(formData.getRecruiter());
        }
        if (formData.getRoleId()==3){
            studentMapper.updateById(formData.getStudent());
        }
        return this.updateById(formData);
    }
    
    /**
     * 删除用户
     *
     * @param ids 用户ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    @Transactional
    public boolean deleteUsers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的用户数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }




    @Override
    public String getUserName(Integer id) {
        return this.getById(id).getUsername();
    }



    @Override
    @Transactional
    public boolean updateProfile(UserDTO formData) {
        if (formData.getRoleId()==2){
            recruiterMapper.updateById(formData.getRecruiter());
        }
        if (formData.getRoleId()==3){
            studentMapper.updateById(formData.getStudent());
        }
        return this.updateById(formData);
    }


    @Override
    public boolean resetPassword(Integer userId, String password) {
        return this.update(new LambdaUpdateWrapper<User>()
                .eq(User::getId, userId)
                .set(User::getPassword, password)
        );
    }


    @Override
    public UserDTO getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = this.getOne(queryWrapper);
        Role role = roleMapper.selectById(user.getRoleId());
        user.setRole(role.getName());
        return toDTO(user);
    }

    @Override
    public boolean updatePassword(ChangePasswordDTO changePasswordDTO) {
        Integer id = changePasswordDTO.getId();
        String oldPassword = changePasswordDTO.getOldPassword();
        String newPassword = changePasswordDTO.getNewPassword();
        User user = this.getById(id);
        if (user.getPassword().equals(oldPassword)){
            return this.update(new LambdaUpdateWrapper<User>()
                    .eq(User::getId, id)
                    .set(User::getPassword, newPassword)
            );
        }
        return false;
    }
}
