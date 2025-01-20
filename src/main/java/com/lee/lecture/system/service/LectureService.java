package com.lee.lecture.system.service;

import com.lee.lecture.system.model.entity.Lecture;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * 讲座信息表 服务类
 *
 * @author baomidou
 * @since 2025-01-07
 */
public interface LectureService extends IService<Lecture> {


    /**
     *讲座信息表分页列表
     *
     * @return
     */
    IPage<Lecture> listPagedLectures(Lecture queryParams, Integer pageNum, Integer pageSize);


    /**
     * 获取讲座信息表表单数据
     *
     * @param id 讲座信息表ID
     * @return
     */
     Lecture getLectureData(Long id);


    /**
     * 新增讲座信息表
     *
     * @param formData 讲座信息表表单对象
     * @return
     */
    boolean saveLecture(Lecture formData);

    /**
     * 修改讲座信息表
     *
     * @param id   讲座信息表ID
     * @param formData 讲座信息表表单对象
     * @return
     */
    boolean updateLecture(Long id, Lecture formData);


    /**
     * 删除讲座信息表
     *
     * @param ids 讲座信息表ID，多个以英文逗号(,)分割
     * @return
     */
    boolean deleteLectures(String ids);

    boolean exportLectures(Integer ids);

    void exportReservations(Long lectureId, HttpServletResponse response);

    List<Lecture> listLectureOptions();
}
