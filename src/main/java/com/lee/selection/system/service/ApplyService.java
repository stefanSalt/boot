package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Apply;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.system.model.vo.ApplyVO;

/**
 * 申请 服务类
 *
 * @author baomidou
 * @since 2024-11-13
 */
public interface ApplyService extends IService<Apply> {


    /**
     *申请分页列表
     *
     * @return
     */
    IPage<ApplyVO> listPagedApplys(ApplyVO queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取申请表单数据
     *
     * @param id 申请ID
     * @return
     */
     Apply getApplyData(Long id);


    /**
     * 新增申请
     *
     * @param formData 申请表单对象
     * @return
     */
    boolean saveApply(Apply formData);

    /**
     * 修改申请
     *
     * @param id   申请ID
     * @param formData 申请表单对象
     * @return
     */
    boolean updateApply(Long id, Apply formData);


    /**
     * 删除申请
     *
     * @param ids 申请ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteApplys(String ids);

    boolean isApplied(Integer studentId, Integer jobId);
}
