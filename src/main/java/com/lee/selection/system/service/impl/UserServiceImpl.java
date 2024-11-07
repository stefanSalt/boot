package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.constant.SystemConstant;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.mapper.UserMapper;
import com.lee.selection.system.model.vo.UserProfileVO;
import com.lee.selection.system.model.vo.UserVO;
import com.lee.selection.system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.converter.UserConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户服务实现类
 *
 * @author baomidou
 * @since 2024-10-24
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserConverter userConverter;

    /**
    * 获取用户分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<UserPageVO>} 用户分页列表
    */
    @Override
    public IPage listPagedUsers(User queryParams, Integer pageNum, Integer pageSize) {
    

        Page<User> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        //DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<User> boPage = this.baseMapper.listPagedUsers(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取用户表单数据
     *
     * @param id 用户ID
     * @return
     */
    @Override
    public User getUserData(Long id) {
        User entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增用户
     *
     * @param formData 用户表单对象
     * @return
     */
    @Override
    public boolean saveUser(User formData) {
        // 实体转换 form->entity
        formData.setPassword(SystemConstant.DEFAULT_PASSWORD);
        formData.setAvatar(SystemConstant.DEFAULT_AVATAR);
        return this.save(formData);
    }
    
    /**
     * 更新用户
     *
     * @param id   用户ID
     * @param formData 用户表单对象
     * @return
     */
    @Override
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
    public boolean deleteUsers(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的用户数据为空");
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
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
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
}
