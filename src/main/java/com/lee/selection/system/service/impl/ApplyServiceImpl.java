package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Apply;
import com.lee.selection.system.mapper.ApplyMapper;
import com.lee.selection.system.model.entity.User;
import com.lee.selection.system.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lee.selection.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.form.ApplyForm;
import com.lee.selection.system.model.query.ApplyPageQuery;
import com.lee.selection.system.model.bo.ApplyBO;
import com.lee.selection.system.model.vo.ApplyPageVO;
import com.lee.selection.system.converter.ApplyConverter;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 申请表服务实现类
 *
 * @author baomidou
 * @since 2024-10-31
 */
@Service
@RequiredArgsConstructor
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {

    private final ApplyConverter applyConverter;

    private final UserService userService;

    /**
    * 获取申请表分页列表
    *
    * @param queryParams 查询参数
    * @return {@link IPage<ApplyPageVO>} 申请表分页列表
    */
    @Override
    public IPage<ApplyPageVO> listPagedApplys(ApplyPageQuery queryParams) {
    
        // 参数构建
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        Page<ApplyBO> page = new Page<>(pageNum, pageSize);

        // 格式化为数据库日期格式，避免日期比较使用格式化函数导致索引失效
        DateUtils.toDatabaseFormat(queryParams, "startTime", "endTime");
    
        // 查询数据
        Page<ApplyBO> boPage = this.baseMapper.listPagedApplys(page, queryParams);
    
        // 实体转换
        return applyConverter.toPageVo(boPage);
    }

    @Override
    public IPage<ApplyPageVO> listPagedApplysByStudent(ApplyPageQuery queryParams) {
        Integer id = userService.getCurrentUser().getId();
        if (id != null) {
            queryParams.setStudentId(id);
            return listPagedApplys(queryParams);
        }
        return null;
    }

    @Override
    public IPage<ApplyPageVO> listPagedApplysByTeacher(ApplyPageQuery queryParams) {
        Integer id = userService.getCurrentUser().getId();
        if (id != null) {
            queryParams.setTeacherId(id);
            return listPagedApplys(queryParams);
        }
        return null;
    }

    /**
     * 获取申请表表单数据
     *
     * @param id 申请表ID
     * @return
     */
    @Override
    public ApplyForm getApplyFormData(Long id) {
        Apply entity = this.getById(id);
        return applyConverter.toForm(entity);
    }
    
    /**
     * 新增申请表
     *
     * @param formData 申请表表单对象
     * @return
     */
    @Override
    public boolean saveApply(ApplyForm formData) {
        User user = userService.getCurrentUser();
        if (user != null && user.getRoleId()==3){
            formData.setStudentId(user.getId());
        }
        // 实体转换 form->entity
        Apply entity = applyConverter.toEntity(formData);
        return this.save(entity);
    }
    
    /**
     * 更新申请表
     *
     * @param id   申请表ID
     * @param formData 申请表表单对象
     * @return
     */
    @Override
    public boolean updateApply(Long id,ApplyForm formData) {
        Apply entity = applyConverter.toEntity(formData);
        return this.updateById(entity);
    }
    
    /**
     * 删除申请表
     *
     * @param ids 申请表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteApplys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的申请表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }
    

}
