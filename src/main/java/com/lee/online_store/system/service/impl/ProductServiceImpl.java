package com.lee.online_store.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.online_store.system.model.dto.ProductQuery;
import com.lee.online_store.system.model.entity.Product;
import com.lee.online_store.system.mapper.ProductMapper;
import com.lee.online_store.system.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商品信息服务实现类
 *
 * @author baomidou
 * @since 2024-11-26
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {


    /**
     * 获取商品信息分页列表
     *
     * @param queryParams 查询参数
     * @param pageNum     页号
     * @param pageSize    页大小
     * @return {@link IPage<Product>} 商品信息分页列表
     */
    @Override
    public IPage<Product> listPagedProducts(ProductQuery queryParams, Integer pageNum, Integer pageSize) {


        Page<Product> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Product> boPage = this.baseMapper.listPagedProducts(page, queryParams);

        // 实体转换
        return boPage;
    }

    @Override
    public List<Product> listProducts(ProductQuery queryParams) {

        return this.baseMapper.listProducts(queryParams);
    }

    /**
     * 获取商品信息表单数据
     *
     * @param id 商品信息ID
     * @return
     */
    @Override
    public Product getProductData(Long id) {
        Product entity = this.getById(id);
        List<String> imgs = new ArrayList<>();
        imgs.add(entity.getImg1());
        imgs.add(entity.getImg2());
        imgs.add(entity.getImg3());
        imgs.add(entity.getImg4());
        entity.setImgs(imgs);
        return entity;
    }

    /**
     * 新增商品信息
     *
     * @param formData 商品信息表单对象
     * @return
     */
    @Override
    public boolean saveProduct(Product formData) {
        formData.setStatus(0);
        List<String> imgs = formData.getImgs();
        for (int i = 0; i < Math.min(4, imgs.size()); i++) {
            String img = imgs.get(i);
            switch (i) {
                case 0:
                    formData.setImg1(img);
                    break;
                case 1:
                    formData.setImg2(img);
                    break;
                case 2:
                    formData.setImg3(img);
                    break;
                case 3:
                    formData.setImg4(img);
                    break;
            }
        }
        return this.save(formData);
    }

    /**
     * 更新商品信息
     *
     * @param id       商品信息ID
     * @param formData 商品信息表单对象
     * @return
     */
    @Override
    public boolean updateProduct(Long id, Product formData) {
        formData.setImg1(null);
        formData.setImg2(null);
        formData.setImg3(null);
        formData.setImg4(null);
        if (formData.getImgs() != null){
            List<String> imgs = formData.getImgs();
            for (int i = 0; i < Math.min(4, imgs.size()); i++) {
                String img = imgs.get(i);
                switch (i) {
                    case 0:
                        formData.setImg1(img);
                        break;
                    case 1:
                        formData.setImg2(img);
                        break;
                    case 2:
                        formData.setImg3(img);
                        break;
                    case 3:
                        formData.setImg4(img);
                        break;
                }
            }
        }
        return this.updateById(formData);
    }

    /**
     * 删除商品信息
     *
     * @param ids 商品信息ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteProducts(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的商品信息数据为空" );
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split("," ))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    @Override
    public List<Product> listProductsByIds(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "商品信息ID为空");
        List<Long> idList = Arrays.stream(ids.split("," ))
                .map(Long::parseLong)
                .toList();

        return this.listByIds(idList);
    }

    @Override
    public List<Product> listDiscountByIds(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "商品信息ID为空");
        List<Long> idList = Arrays.stream(ids.split("," ))
                .map(Long::parseLong)
                .toList();
        return this.baseMapper.listDiscountByIds(idList);
    }
}
