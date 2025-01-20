package com.lee.lecture.system.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lee.lecture.common.exception.BusinessException;
import com.lee.lecture.system.model.entity.Lecture;
import com.lee.lecture.system.mapper.LectureMapper;
import com.lee.lecture.system.model.vo.LectureReservationExcelVO;
import com.lee.lecture.system.service.LectureService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;
/**
 * 讲座信息表服务实现类
 *
 * @author baomidou
 * @since 2025-01-07
 */
@Service
@RequiredArgsConstructor
public class LectureServiceImpl extends ServiceImpl<LectureMapper, Lecture> implements LectureService {


    /**
    * 获取讲座信息表分页列表
    *
    * @param queryParams 查询参数
    * @param pageNum 页号
     * @param pageSize 页大小
     *
    * @return {@link IPage<Lecture>} 讲座信息表分页列表
    */
    @Override
    public IPage<Lecture> listPagedLectures(Lecture queryParams, Integer pageNum, Integer pageSize) {
    

        Page<Lecture> page = new Page<>(pageNum, pageSize);


        // 查询数据
        Page<Lecture> boPage = this.baseMapper.listPagedLectures(page, queryParams);

        return boPage;
    }
    
    /**
     * 获取讲座信息表表单数据
     *
     * @param id 讲座信息表ID
     * @return
     */
    @Override
    public Lecture getLectureData(Long id) {
        Lecture entity = this.getById(id);
        return entity;
    }
    
    /**
     * 新增讲座信息表
     *
     * @param formData 讲座信息表表单对象
     * @return
     */
    @Override
    public boolean saveLecture(Lecture formData) {

        return this.save(formData);
    }
    
    /**
     * 更新讲座信息表
     *
     * @param id   讲座信息表ID
     * @param formData 讲座信息表表单对象
     * @return
     */
    @Override
    public boolean updateLecture(Long id,Lecture formData) {
        return this.updateById(formData);
    }
    
    /**
     * 删除讲座信息表
     *
     * @param ids 讲座信息表ID，多个以英文逗号(,)分割
     * @return true|false
     */
    @Override
    public boolean deleteLectures(String ids) {
        Assert.isTrue(StrUtil.isNotBlank(ids), "删除的讲座信息表数据为空");
        // 逻辑删除
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .toList();
        return this.removeByIds(idList);
    }

    /**
     * 获取讲座信息表下拉选项
     *
     * @return
     */
    @Override
    public List<Lecture> listLectureOptions() {
        QueryWrapper<Lecture> lectureQueryWrapper = new QueryWrapper<>();
        lectureQueryWrapper.select("id", "title");
        lectureQueryWrapper.orderByAsc("id");
        return this.baseMapper.selectList(lectureQueryWrapper);
    }


    @Override
    public boolean exportLectures(Integer ids) {
        return false;
    }

    /**
     * 导出讲座预约名单
     *
     * @param lectureId 讲座ID
     * @param response HTTP响应对象
     */
    @Override
    public void exportReservations(Long lectureId, HttpServletResponse response) {
        try {
            // 查询讲座信息
            Lecture lecture = this.baseMapper.selectById(lectureId);
            if (lecture == null) {
                throw new BusinessException("讲座不存在");
            }

            // 查询预约名单
            List<LectureReservationExcelVO> list = this.baseMapper.selectExportList(lectureId);

            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(lecture.getTitle() + "-预约名单", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            // 导出Excel
            EasyExcel.write(response.getOutputStream(), LectureReservationExcelVO.class)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy()) // 自适应列宽
                    .sheet("预约名单")
                    .doWrite(list);

        } catch (Exception e) {

            throw new BusinessException("导出失败");
        }
    }
}
