package com.lee.selection.system.service;

import com.lee.selection.system.model.entity.Clazz;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.selection.system.model.form.ClazzForm;
import com.lee.selection.system.model.query.ClazzPageQuery;
import com.lee.selection.system.model.vo.ClazzPageVO;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 班级 服务类
 *
 * @author baomidou
 * @since 2024-10-30
 */
public interface ClazzService extends IService<Clazz> {


    /**
     *班级分页列表
     *
     * @return
     */
    IPage<ClazzPageVO> listPagedClazzs(ClazzPageQuery queryParams);


    /**
     * 获取班级表单数据
     *
     * @param id 班级ID
     * @return
     */
     ClazzForm getClazzFormData(Long id);


    /**
     * 新增班级
     *
     * @param formData 班级表单对象
     * @return
     */
    boolean saveClazz(ClazzForm formData);

    /**
     * 修改班级
     *
     * @param id   班级ID
     * @param formData 班级表单对象
     * @return
     */
    boolean updateClazz(Long id, ClazzForm formData);


    /**
     * 删除班级
     *
     * @param ids 班级ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteClazzs(String ids);

    List<ClazzPageVO> listClazzs(ClazzPageQuery queryParams);
}
