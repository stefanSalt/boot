package com.lee.lecture.system.service;

import com.lee.lecture.system.model.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 资讯信息表 服务类
 *
 * @author baomidou
 * @since 2025-01-07
 */
public interface NewsService extends IService<News> {


    /**
     *资讯信息表分页列表
     *
     * @return
     */
    IPage<News> listPagedNewss(News queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取资讯信息表表单数据
     *
     * @param id 资讯信息表ID
     * @return
     */
     News getNewsData(Long id);


    /**
     * 新增资讯信息表
     *
     * @param formData 资讯信息表表单对象
     * @return
     */
    boolean saveNews(News formData);

    /**
     * 修改资讯信息表
     *
     * @param id   资讯信息表ID
     * @param formData 资讯信息表表单对象
     * @return
     */
    boolean updateNews(Long id, News formData);


    /**
     * 删除资讯信息表
     *
     * @param ids 资讯信息表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNewss(String ids);


}
