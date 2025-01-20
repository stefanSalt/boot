package com.lee.lecture.system.mapper;

import com.lee.lecture.system.model.entity.Lecture;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.system.model.entity.Lecture;
import com.lee.lecture.system.model.vo.LectureReservationExcelVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 讲座信息表 Mapper 接口
 *
 * @author baomidou
 * @since 2025-01-07
 */

@Mapper
public interface LectureMapper extends BaseMapper<Lecture> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    Page<Lecture> listPagedLectures(Page<Lecture> page, Lecture queryParams);

    /**
     * 查询讲座预约导出数据
     *
     * @param lectureId 讲座ID
     * @return 预约名单列表
     */
    List<LectureReservationExcelVO> selectExportList(@Param("lectureId") Long lectureId);
}
