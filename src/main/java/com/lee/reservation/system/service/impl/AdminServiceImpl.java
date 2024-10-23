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
import com.lee.reservation.system.converter.AdminConverter;
import com.lee.reservation.system.mapper.AdminMapper;
import com.lee.reservation.system.model.bo.AdminBO;
import com.lee.reservation.system.model.entity.Admin;
import com.lee.reservation.system.model.form.AdminForm;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.query.AdminPageQuery;
import com.lee.reservation.system.model.vo.AdminPageVO;
import com.lee.reservation.system.model.vo.AdminVO;
import com.lee.reservation.system.model.vo.ProfileVO;
import com.lee.reservation.system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 管理员服务实现类
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private  AdminConverter adminConverter;
    @Autowired
    private AdminService adminService;

    /**
    * 获取管理员分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<AdminPageVO>} 管理员分页列表
    */
    @Override
    public IPage<AdminPageVO> listPagedAdmins(AdminPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<AdminBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<AdminBO> boPage = this.baseMapper.listPagedAdmins(page, queryParams);
    
        // 实体转换
        return adminConverter.toPageVo(boPage);
    }
    
    /**
     * 获取管理员表单数据
     *
     * @param id 管理员ID
     * @return
     */
    @Override
    public AdminForm getAdminFormData(Long id) {
        Admin entity = this.getById(id);
        return adminConverter.toForm(entity);
    }
    
    /**
     * 新增管理员
     *
     * @param formData 管理员表单对象
     * @return
     */
    @Override
    public boolean saveAdmin(AdminForm formData) {
        // 实体转换 form->entity
        Admin entity = adminConverter.toEntity(formData);
        String passwordMD5 = DigestUtils.md5DigestAsHex(SystemConstant.DEFAULT_PASSWORD.getBytes());
        entity.setPassword(SystemConstant.DEFAULT_PASSWORD);
        return this.save(entity);
    }
    
    /**
     * 更新管理员
     *
     * @param id   管理员ID
     * @param formData 管理员表单对象
     * @return
     */
    @Override
    public boolean updateAdmin(Long id,AdminForm formData) {
        Admin entity = adminConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除管理员
     *
     * @param ids 管理员ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Transactional
    @Override
    public boolean deleteAdmins(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的管理员数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return adminService.removeByIds(idList);
    }


    @Override
    public void login(String username, String password) {
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username)
                .eq("status",1);
        Admin admin = this.getOne(adminQueryWrapper);
       // String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5 = SystemConstant.DEFAULT_PASSWORD;
        if (admin == null|| !admin.getPassword().equals(passwordMD5)){
            throw new RuntimeException("用户名或密码错误");
        }
    }

    @Override
    public AdminVO getCurrentAdminInfo() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username);
        Admin admin = this.getOne(adminQueryWrapper);
        return adminConverter.toVo(admin);
    }

    @Override
    public ProfileVO getProfile() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Admin> adminQueryWrapper = new QueryWrapper<>();
        adminQueryWrapper.eq("username",username);
        Admin admin = this.getOne(adminQueryWrapper);
        return adminConverter.toProfileVo(admin);
    }

    @Override
    public boolean updateProfile(ProfileForm formData) {
        String username = SystemUtils.getCurrentUsername();
        Admin admin = this.getAdminByUsername(username);
        formData.setId(admin.getId());
        Admin entity = adminConverter.toEntity(formData);
        return this.updateById(entity);
    }

    @Override
    public boolean resetPassword(Integer userId, String password) {
        String username = SystemUtils.getCurrentUsername();
        //String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5=password;
        return this.update(new LambdaUpdateWrapper<Admin>()
                .eq(Admin::getId, userId)
                .set(Admin::getPassword, passwordMD5)
        );
    }

    @Override
    public boolean changePassword(PasswordChangeForm data) {
        String username = SystemUtils.getCurrentUsername();
        Admin user = this.getAdminByUsername(username);
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


        boolean result= this.update(new LambdaUpdateWrapper<Admin>()
                .eq(Admin::getId, user.getId())
                .set(Admin::getPassword, newPassword)
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


    public Admin getAdminByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<Admin>().eq(Admin::getUsername, username));
    }

    @Override
    public String getAdminName(Integer userId) {
        Admin admin = this.getById(userId);
        if (admin != null) {
            return admin.getNickname();
        }
        return null;
    }
}
