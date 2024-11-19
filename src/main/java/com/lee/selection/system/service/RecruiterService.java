package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Recruiter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.entity.Recruiter;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 招聘者 服务类
 *
 * @author baomidou
 * @since 2024-11-08
 */
public interface RecruiterService extends IService<Recruiter> {


    /**
     *招聘者分页列表
     *
     * @return
     */
    IPage<Recruiter> listPagedRecruiters(Recruiter queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取招聘者表单数据
     *
     * @param id 招聘者ID
     * @return
     */
     Recruiter getRecruiterData(Long id);


    /**
     * 新增招聘者
     *
     * @param formData 招聘者表单对象
     * @return
     */
    boolean saveRecruiter(Recruiter formData);

    /**
     * 修改招聘者
     *
     * @param id   招聘者ID
     * @param formData 招聘者表单对象
     * @return
     */
    boolean updateRecruiter(Long id, Recruiter formData);


    /**
     * 删除招聘者
     *
     * @param ids 招聘者ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteRecruiters(String ids);

}
