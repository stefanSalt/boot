package com.lee.system.service;

import com.lee.system.model.entity.News;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.system.model.entity.News;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 资讯表 服务类
 *
 * @author baomidou
 * @since 2025-02-10
 */
public interface NewsService extends IService<News> {


    /**
     *资讯表分页列表
     *
     * @return
     */
    IPage<News> listPagedNewss(News queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取资讯表表单数据
     *
     * @param id 资讯表ID
     * @return
     */
     News getNewsData(Long id);


    /**
     * 新增资讯表
     *
     * @param formData 资讯表表单对象
     * @return
     */
    boolean saveNews(News formData);

    /**
     * 修改资讯表
     *
     * @param id   资讯表ID
     * @param formData 资讯表表单对象
     * @return
     */
    boolean updateNews(Long id, News formData);


    /**
     * 删除资讯表
     *
     * @param ids 资讯表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteNewss(String ids);

}
