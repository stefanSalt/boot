package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Apply;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.ApplyForm;
import com.lee.selection.system.model.query.ApplyPageQuery;
import com.lee.selection.system.model.vo.ApplyPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 申请表 服务类
 *
 * @author baomidou
 * @since 2024-10-31
 */
public interface ApplyService extends IService<Apply> {


    /**
     *申请表分页列表
     *
     * @return
     */
    IPage<ApplyPageVO> listPagedApplys(ApplyPageQuery queryParams);


    /**
     * 获取申请表表单数据
     *
     * @param id 申请表ID
     * @return
     */
     ApplyForm getApplyFormData(Long id);


    /**
     * 新增申请表
     *
     * @param formData 申请表表单对象
     * @return
     */
    boolean saveApply(ApplyForm formData);

    /**
     * 修改申请表
     *
     * @param id   申请表ID
     * @param formData 申请表表单对象
     * @return
     */
    boolean updateApply(Long id, ApplyForm formData);


    /**
     * 删除申请表
     *
     * @param ids 申请表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteApplys(String ids);

    IPage<ApplyPageVO> listPagedApplysByStudent(ApplyPageQuery queryParams);

    IPage<ApplyPageVO> listPagedApplysByTeacher(ApplyPageQuery queryParams);
}
