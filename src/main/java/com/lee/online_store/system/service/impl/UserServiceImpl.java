package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.common.constant.SystemConstant;
import com.lee.online_store.system.mapper.RecruiterMapper;
import com.lee.online_store.system.mapper.RoleMapper;
import com.lee.online_store.system.mapper.StudentMapper;
import com.lee.online_store.system.model.dto.ChangePasswordDTO;
import com.lee.online_store.system.model.dto.UserDTO;
import com.lee.online_store.system.model.entity.Recruiter;
import com.lee.online_store.system.model.entity.Role;
import com.lee.online_store.system.model.entity.Student;
import com.lee.online_store.system.model.entity.User;
import com.lee.online_store.system.mapper.UserMapper;
import com.lee.online_store.system.service.UserService;
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
    public IPage<User> listPagedUsers(User queryParams, Integer pageNum, Integer pageSize) {
    

        Page<User> page = new Page<>(pageNum, pageSize);

        // 查询数据
        IPage<User> userPage = this.baseMapper.listPagedUsers(page, queryParams);

        // 实体转换
        return userPage;
    }
    
    /**
     * 获取用户表单数据
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public User getUserData(Long id) {
        User user = this.getById(id);
        return user;
    }
    public UserDTO toDTO(User user){
        UserDTO entity = new UserDTO(user);
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
    public boolean saveUser(User formData) {
        formData.setPassword(SystemConstant.DEFAULT_PASSWORD);
        formData.setAvatar(SystemConstant.DEFAULT_AVATAR);
        formData.setStatus(1);
        this.save(formData);
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
    public boolean updateUser(Long id,User formData) {
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
    public boolean updateProfile(User formData) {
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
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        User user = this.getOne(queryWrapper);
        return user;
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
