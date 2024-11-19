package com.lee.selection.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.selection.common.util.DateUtils;
import com.lee.selection.system.model.entity.Apply;
import com.lee.selection.system.mapper.ApplyMapper;
import com.lee.selection.system.model.vo.ApplyVO;
import com.lee.selection.system.service.ApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.lee.selection.system.model.entity.Apply;

import java.util.Arrays;
import java.util.List;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
/**
 * 申请服务实现类
 *
 * @author baomidou
 * @since 2024-11-13
 */
@Service
@RequiredArgsConstructor
public class ApplyServiceImpl extends ServiceImpl<ApplyMapper, Apply> implements ApplyService {


    /**
    * 获取申请分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Apply>} 申请分页列表
    */
    @Override
    public IPage<ApplyVO> listPagedApplys(ApplyVO queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Apply> page = new Page<>(pageNum, pageSize);

    
        // 查询数据
        Page<ApplyVO> boPage = this.baseMapper.listPagedApplys(page, queryParams);
    
        // 实体转换
        return boPage;
    }
    
    /**
     * 获取申请表单数据
     *
     * @param id 申请ID
     * @return
     */
    @Override
    public Apply getApplyData(Long id) {
        Apply entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增申请
     *
     * @param formData 申请表单对象
     * @return
     */
    @Override
    public boolean saveApply(Apply formData) {

        return this.save(formData);
    }
    
    /**
     * 更新申请
     *
     * @param id   申请ID
     * @param formData 申请表单对象
     * @return
     */
    @Override
    public boolean updateApply(Long id,Apply formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除申请
     *
     * @param ids 申请ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteApplys(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的申请数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public boolean isApplied(Integer studentId, Integer jobId) {
        boolean exists = this.exists(new QueryWrapper<Apply>().eq("stu_id", studentId)
                .eq("job_id", jobId));
        return exists;
    }
}
