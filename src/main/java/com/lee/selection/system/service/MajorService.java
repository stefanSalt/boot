package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Major;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.MajorForm;
import com.lee.selection.system.model.query.MajorPageQuery;
import com.lee.selection.system.model.vo.MajorPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lee.selection.system.model.vo.MajorVO;

import java.util.List;

/**
 * 专业信息表 服务类
 *
 * @author baomidou
 * @since 2024-10-25
 */
public interface MajorService extends IService<Major> {


    /**
     *专业信息表分页列表
     *
     * @return
     */
    IPage<MajorPageVO> listPagedMajors(MajorPageQuery queryParams);


    /**
     * 获取专业信息表表单数据
     *
     * @param id 专业信息表ID
     * @return
     */
     MajorForm getMajorFormData(Long id);


    /**
     * 新增专业信息表
     *
     * @param formData 专业信息表表单对象
     * @return
     */
    boolean saveMajor(MajorForm formData);

    /**
     * 修改专业信息表
     *
     * @param id   专业信息表ID
     * @param formData 专业信息表表单对象
     * @return
     */
    boolean updateMajor(Long id, MajorForm formData);


    /**
     * 删除专业信息表
     *
     * @param ids 专业信息表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteMajors(String ids);

    List<MajorVO> listMajors(MajorPageQuery queryParams);
}
