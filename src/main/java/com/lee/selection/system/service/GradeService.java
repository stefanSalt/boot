package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Grade;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.GradeForm;
import com.lee.selection.system.model.query.GradePageQuery;
import com.lee.selection.system.model.vo.GradePageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 成绩服务类
 *
 * @author baomidou
 * @since 2024-11-01
 */
public interface GradeService extends IService<Grade> {


    /**
     *成绩分页列表
     *
     * @return
     */
    IPage<GradePageVO> listPagedGrades(GradePageQuery queryParams);


    /**
     * 获取成绩表单数据
     *
     * @param id 成绩ID
     * @return
     */
     GradeForm getGradeFormData(Long id);


    /**
     * 新增成绩
     *
     * @param formData 成绩表单对象
     * @return
     */
    boolean saveGrade(GradeForm formData);

    /**
     * 修改成绩
     *
     * @param id   成绩ID
     * @param formData 成绩表单对象
     * @return
     */
    boolean updateGrade(Long id, GradeForm formData);


    /**
     * 删除成绩
     *
     * @param ids 成绩ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteGrades(String ids);

}
