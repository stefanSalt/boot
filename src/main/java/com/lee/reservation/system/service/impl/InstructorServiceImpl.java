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
import com.lee.reservation.system.converter.InstructorConverter;
import com.lee.reservation.system.mapper.InstructorMapper;
import com.lee.reservation.system.model.bo.InstructorBO;
import com.lee.reservation.system.model.entity.Instructor;
import com.lee.reservation.system.model.form.InstructorForm;
import com.lee.reservation.system.model.form.PasswordChangeForm;
import com.lee.reservation.system.model.form.ProfileForm;
import com.lee.reservation.system.model.query.InstructorPageQuery;
import com.lee.reservation.system.model.vo.InstructorPageVO;
import com.lee.reservation.system.model.vo.InstructorVO;
import com.lee.reservation.system.model.vo.ProfileVO;
import com.lee.reservation.system.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Arrays;
import java.util.List;

/**
 * 教练服务实现类
 *
 * @author baomidou
 * @since 2024-10-14
 */
@Service
public class InstructorServiceImpl extends ServiceImpl<InstructorMapper, Instructor> implements InstructorService {

    @Autowired
    private  InstructorConverter instructorConverter;
    @Autowired
    private InstructorService instructorService;

    /**
    * 获取教练分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<InstructorPageVO>} 教练分页列表
    */
    @Override
    public IPage<InstructorPageVO> listPagedInstructors(InstructorPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<InstructorBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<InstructorBO> boPage = this.baseMapper.listPagedInstructors(page, queryParams);
    
        // 实体转换
        return instructorConverter.toPageVo(boPage);
    }
    
    /**
     * 获取教练表单数据
     *
     * @param id 教练ID
     * @return
     */
    @Override
    public InstructorForm getInstructorFormData(Long id) {
        Instructor entity = this.getById(id);
        return instructorConverter.toForm(entity);
    }
    
    /**
     * 新增教练
     *
     * @param formData 教练表单对象
     * @return
     */
    @Override
    public boolean saveInstructor(InstructorForm formData) {
        // 实体转换 form->entity
        Instructor entity = instructorConverter.toEntity(formData);
        String passwordMD5 = DigestUtils.md5DigestAsHex(SystemConstant.DEFAULT_PASSWORD.getBytes());
        entity.setPassword(SystemConstant.DEFAULT_PASSWORD);
        return this.save(entity);
    }
    
    /**
     * 更新教练
     *
     * @param id   教练ID
     * @param formData 教练表单对象
     * @return
     */
    @Override
    public boolean updateInstructor(Long id,InstructorForm formData) {
        Instructor entity = instructorConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除教练
     *
     * @param ids 教练ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Transactional
    @Override
    public boolean deleteInstructors(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的教练数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return instructorService.removeByIds(idList);
    }


    @Override
    public void login(String username, String password) {
        QueryWrapper<Instructor> InstructorQueryWrapper = new QueryWrapper<>();
        InstructorQueryWrapper.eq("username",username)
                .eq("status",1);
        Instructor Instructor = this.getOne(InstructorQueryWrapper);
       // String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5 = SystemConstant.DEFAULT_PASSWORD;
        if (Instructor == null|| !Instructor.getPassword().equals(passwordMD5)){
            throw new RuntimeException("用户名或密码错误");
        }
    }

    @Override
    public InstructorVO getCurrentInstructorInfo() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Instructor> InstructorQueryWrapper = new QueryWrapper<>();
        InstructorQueryWrapper.eq("username",username);
        Instructor Instructor = this.getOne(InstructorQueryWrapper);
        return instructorConverter.toVo(Instructor);
    }

    @Override
    public ProfileVO getProfile() {
        String username = SystemUtils.getCurrentUsername();
        QueryWrapper<Instructor> InstructorQueryWrapper = new QueryWrapper<>();
        InstructorQueryWrapper.eq("username",username);
        Instructor Instructor = this.getOne(InstructorQueryWrapper);
        return instructorConverter.toProfileVo(Instructor);
    }

    @Override
    public boolean updateProfile(ProfileForm formData) {
        String username = SystemUtils.getCurrentUsername();
        Instructor Instructor = this.getInstructorByUsername(username);
        formData.setId(Instructor.getId());
        Instructor entity = instructorConverter.toEntity(formData);
        return this.updateById(entity);
    }

    @Override
    public boolean resetPassword(Integer userId, String password) {
        String username = SystemUtils.getCurrentUsername();
        //String passwordMD5 = DigestUtils.md5DigestAsHex((password+username).getBytes());
        String passwordMD5=password;
        return this.update(new LambdaUpdateWrapper<Instructor>()
                .eq(Instructor::getId, userId)
                .set(Instructor::getPassword, passwordMD5)
        );
    }

    @Override
    public boolean changePassword(PasswordChangeForm data) {
        String username = SystemUtils.getCurrentUsername();
        Instructor user = this.getInstructorByUsername(username);
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


        boolean result= this.update(new LambdaUpdateWrapper<Instructor>()
                .eq(Instructor::getId, user.getId())
                .set(Instructor::getPassword, newPassword)
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


    public Instructor getInstructorByUsername(String username) {
        return this.getOne(new LambdaQueryWrapper<Instructor>().eq(Instructor::getUsername, username));
    }
}
