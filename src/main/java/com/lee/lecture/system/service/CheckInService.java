package com.lee.lecture.system.service;

import com.lee.lecture.system.model.entity.CheckIn;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.lecture.system.model.entity.CheckIn;
import com.baomidou.mybatisplus.core.metadata.IPage;
/**
 * 讲座签到表 服务类
 *
 * @author baomidou
 * @since 2025-01-08
 */
public interface CheckInService extends IService<CheckIn> {


    /**
     *讲座签到表分页列表
     *
     * @return
     */
    IPage<CheckIn> listPagedCheckIns(CheckIn queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取讲座签到表表单数据
     *
     * @param id 讲座签到表ID
     * @return
     */
     CheckIn getCheckInData(Long id);


    /**
     * 新增讲座签到表
     *
     * @param formData 讲座签到表表单对象
     * @return
     */
    boolean saveCheckIn(CheckIn formData);

    /**
     * 修改讲座签到表
     *
     * @param id   讲座签到表ID
     * @param formData 讲座签到表表单对象
     * @return
     */
    boolean updateCheckIn(Long id, CheckIn formData);


    /**
     * 删除讲座签到表
     *
     * @param ids 讲座签到表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteCheckIns(String ids);

}
