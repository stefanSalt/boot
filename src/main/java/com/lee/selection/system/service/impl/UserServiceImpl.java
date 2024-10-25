package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.common.util.SystemUtils;
import com.lee.selection.system.model.entity.Admin;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.mapper.UserMapper;
import com.lee.selection.system.model.form.ProfileForm;
import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;
import com.lee.selection.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.UserForm;
import com.lee.selection.system.model.query.UserPageQuery;
import com.lee.selection.system.model.bo.UserBO;
import com.lee.selection.system.model.vo.UserPageVO;
import com.lee.selection.system.converter.UserConverter;

import java.util.Arrays;
import java.util.List;

/**
 * 包含  管理员  教师  学生等服务实现类
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserConverter userConverter;

    /**
    * 获取包含  管理员  教师  学生等分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<UserPageVO>} 包含  管理员  教师  学生等分页列表
    */
    @Override
    public IPage<UserPageVO> listPagedUsers(UserPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<UserBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<UserBO> boPage = this.baseMapper.listPagedUsers(page, queryParams);
    
        // 实体转换
        return userConverter.toPageVo(boPage);
    }
    
    /**
     * 获取包含  管理员  教师  学生等表单数据
     *
     * @param id 包含  管理员  教师  学生等ID
     * @return
     */
    @Override
    public UserForm getUserFormData(Long id) {
        User entity = this.getById(id);
        return userConverter.toForm(entity);
    }
    
    /**
     * 新增包含  管理员  教师  学生等
     *
     * @param formData 包含  管理员  教师  学生等表单对象
     * @return
     */
    @Override
    public boolean saveUser(UserForm formData) {
        // 实体转换 form->entity
        User entity = userConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新包含  管理员  教师  学生等
     *
     * @param id   包含  管理员  教师  学生等ID
     * @param formData 包含  管理员  教师  学生等表单对象
     * @return
     */
    @Override
    public boolean updateUser(Long id,UserForm formData) {
        User entity = userConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除包含  管理员  教师  学生等
     *
     * @param ids 包含  管理员  教师  学生等ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteUsers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的包含  管理员  教师  学生等数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public UserVO getCurrentUserInfo() {
        return userConverter.toVo(getCurrentUser());
    }

    @Override
    public User getCurrentUser() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("code",username);
        return this.getOne(queryWrapper);
    }

    @Override
    public String getUserName(Integer id) {
        return this.getById(id).getName();
    }

    @Override
    public UserProfileVO getProfile() {
        return userConverter.toProfile(getCurrentUser());
    }

    @Override
    public boolean updateProfile(ProfileForm formData) {
        User user = this.getCurrentUser();
        formData.setId(user.getId());
        User entity = userConverter.toEntity(formData);
        return this.updateById(entity);
    }
}
