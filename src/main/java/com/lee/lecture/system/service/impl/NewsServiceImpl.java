package com.lee.lecture.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.News;
import com.lee.lecture.system.mapper.NewsMapper;
import com.lee.lecture.system.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
/**
 * 资讯信息表服务实现类
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Service
@RequiredArgsConstructor
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {


    /**
    * 获取资讯信息表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<News>} 资讯信息表分页列表
    */
    @Override
    public IPage<News> listPagedNewss(News queryParams, Integer pageNum, Integer pageSize) {
    

        Page<News> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<News> boPage = this.baseMapper.listPagedNewss(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取资讯信息表表单数据
     *
     * @param id 资讯信息表ID
     * @return
     */
    @Override
    public News getNewsData(Long id) {
        News entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增资讯信息表
     *
     * @param formData 资讯信息表表单对象
     * @return
     */
    @Override
    public boolean saveNews(News formData) {

        return this.save(formData);
    }
    
    /**
     * 更新资讯信息表
     *
     * @param id   资讯信息表ID
     * @param formData 资讯信息表表单对象
     * @return
     */
    @Override
    public boolean updateNews(Long id,News formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除资讯信息表
     *
     * @param ids 资讯信息表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteNewss(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的资讯信息表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }


}
